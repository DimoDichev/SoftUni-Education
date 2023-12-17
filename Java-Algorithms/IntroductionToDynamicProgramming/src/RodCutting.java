import java.util.Arrays;
import java.util.Scanner;

public class RodCutting {

    private static int[] bestPrices;
    private static int[] prices;
    private static int[] prev;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        prices = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = Integer.parseInt(scanner.nextLine());

        bestPrices = new int[length + 1];
        prev = new int[length + 1];

        int maxPrice = cutRope(length);

        System.out.println(maxPrice);
        printSolution(length);
    }

    private static int cutRope(int length) {
        if (length == 0) {
            return 0;
        }

        if (bestPrices[length] != 0) {
            return bestPrices[length];
        }

        int currentBest = bestPrices[length];

        for (int i = 1; i <= length; i++) {
            int localMax = Math.max(currentBest, prices[i] + cutRope(length - i));
            if (localMax > currentBest) {
                currentBest = localMax;
                bestPrices[length] = currentBest;
                prev[length] = i;
            }
        }
        return bestPrices[length];
    }

    private static void printSolution(int length) {
        while (length - prev[length] != 0) {
            System.out.print(prev[length] + " ");
            length = length - prev[length];
        }
        System.out.println(prev[length]);
    }
}

