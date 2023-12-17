import java.util.Scanner;

public class BinomialCoefficients {

    private static long[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = Integer.parseInt(scanner.nextLine());
        int col = Integer.parseInt(scanner.nextLine());

        dp = new long[row + 1][col + 1];

        long result = binom(row, col);

        System.out.println(result);
    }

    private static long binom(int row, int col) {
        if (col == 0 || col == row) {
           return 1;
        }

        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        return dp[row][col] = binom(row - 1, col - 1) + binom(row - 1, col);
    }
}
