package implementations;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        traversMatrix(startRow, startCol);
    }

    private void traversMatrix(int row, int col) {
        if (outOfBounce(row, col) || this.matrix[row][col] != this.toBeReplaced) {
            return;
        }

        matrix[row][col] = fillChar;

        traversMatrix(row + 1, col);
        traversMatrix(row - 1, col);
        traversMatrix(row , col + 1);
        traversMatrix(row, col - 1);
    }

    private boolean outOfBounce(int row, int col) {
        return row < 0 || row >= this.matrix.length || col < 0 || col >= this.matrix[row].length;
    }

    public String toOutputString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[row].length; col++) {
                result.append(matrix[row][col]);
            }
            result.append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
