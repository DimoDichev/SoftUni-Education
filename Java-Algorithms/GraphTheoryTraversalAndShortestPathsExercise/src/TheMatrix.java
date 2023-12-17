import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TheMatrix {

    private static char[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("\\s+");

        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] chars = reader.readLine().split("\\s+");
            for (int col = 0; col < chars.length; col++) {
                matrix[row][col] = chars[col].charAt(0);
            }
        }

        char fillChar = reader.readLine().charAt(0);

        String[] coordinates = reader.readLine().split("\\s+");
        int startRow = Integer.parseInt(coordinates[0]);
        int startCol = Integer.parseInt(coordinates[1]);

        replaceMatrixChar(startRow, startCol, matrix[startRow][startCol], fillChar);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static void replaceMatrixChar(int row, int col, char currentChar, char fillChar) {
        if (isOutOfBounds(row, col) || matrix[row][col] != currentChar) {
            return;
        }

        matrix[row][col] = fillChar;

        replaceMatrixChar(row, col + 1, currentChar, fillChar);
        replaceMatrixChar(row, col - 1, currentChar, fillChar);
        replaceMatrixChar(row + 1, col, currentChar, fillChar);
        replaceMatrixChar(row - 1, col, currentChar, fillChar);

    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
