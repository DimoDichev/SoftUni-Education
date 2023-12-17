import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathsInLabyrinth {

    public static List<Character> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        char[][] labyrinth = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            labyrinth[i] = scanner.nextLine().toCharArray();
        }

        findPaths(labyrinth, 0, 0, ' ');

    }

    private static void findPaths(char[][] labyrinth, int row, int col, char direction) {
        if (!isInBounds(labyrinth, row, col) || labyrinth[row][col] == 'v' || labyrinth[row][col] == '*') {
            return;
        }

        path.add(direction);

        if (labyrinth[row][col] == 'e') {
            printPath();
            path.remove(path.size() - 1);
            return;
        }

        labyrinth[row][col] = 'v';

        findPaths(labyrinth, row, col + 1, 'R');
        findPaths(labyrinth, row + 1, col, 'D');
        findPaths(labyrinth, row, col - 1, 'L');
        findPaths(labyrinth, row - 1, col, 'U');

        labyrinth[row][col] = '-';
        path.remove(path.size() - 1);
    }

    private static void printPath() {
        for (int i = 1; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
        System.out.println();
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int col) {
        return row >= 0 && row < labyrinth.length && col >= 0 && col < labyrinth[row].length;
    }
}
