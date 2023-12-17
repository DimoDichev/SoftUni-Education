import java.util.Scanner;

public class PermutationsWithoutRepetitionSwap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] elements = scanner.nextLine().split("\\s+");

        permute(elements, 0);
    }

    private static void permute(String[] elements, int index) {
        if (index == elements.length) {
            printElements(elements);
        } else {
            permute(elements, index + 1);
            for (int i = index + 1; i < elements.length; i++) {
                swap(elements, index, i);
                permute(elements, index + 1);
                swap(elements, index, i);
            }
        }
    }

    private static void swap(String[] elements, int index, int i) {
        String temp = elements[index];
        elements[index] = elements[i];
        elements[i] = temp;
    }

    private static void printElements(String[] elements) {
        System.out.println(String.join(" ", elements));
    }
}
