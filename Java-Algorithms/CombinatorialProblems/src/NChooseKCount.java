import java.util.Scanner;

public class NChooseKCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        int result = binom(n, k);

        System.out.println(result);
    }

    private static int binom(int n, int k) {
        if (k > n) {
            return 0;
        }

        if (k == n || k == 0) {
            return 1;
        }

        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
