import java.util.Scanner;

public class Fibonacci {

    private static long[] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        dp = new long[num + 1];

        long sum = calculateFib(num);

        System.out.println(sum);
    }

    private static long calculateFib(int num) {
        if (num <= 1) {
            return num;
        }

        if (dp[num] != 0) {
            return dp[num];
        }

        return dp[num] = calculateFib(num - 1) + calculateFib(num - 2);
    }
}
