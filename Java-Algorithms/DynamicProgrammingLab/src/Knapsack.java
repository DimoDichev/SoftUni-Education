import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Knapsack {

    public static class Item implements Comparable<Item> {
        private String name;
        private int weight;
        private int value;

        public Item(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Item other) {
            return this.getName().compareTo(other.getName());
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine());

        String line = reader.readLine();

        List<Item> items = new ArrayList<>();

        while (!line.equals("end")) {
            String[] tokens = line.split("\\s+");
            items.add(new Item(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
            line = reader.readLine();
        }

        int[][] maxValue = new int[items.size() + 1][capacity + 1];
        boolean[][] itemIncluded = new boolean[items.size() + 1][capacity + 1];

        for (int itemRow = 1; itemRow <= items.size(); itemRow++) {
            Item item = items.get(itemRow - 1);

            for (int capacityCol = 0; capacityCol <= capacity; capacityCol++) {
                int excluded = maxValue[itemRow - 1][capacityCol];

                if (capacityCol - item.weight < 0) {
                    maxValue[itemRow][capacityCol] = excluded;
                } else {
                    int included = maxValue[itemRow - 1][capacityCol - item.weight] + item.getValue();

                    if (excluded > included) {
                        maxValue[itemRow][capacityCol] = excluded;
                    } else {
                        maxValue[itemRow][capacityCol] = included;
                        itemIncluded[itemRow][capacityCol] = true;
                    }
                }
            }
        }

        int weight = capacity;

        int bestValue = maxValue[items.size()][capacity];

        while (maxValue[items.size()][weight - 1] == bestValue) {
            weight--;
        }

        Set<Item> takenItems = new TreeSet<>();

        for (int i = items.size(); i > 0; i--) {
            if (!itemIncluded[i][capacity]) continue;

            Item item = items.get(i - 1);
            takenItems.add(item);
            capacity -= item.getWeight();
        }

        System.out.printf("Total Weight: %d%n", takenItems.stream().map(Item::getWeight).reduce(0, Integer::sum));
        System.out.printf("Total Value: %d%n", takenItems.stream().map(Item::getValue).reduce(0, Integer::sum));
        takenItems.stream().map(Item::getName).forEach(System.out::println);
    }

}
