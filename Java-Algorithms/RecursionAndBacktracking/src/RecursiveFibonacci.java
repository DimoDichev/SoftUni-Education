import java.util.Scanner;

public class RecursiveFibonacci {
    public static long[] memory = new long[500];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        long result = getFibonacci(n);

        System.out.println(result);
    }

    private static long getFibonacci(int n) {
        if (n <= 1) {
            return 1;
        } else if (memory[n] != 0) {
            return memory[n];
        }

        long fibValue = getFibonacci(n - 1) + getFibonacci(n - 2);
        memory[n] = fibValue;

        return fibValue;
    }
}
