import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectedAreasInMatrix {

    public static char[][] matrix;
    public static List<Integer[]> areasInfo = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());

        matrix = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == '-') {
                    areasInfo.add(new Integer[3]);
                    areasInfo.get(areasInfo.size() - 1)[0] = r;
                    areasInfo.get(areasInfo.size() - 1)[1] = c;
                    areasInfo.get(areasInfo.size() - 1)[2] = 0;
                    matrixTraversal(r, c, matrix);
                }
            }
        }

        System.out.println("Total areas found: " + areasInfo.size());
        areasInfo.sort((f, s) -> s[2] - f[2]);
        for (int arr = 0; arr < areasInfo.size(); arr++) {
            System.out.printf("Area #%d at (%d, %d), size: %d%n"
                    , arr + 1, areasInfo.get(arr)[0], areasInfo.get(arr)[1], areasInfo.get(arr)[2]);
        }
    }

    private static void matrixTraversal(int r, int c, char[][] matrix) {
        if (isInBound(r, c, matrix) && matrix[r][c] == '-') {
            matrix[r][c] = 'V';
            areasInfo.get(areasInfo.size() - 1)[2] ++;
            matrixTraversal(r + 1, c, matrix);
            matrixTraversal(r, c + 1, matrix);
            matrixTraversal(r - 1, c, matrix);
            matrixTraversal(r, c - 1, matrix);
        }
    }

    private static boolean isInBound(int r, int c, char[][] matrix) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;
    }
}
