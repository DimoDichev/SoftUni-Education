import java.util.Arrays;
import java.util.Scanner;

public class SumWithUnlimitedCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] coins = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int target = Integer.parseInt(scanner.nextLine());

        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= target; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        System.out.println(dp[target]);
    }
}
