import java.util.Scanner;

public class ReverseArray {

    public static String[] array;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        array = scanner.nextLine().split("\\s+");

        printReverseArray(array.length - 1);
    }

    private static void printReverseArray(int index) {
        if (index < 0) {
            return;
        }

        System.out.print(array[index] + " ");

        printReverseArray(index - 1);
    }
}
