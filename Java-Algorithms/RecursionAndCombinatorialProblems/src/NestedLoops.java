import java.util.Scanner;

public class NestedLoops {

    public static int[] array;
    public static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = Integer.parseInt(scanner.nextLine());

        array = new int[n];

        solve(0);
    }

    private static void solve(int index) {
        if (index == array.length) {
            print();
        } else {
            for (int i = 1; i <= n; i++) {
                array[index] =i;
                solve(index + 1);
            }
        }
    }

    private static void print() {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
