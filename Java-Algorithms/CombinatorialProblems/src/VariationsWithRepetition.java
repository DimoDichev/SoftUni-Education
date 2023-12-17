import java.util.Scanner;

public class VariationsWithRepetition {

    public static String[] elementsArr;
    public static String[] variationsArr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        elementsArr = scanner.nextLine().split("\\s+");
        int k = Integer.parseInt(scanner.nextLine());

        variationsArr = new String[k];

        variations(0);

    }

    private static void variations(int index) {
        if (index == variationsArr.length) {
            System.out.println(String.join(" ", variationsArr));
            return;
        }

        for (int i = 0; i < elementsArr.length; i++) {
            variationsArr[index] = elementsArr[i];
            variations(index + 1);
        }
    }
}
