import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitcoinMiners {

    private static int[] pool;
    private static int[] combinationArr;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int transactionCount = Integer.parseInt(reader.readLine());
        int transactionSlot = Integer.parseInt(reader.readLine());

        pool = new int[transactionCount];
        combinationArr = new int[transactionSlot];

        combine(0, 0);

        System.out.println(count);
    }

    private static void combine(int index, int start) {
        if (index == combinationArr.length) {
            count++;
            return;
        }

        for (int i = start; i < pool.length ; i++) {
            combinationArr[index] = pool[i];
            combine(index + 1, i + 1);
        }
    }
}
