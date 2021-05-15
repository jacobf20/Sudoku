package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SudokuConfig implements Configuration {

    public static final String VERT_DIVIDE = "|";
    public static final String HORI_DIVIDE = "-";

    private final String[][] board;
    private ArrayList<String[][]> subSections;

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
        this.subSections = constructSubSections();
    }

    public Collection<Configuration> getSuccessors() {return null;}
    public boolean isValid() {return false;}
    public boolean isGoal() {return false;}

    private ArrayList<String[][]> constructSubSections(){
        ArrayList<String[][]> subSections = new ArrayList<>();
        String[][] subSection1 = new String[3][3];
        String[][] subSection2 = new String[3][3];
        String[][] subSection3 = new String[3][3];
        String[][] subSection4 = new String[3][3];
        String[][] subSection5 = new String[3][3];
        String[][] subSection6 = new String[3][3];
        String[][] subSection7 = new String[3][3];
        String[][] subSection8 = new String[3][3];
        String[][] subSection9 = new String[3][3];
        for (int row = 0; row < 3; ++row) {
            System.arraycopy(this.board[row], 0, subSection1[row], 0, 3);
            System.arraycopy(this.board[row], 3, subSection2[row], 0, 3);
            System.arraycopy(this.board[row], 6, subSection3[row], 0, 3);
            System.arraycopy(this.board[row + 3], 0, subSection4[row], 0, 3);
            System.arraycopy(this.board[row + 3], 3, subSection5[row], 0, 3);
            System.arraycopy(this.board[row+3], 6, subSection6[row], 0, 3);
            System.arraycopy(this.board[row+6], 0, subSection7[row], 0, 3);
            System.arraycopy(this.board[row+6], 3, subSection8[row], 0, 3);
            System.arraycopy(this.board[row+6], 6, subSection9[row], 0, 3);
        }
        subSections.add(subSection1);
        subSections.add(subSection2);
        subSections.add(subSection3);
        subSections.add(subSection4);
        subSections.add(subSection5);
        subSections.add(subSection6);
        subSections.add(subSection7);
        subSections.add(subSection8);
        subSections.add(subSection9);
        return subSections;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            for (int row = 3*i; row < 3*(i+1); ++row) {
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
