import java.util.Scanner;

public class Generating01Vectors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[] arr = new int[n];

        printVector(arr, 0);

    }

    private static void printVector(int[] vector, int index) {
        if (index >= vector.length) {
            printVector(vector);
            return;
        }

        for (int i = 0; i <= 1; i++) {
            vector[index] = i;
            printVector(vector, index + 1);
        }
    }

    private static void printVector(int[] vector) {
        for (int n : vector) {
            System.out.print(n);
        }
        System.out.println();
    }
}
