import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int num = Integer.parseInt(reader.readLine());

        System.out.println(findIndex(arr, num));
    }

    public static int findIndex(int[] arr, int num) {
        int begin = 0;
        int end = arr.length - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;

            if (num > arr[mid]) {
                begin = mid + 1;
            } else if (num < arr[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
