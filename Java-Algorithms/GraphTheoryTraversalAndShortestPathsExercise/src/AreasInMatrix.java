import java.util.*;

public class AreasInMatrix {

    public static char[][] matrix;
    public static boolean[][] visited;
    public static Map<Character, Integer> areas = new TreeMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        matrix = new char[n][];
        visited = new boolean[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
            visited[i] = new boolean[matrix[i].length];
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (!visited[row][col]) {
                    areas.putIfAbsent(matrix[row][col], 0);
                    areas.put(matrix[row][col], areas.get(matrix[row][col]) + 1);
                    dfs(row, col, matrix[row][col]);
                }
            }
        }

        int areasCount = areas.values().stream().mapToInt(s -> s).sum();
        System.out.println("Areas: " + areasCount);

        for (Map.Entry<Character, Integer> area : areas.entrySet()) {
            System.out.printf("Letter '%s' -> %d%n", area.getKey(), area.getValue());
        }

    }

    private static void dfs(int row, int col, char symbol) {
        visited[row][col] = true;

        if (isInBounce(row, col + 1) && !visited[row][col + 1] && matrix[row][col + 1] == symbol) {
            dfs(row, col + 1, symbol);
        }
        if (isInBounce(row, col - 1) && !visited[row][col - 1] && matrix[row][col - 1] == symbol) {
            dfs(row, col - 1, symbol);
        }
        if (isInBounce(row + 1, col) && !visited[row + 1][col] && matrix[row + 1][col] == symbol) {
            dfs(row + 1, col, symbol);
        }
        if (isInBounce(row -1, col) && !visited[row - 1][col] && matrix[row - 1][col] == symbol) {
            dfs(row - 1, col, symbol);
        }
    }

    private static boolean isInBounce(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

}
