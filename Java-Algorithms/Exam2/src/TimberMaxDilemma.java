import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TimberMaxDilemma {

    public static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Item> items = new ArrayList<>();

        int[] prices = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = Integer.parseInt(reader.readLine());

        for (int i = 0; i < prices.length; i++) {
            items.add(new Item(i + 1, prices[i]));
        }

        int[][] maxValue = new int[items.size() + 1][length + 1];
        boolean[][] itemIncluded = new boolean[items.size() + 1][length + 1];

        for (int itemRow = 1; itemRow <= items.size(); itemRow++) {
            Item item = items.get(itemRow - 1);

            for (int capacityCol = 0; capacityCol <= length; capacityCol++) {
                int excluded = maxValue[itemRow - 1][capacityCol];

                if (capacityCol - item.weight < 0) {
                    maxValue[itemRow][capacityCol] = excluded;
                } else {
                    int included = maxValue[itemRow - 1][capacityCol - item.weight] + item.value;

                    if (excluded > included) {
                        maxValue[itemRow][capacityCol] = excluded;
                    } else {
                        maxValue[itemRow][capacityCol] = included;
                        itemIncluded[itemRow][capacityCol] = true;
                    }
                }
            }
        }

        int weight = length;

        int bestValue = maxValue[items.size()][length];

        while (maxValue[items.size()][weight - 1] == bestValue) {
            weight--;
        }

        Set<Item> takenItems = new HashSet<>();

        for (int i = items.size(); i > 0; i--) {
            if (!itemIncluded[i][length]) continue;

            Item item = items.get(i - 1);
            takenItems.add(item);
            length -= item.weight;
        }

        System.out.println(takenItems.stream().map(Item::getValue).reduce(0, Integer::sum));
    }
}
