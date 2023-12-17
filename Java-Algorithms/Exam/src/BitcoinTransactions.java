import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BitcoinTransactions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstArr = reader.readLine().split("\\s+");
        String[] secondArr = reader.readLine().split("\\s+");

        List<String> result = new ArrayList<>();
        int lastIndexOfSecondEl = -1;

        for (int i = 0; i < firstArr.length; i++) {
            String firstEl = firstArr[i];

            for (int j = 0; j < secondArr.length; j++) {
                String secondEl = secondArr[j];
                if (firstEl.equals(secondEl) && lastIndexOfSecondEl < j) {
                    result.add(firstEl);
                    lastIndexOfSecondEl = j;
                    break;
                }
            }
        }

        System.out.println("[" + String.join(" ", result) + "]");
    }
}
