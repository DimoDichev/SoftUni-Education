import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Medivac {

    public static class Unit {
        int unit;
        int size;
        int urgency;

        public Unit(int unit, int size, int urgency) {
            this.unit = unit;
            this.size = size;
            this.urgency = urgency;
        }

        public int getUnit() {
            return unit;
        }

        public int getSize() {
            return size;
        }

        public int getUrgency() {
            return urgency;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine());

        List<Unit> units = new ArrayList<>();

        String infoLine = reader.readLine();

        while (!infoLine.equals("Launch")) {
            int[] properties = Arrays.stream(infoLine.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            units.add(new Unit(properties[0], properties[1], properties[2]));

            infoLine = reader.readLine();
        }

        int[][] dp = new int[units.size() + 1][capacity + 1];
        boolean[][] deliveredUnits = new boolean[units.size() + 1][capacity + 1];

        for (int currentUnit = 1; currentUnit <= units.size(); currentUnit++) {

            Unit unit = units.get(currentUnit - 1);

            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {

                int excludedUnit = dp[currentUnit - 1][currentCapacity];

                if (currentCapacity - unit.size < 0) {
                    dp[currentUnit][currentCapacity] = excludedUnit;
                } else {
                    int includedUnit = dp[currentUnit - 1][currentCapacity - unit.size] + unit.urgency;

                    if (excludedUnit > includedUnit) {
                        dp[currentUnit][currentCapacity] = excludedUnit;
                    } else {
                        dp[currentUnit][currentCapacity] = includedUnit;
                        deliveredUnits[currentUnit][currentCapacity] = true;
                    }
                }

            }

        }

        Set<Unit> output = new TreeSet<>(Comparator.comparingInt(u -> u.unit));

        int urgencyAchieved = dp[units.size()][capacity];

        int lastUnit = units.size();

        while (lastUnit > 0) {
            if (deliveredUnits[lastUnit][capacity]) {
                Unit unit = units.get(lastUnit - 1);
                output.add(unit);
                capacity -= unit.size;
            }
            lastUnit --;
        }

        System.out.println(output.stream().mapToInt(Unit::getSize).sum());
        System.out.println(urgencyAchieved);
        System.out.println(output.stream().map(Unit::getUnit).map(String::valueOf).collect(Collectors.joining(System.lineSeparator())));
    }

}
