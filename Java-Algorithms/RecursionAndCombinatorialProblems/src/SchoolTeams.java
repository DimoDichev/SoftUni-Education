import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SchoolTeams {

    public static String[] girls;
    public static String[] boys;
    public static String[] girlsSlots = new String[3];
    public static String[] boysSlots = new String[2];
    public static List<String> girlsCombination = new ArrayList<>();
    public static List<String> boysCombination = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        girls = scanner.readLine().split(", ");
        boys = scanner.readLine().split(", ");

        combinationsGirls(0, 0);
        combinationsBoys(0, 0);

        for (String girls : girlsCombination) {
            for (String boys : boysCombination) {
                System.out.println(girls + ", " + boys);
            }
        }
    }

    private static void combinationsGirls(int index, int start) {
        if (index == girlsSlots.length) {
            girlsCombination.add(String.join(", ", girlsSlots));
        } else {
            for (int i = start; i < girls.length; i++) {
                girlsSlots[index] = girls[i];
                combinationsGirls(index + 1, i + 1);
            }
        }
    }

    private static void combinationsBoys(int index, int start) {
        if (index == boysSlots.length) {
            boysCombination.add(String.join(", ", boysSlots));
        } else {
            for (int i = start; i < boys.length; i++) {
                boysSlots[index] = boys[i];
                combinationsBoys(index + 1, i + 1);
            }
        }
    }
}
