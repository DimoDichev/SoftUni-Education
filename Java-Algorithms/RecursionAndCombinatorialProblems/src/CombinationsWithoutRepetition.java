import java.util.Scanner;

public class CombinationsWithoutRepetition {

    public static int[] elementsArr;
    public static int[] combinationsArr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nElements = Integer.parseInt(scanner.nextLine());
        int kSlots = Integer.parseInt(scanner.nextLine());

        elementsArr = new int[nElements];
        combinationsArr = new int[kSlots];

        for (int i = 0; i < elementsArr.length; i++) {
            elementsArr[i] = i + 1;
        }

        combinations(0, 0);
    }

    private static void combinations(int index, int start) {
        if (index == combinationsArr.length) {
            print();
        } else {
            for (int i = start; i < elementsArr.length; i++) {
                combinationsArr[index] = elementsArr[i];
                combinations(index + 1, i + 1);
            }
        }
    }

    private static void print() {
        for (int number : combinationsArr) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
