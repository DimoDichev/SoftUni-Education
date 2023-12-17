import java.util.Arrays;
import java.util.Scanner;

public class Trains {

    private static double[] arrival;
    private static double[] departure;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        arrival = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();

        departure = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();

        System.out.println(calculatePlatforms());
    }

    private static int calculatePlatforms() {
        int arrivalCount = 0;
        int departureCount = 0;
        int maxPlatformCount = 0;
        int currentPlatformCount = 0;

        while (arrival.length > arrivalCount) {
            if (arrival[arrivalCount] < departure[departureCount]) {
                arrivalCount++;
                currentPlatformCount++;
            } else {
                departureCount++;
                currentPlatformCount--;
            }

            maxPlatformCount = Math.max(maxPlatformCount, currentPlatformCount);
        }

        return maxPlatformCount;
    }
}
