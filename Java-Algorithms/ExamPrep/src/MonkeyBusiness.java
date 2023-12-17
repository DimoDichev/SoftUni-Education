import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MonkeyBusiness {

    private static int[] numbers;
    private static int[] variation;
    private static int totalSolution = 0;
    private static StringBuilder outputLine = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        numbers = new int[n];
        variation = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        findSolutions(0, n);

        System.out.println(String.join(System.lineSeparator(), outputLine.toString().trim()));
        System.out.println("Total Solutions: " + totalSolution);
    }

    private static void findSolutions(int index, int end) {
        if (index >= end) {
            printSolution();
            return;
        }

        variation[index] = numbers[index];
        findSolutions(index + 1, end);

        variation[index] = -numbers[index];
        findSolutions(index + 1, end);
    }

    private static void printSolution() {
        int sum = Arrays.stream(variation).sum();

        if (sum == 0) {
            totalSolution++;

            for (int num : variation) {
                String output = num > 0 ? "+" + num : String.valueOf(num);
                outputLine.append(output).append(" ");
            }

            outputLine.append(System.lineSeparator());
        }
    }
}
