import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int result = calculateFactorial(n);

        System.out.println(result);
    }

    private static int calculateFactorial(int n) {
        if (n == 1) {
            return 1;
        }

        return n * calculateFactorial(n - 1);
    }
}
