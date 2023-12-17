import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LongestIncreasingSubsequence {

    private static int[] sequence;
    private static int[] length;
    private static int[] prev;
    private static int maxLength = 0;
    private static int bestIndex = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        sequence = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        length = new int[sequence.length];
        prev = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            int bestLength = 1;
            int index = -1;

            for (int j = i - 1; j >= 0; j--) {
                if (bestLength <= length[j] + 1 && sequence[j] < sequence[i]) {
                    bestLength = length[j] + 1;
                    index = j;
                }
            }

            length[i] = bestLength;

            if (bestLength > maxLength) {
                maxLength = bestLength;
                bestIndex = i;
            }

            prev[i] = index;
        }

        List<Integer> LIS = new ArrayList<>();

        while (bestIndex != -1) {
            LIS.add(sequence[bestIndex]);
            bestIndex = prev[bestIndex];
        }

        for (int i = LIS.size() - 1; i >= 0 ; i--) {
            System.out.print(LIS.get(i) + " ");
        }
    }
}
