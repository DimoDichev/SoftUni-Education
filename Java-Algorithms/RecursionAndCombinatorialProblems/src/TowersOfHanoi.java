import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TowersOfHanoi {

    public static Deque<Integer> source = new ArrayDeque<>();
    public static Deque<Integer> destination = new ArrayDeque<>();
    public static Deque<Integer> spare = new ArrayDeque<>();
    public static StringBuilder output = new StringBuilder();
    public static int steps = 0;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int disks = Integer.parseInt(scanner.nextLine());

        for (int i = disks; i > 0 ; i--) {
            source.push(i);
        }

        printRods();

        solvePuzzle(disks, source, destination, spare);

        System.out.println(output.toString());
    }

    private static void solvePuzzle(int disks, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if (disks == 1) {
            destination.push(source.pop());
            output.append("Step #")
                    .append(++steps)
                    .append(": Moved disk")
                    .append(System.lineSeparator());
            printRods();
        } else {
            solvePuzzle(disks - 1, source, spare, destination);
            solvePuzzle(1, source, destination, spare);
            solvePuzzle(disks - 1, spare, destination, source);
        }
    }

    private static void printRods() {
        output.append("Source: ")
                .append(formatRods(source))
                .append(System.lineSeparator())
                .append("Destination: ")
                .append(formatRods(destination))
                .append(System.lineSeparator())
                .append("Spare: ")
                .append(formatRods(spare))
                .append(System.lineSeparator())
                .append(System.lineSeparator());
    }

    private static String formatRods(Deque<Integer> stack) {
        return stack.stream()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
