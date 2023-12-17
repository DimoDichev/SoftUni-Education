import java.util.Scanner;

public class CombinationsWithRepetition {

    public static String[] elementsArr;
    public static String[] combinationsArr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elementsArr = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());

        combinationsArr = new String[k];

        combinations(0, 0);
    }

    private static void combinations(int index, int start) {
        if (index == combinationsArr.length) {
            System.out.println(String.join(" ", combinationsArr));
            return;
        }

        for (int i = start; i < elementsArr.length; i++) {
            combinationsArr[index] = elementsArr[i];
            combinations(index + 1, i);
        }
    }
}
