import java.util.Scanner;

public class Stairs {

    private static long[] memory;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int steps = Integer.parseInt(scanner.nextLine());

        if (steps == 0) {
            System.out.println(0);
            return;
        }

        memory = new long[steps + 1];
        memory[1] = 1;
        memory[2] = 2;

        System.out.println(stairsClimberCount(steps));
    }

    private static long stairsClimberCount(int currentStep) {
        if (memory[currentStep] != 0) {
            return memory[currentStep];
        }

        return memory[currentStep] = stairsClimberCount(currentStep - 1) + stairsClimberCount(currentStep - 2);
    }
}
