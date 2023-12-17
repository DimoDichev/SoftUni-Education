import java.util.Scanner;

public class CombinationsWithoutRepetition {

    public static String[] elementsArr;
    public static String[] combinationsArr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elementsArr = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());

        combinationsArr = new String[k];

        combination(0, 0);
    }

    private static void combination(int index, int start) {
        if (index == combinationsArr.length) {
            System.out.println(String.join(" ", combinationsArr));
            return;
        }

        for (int i = start; i < elementsArr.length; i++) {
            combinationsArr[index] = elementsArr[i];
            combination(index + 1, i + 1);
        }
    }
}
