import java.util.HashSet;
import java.util.Scanner;

public class PermutationsWithRepetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] elements = scanner.nextLine().split("\\s+");

        permuteWithRepetitions(elements, 0);
    }

    private static void permuteWithRepetitions(String[] elements, int index) {
        if (index >= elements.length) {
            printElements(elements);
        } else {
            permuteWithRepetitions(elements, index + 1);
            HashSet<String> swapped = new HashSet<>();
            swapped.add(elements[index]);
            for (int i = index + 1; i < elements.length; i++) {
                if (!swapped.contains(elements[i])) {
                    swap(elements, index, i);
                    permuteWithRepetitions(elements, index + 1);
                    swap(elements, index, i);
                    swapped.add(elements[i]);
                }
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
