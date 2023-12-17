import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int output = arraySum(arr, 0);

        System.out.println(output);
    }

    private static int arraySum(int[] arr, int startIndex) {
        if (startIndex >= arr.length) {
            return 0;
        }

        return arr[startIndex] + arraySum(arr, startIndex + 1);
    }
}
