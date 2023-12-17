import java.util.*;

public class MoveDownRight {

    private static int[][] matrix;
    private static int[][] sumMatrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        matrix = new int[rows][cols];
        sumMatrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        sumMatrix[0][0] = matrix[0][0];

        for (int row = 1; row < rows; row++) {
            sumMatrix[row][0] = sumMatrix[row - 1][0] + matrix[row][0];
        }

        for (int col = 1; col < cols; col++) {
            sumMatrix[0][col] = sumMatrix[0][col - 1] + matrix[0][col];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                sumMatrix[row][col] = Math.max(sumMatrix[row][col - 1] + matrix[row][col]
                        , sumMatrix[row - 1][col] + matrix[row][col]);

            }
        }

        List<String> path = new ArrayList<>();
        int row = rows - 1;
        int col = cols - 1;

        while (row > 0 || col > 0) {
            int top = -1;
            int left = -1;

            path.add(outputFormat(row, col));

            if (row > 0) {
                top = sumMatrix[row - 1][col];
            }

            if (col > 0) {
                left = sumMatrix[row][col - 1];
            }

            if (top > left) {
                row--;
            } else {
                col--;
            }
        }

        path.add(outputFormat(0, 0));

        Collections.reverse(path);
        System.out.println(String.join(" ", path));
    }

    private static String outputFormat(int row, int col) {
        return String.format("[" + row + ", " + col + "]");
    }
}
