package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

public class SudokuConfig implements Configuration {

    public static final String VERT_DIVIDE = "|";
    public static final String HORI_DIVIDE = "-";

    private final String[][] board;

    public SudokuConfig(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        this.board = new String[9][9];
        String nextLine = in.readLine();
        int row = 0;
        while (nextLine != null) {
            String[] fields = nextLine.split("\\s+");
            System.arraycopy(fields, 0, this.board[row], 0, fields.length);
            ++row;
            nextLine = in.readLine();
        }
    }

    public Collection<Configuration> getSuccessors() {return null;}
    public boolean isValid() {return false;}
    public boolean isGoal() {return false;}

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            for (int row = 0; row < 3; ++row) {
                for (int col = 0; col < this.board[0].length; ++col) {
                    result.append(this.board[row][col]).append("\s");
                    if (col == 2 || col == 5) {
                        result.append(VERT_DIVIDE).append("\s");
                    }
                }
                result.append("\n");
            }
            for (int j = 0; j < 11; ++j) {
                result.append(HORI_DIVIDE).append("\s");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
