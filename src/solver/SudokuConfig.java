package solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SudokuConfig implements Configuration {

    public static final String VERT_DIVIDE = "|";
    public static final String HORI_DIVIDE = "-";

    private final String[][] board;
    private final ArrayList<String[][]> subSections;
    private int updatedNumber;
    private String[][] updatedSubSection;

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
        this.updatedNumber = 0;
        this.updatedSubSection = null;
    }

    public SudokuConfig(SudokuConfig current) {
        this.board = new String[9][9];
        for (int row = 0; row < 9; ++row) {
            System.arraycopy(current.board[row], 0, this.board[row], 0, 9);
        }
        this.subSections = new ArrayList<>();
        for (String[][] subSection : current.subSections) {
            String[][] newSubSection = new String[3][3];
            for (int row = 0; row < 3; ++row) {
                System.arraycopy(subSection[row], 0, newSubSection[row], 0, 3);
            }
            this.subSections.add(newSubSection);
        }
        this.updatedNumber = current.updatedNumber;
        this.updatedSubSection = current.updatedSubSection;
    }

    public Collection<Configuration> getSuccessors() {
    HashSet<Configuration> successors = new HashSet<>();
    Random rng = new Random();
    ArrayList<Integer> fillOrder = new ArrayList<>();
    for (int i = 0; i < 9; ++i) {
        fillOrder.add(i);
    }
    Collections.shuffle(fillOrder);
    while (!fillOrder.isEmpty()) {
        int index = fillOrder.remove(0);
        String[][] subSection = this.subSections.get(index);
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (subSection[row][col].equals(".")) {
                    ArrayList<Integer> numOrder = new ArrayList<>();
                    for (int i = 1; i < 10; ++i) {
                        numOrder.add(i);
                    }
                    Collections.shuffle(numOrder);
                    while (!numOrder.isEmpty()) {
                        int value = numOrder.remove(0);
                        SudokuConfig newConfig = new SudokuConfig(this);
                        newConfig.subSections.get(index)[row][col] = String.valueOf(value);
                        newConfig.updateConfig();
                        newConfig.updatedNumber = value;
                        newConfig.updatedSubSection = newConfig.subSections.get(index);
                        successors.add(newConfig);
                    }
                }
            }
        }
    }
    return successors;
    }

    public boolean isValid() {
        int oneCount = 0;
        int twoCount = 0;
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;
        int sevenCount = 0;
        int eightCount = 0;
        int nineCount = 0;
        for (String[][] subSection : this.subSections) {
            for (int row = 0; row < 3; ++row) {
                for (int col = 0; col < 3; ++col) {
                    switch (subSection[row][col]) {
                        case "1" -> ++oneCount;
                        case "2" -> ++twoCount;
                        case "3" -> ++threeCount;
                        case "4" -> ++fourCount;
                        case "5" -> ++fiveCount;
                        case "6" -> ++sixCount;
                        case "7" -> ++sevenCount;
                        case "8" -> ++eightCount;
                        case "9" -> ++nineCount;
                    }
                }
            }
            if (oneCount > 1 || twoCount > 1 || threeCount > 1 || fourCount > 1
                    || fiveCount > 1 || sixCount > 1 || sevenCount > 1 || eightCount > 1 || nineCount > 1) {
                return false;
            }
            oneCount = 0;
            twoCount = 0;
            threeCount = 0;
            fourCount = 0;
            fiveCount = 0;
            sixCount = 0;
            sevenCount = 0;
            eightCount = 0;
            nineCount = 0;
        }
        int oneRowCount = 0;
        int oneColCount = 0;
        int twoRowCount = 0;
        int twoColCount = 0;
        int threeRowCount = 0;
        int threeColCount = 0;
        int fourRowCount = 0;
        int fourColCount = 0;
        int fiveRowCount = 0;
        int fiveColCount = 0;
        int sixRowCount = 0;
        int sixColCount = 0;
        int sevenRowCount = 0;
        int sevenColCount = 0;
        int eightRowCount = 0;
        int eightColCount = 0;
        int nineRowCount = 0;
        int nineColCount = 0;
        for (int row = 0; row < 9; ++row) {
            for (int col = 0; col < 9; ++col) {
                switch (this.board[row][col]) {
                    case "1" -> ++oneColCount;
                    case "2" -> ++twoColCount;
                    case "3" -> ++threeColCount;
                    case "4" -> ++fourColCount;
                    case "5" -> ++fiveColCount;
                    case "6" -> ++sixColCount;
                    case "7" -> ++sevenColCount;
                    case "8" -> ++eightColCount;
                    case "9" -> ++nineColCount;
                }
            }
            if (oneColCount > 1 || twoColCount > 1 || threeColCount > 1 || fourColCount > 1
                    || fiveColCount > 1 || sixColCount > 1 || sevenColCount > 1 || eightColCount > 1 || nineColCount > 1) {
                return false;
            }
            oneColCount = 0;
            twoColCount = 0;
            threeColCount = 0;
            fourColCount = 0;
            fiveColCount = 0;
            sixColCount = 0;
            sevenColCount = 0;
            eightColCount = 0;
            nineColCount = 0;
        }
        for (int col = 0; col < 9; ++col) {
            for (int row = 0; row < 9; ++row) {
                switch (this.board[row][col]) {
                    case "1" -> ++oneRowCount;
                    case "2" -> ++twoRowCount;
                    case "3" -> ++threeRowCount;
                    case "4" -> ++fourRowCount;
                    case "5" -> ++fiveRowCount;
                    case "6" -> ++sixRowCount;
                    case "7" -> ++sevenRowCount;
                    case "8" -> ++eightRowCount;
                    case "9" -> ++nineRowCount;
                }
            }
            if (oneRowCount > 1 || twoRowCount > 1 || threeRowCount > 1 || fourRowCount > 1
                    || fiveRowCount > 1 || sixRowCount > 1 || sevenRowCount > 1 || eightRowCount > 1 || nineRowCount > 1) {
                return false;
            }
            oneRowCount = 0;
            twoRowCount = 0;
            threeRowCount = 0;
            fourRowCount = 0;
            fiveRowCount = 0;
            sixRowCount = 0;
            sevenRowCount = 0;
            eightRowCount = 0;
            nineRowCount = 0;
        }
        ArrayList<Integer> forcedRows = new ArrayList<>();
        int updatedRow = 0;
        if (!Arrays.deepEquals(this.updatedSubSection, null)) {
            for (int row = 0; row < 3; ++row) {
                for (int col = 0; col < 3; ++col) {
                    if (this.updatedSubSection[row][col].equals(String.valueOf(updatedNumber))) {
                        updatedRow = row;
                        break;
                    }
                }
            }
            switch (this.subSections.indexOf(updatedSubSection)) {
                case 0:
                    for (int i = 1; i < 3; ++i) {
                        int availRow = this.findAvailableRows(this.subSections.get(i), updatedNumber);
                        if (availRow != 0) {
                            forcedRows.add(availRow);
                        }
                    }
                    break;
                case 1:
                    int availRow0 = this.findAvailableRows(this.subSections.get(0), updatedNumber);
                    int availRow2 = this.findAvailableRows(this.subSections.get(2), updatedNumber);
                    if (availRow0 != 0) {
                        forcedRows.add(availRow0);
                    }
                    if (availRow2 != 0) {
                        forcedRows.add(availRow2);
                    }
                    break;
                case 2:
                    for (int i = 0; i < 2; ++i) {
                        int availRow = this.findAvailableRows(this.subSections.get(i), updatedNumber);
                        if (availRow != 0) {
                            forcedRows.add(availRow);
                        }
                    }
                    break;
                case 3:
                    for (int i = 4; i < 6; ++i) {
                        int availRow = this.findAvailableRows(this.subSections.get(i), updatedNumber);
                        if (availRow != 0) {
                            forcedRows.add(availRow);
                        }
                    }
                    break;
                case 4:
                    int availRow3 = this.findAvailableRows(this.subSections.get(3), updatedNumber);
                    int availRow5 = this.findAvailableRows(this.subSections.get(5), updatedNumber);
                    if (availRow3 != 0) {
                        forcedRows.add(availRow3);
                    }
                    if (availRow5 != 0) {
                        forcedRows.add(availRow5);
                    }
                    break;
                case 5:
                    for (int i = 3; i < 5; ++i) {
                        int availRow = this.findAvailableRows(this.subSections.get(i), updatedNumber);
                        if (availRow != 0) {
                            forcedRows.add(availRow);
                        }
                    }
                    break;
                case 6:
                    for (int i = 7; i < 9; ++i) {
                        int availRow = this.findAvailableRows(this.subSections.get(i), updatedNumber);
                        if (availRow != 0) {
                            forcedRows.add(availRow);
                        }
                    }
                    break;
                case 7:
                    int availRow6 = this.findAvailableRows(this.subSections.get(6), updatedNumber);
                    int availRow8 = this.findAvailableRows(this.subSections.get(8), updatedNumber);
                    if (availRow6 != 0) {
                        forcedRows.add(availRow6);
                    }
                    if (availRow8 != 0) {
                        forcedRows.add(availRow8);
                    }
                    break;
                case 8:
                    for (int i = 6; i < 8; ++i) {
                        int availRow = this.findAvailableRows(this.subSections.get(i), updatedNumber);
                        if (availRow != 0) {
                            forcedRows.add(availRow);
                        }
                    }
                    break;
            }
        }
        return !forcedRows.contains(updatedRow);
    }

    public boolean isGoal() {
        if (!this.isValid()) {
            return false;
        } else {
            for (int row = 0; row < 9; ++row) {
                for (int col = 0; col < 9; ++col) {
                    if (this.board[row][col].equals(".")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

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

    private void updateConfig () {
        int col = 0;
        for (int i = 0; i < 9; ++i) {
            for (int row = 0; row < 3; ++row) {
                if (i < 3) {
                    System.arraycopy(this.subSections.get(i)[row], 0, this.board[row], col, 3);
                } else if (i < 6) {
                    System.arraycopy(this.subSections.get(i)[row], 0, this.board[row+3], col, 3);
                } else {
                    System.arraycopy(this.subSections.get(i)[row], 0, this.board[row+6], col, 3);
                }
            }
            col = col + 3;
            if (col > 6) {
                col = 0;
            }
        }
    }

    private int findAvailableRows (String[][] subSection, int num) {
        TreeSet<Integer> unavailableRows = new TreeSet<>();
        switch (this.subSections.indexOf(subSection)) {
            case 0:
                for (int i = 1; i < 2; ++i) {
                    String[][] section = this.subSections.get(i);
                    for (int row = 0; row < 3; ++row) {
                        for (int col = 0; col < 3; ++col) {
                            if (section[row][col].equals(String.valueOf(num))) {
                                unavailableRows.add(row);
                            }
                        }
                    }
                }
                break;
            case 1:
                String[][] section0 = this.subSections.get(0);
                for (int row = 0; row < 3; ++row) {
                    for (int col = 0; col < 3; ++col) {
                        if (section0[row][col].equals(String.valueOf(num))) {
                            unavailableRows.add(row);
                        }
                    }
                }
                String[][] section2 = this.subSections.get(2);
                for (int row = 0; row < 3; ++row) {
                    for (int col = 0; col < 3; ++col) {
                        if (section2[row][col].equals(String.valueOf(num))) {
                            unavailableRows.add(row);
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 2; ++i) {
                    String[][] section = this.subSections.get(i);
                    for (int row = 0; row < 3; ++row) {
                        for (int col = 0; col < 3; ++col) {
                            if (section[row][col].equals(String.valueOf(num))) {
                                unavailableRows.add(row);
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 4; i < 6; ++i) {
                    String[][] section = this.subSections.get(i);
                    for (int row = 0; row < 3; ++row) {
                        for (int col = 0; col < 3; ++col) {
                            if (section[row][col].equals(String.valueOf(num))) {
                                unavailableRows.add(row);
                            }
                        }
                    }
                }
                break;
            case 4:
                String[][] section3 = this.subSections.get(3);
                for (int row = 0; row < 3; ++row) {
                    for (int col = 0; col < 3; ++col) {
                        if (section3[row][col].equals(String.valueOf(num))) {
                            unavailableRows.add(row);
                        }
                    }
                }
                String[][] section5 = this.subSections.get(5);
                for (int row = 0; row < 3; ++row) {
                    for (int col = 0; col < 3; ++col) {
                        if (section5[row][col].equals(String.valueOf(num))) {
                            unavailableRows.add(row);
                        }
                    }
                }
                break;
            case 5:
                for (int i = 3; i < 5; ++i) {
                    String[][] section = this.subSections.get(i);
                    for (int row = 0; row < 3; ++row) {
                        for (int col = 0; col < 3; ++col) {
                            if (section[row][col].equals(String.valueOf(num))) {
                                unavailableRows.add(row);
                            }
                        }
                    }
                }
                break;
            case 6:
                for (int i = 7; i < 9; ++i) {
                    String[][] section = this.subSections.get(i);
                    for (int row = 0; row < 3; ++row) {
                        for (int col = 0; col < 3; ++col) {
                            if (section[row][col].equals(String.valueOf(num))) {
                                unavailableRows.add(row);
                            }
                        }
                    }
                }
                break;
            case 7:
                String[][] section6 = this.subSections.get(6);
                for (int row = 0; row < 3; ++row) {
                    for (int col = 0; col < 3; ++col) {
                        if (section6[row][col].equals(String.valueOf(num))) {
                            unavailableRows.add(row);
                        }
                    }
                }
                String[][] section8 = this.subSections.get(8);
                for (int row = 0; row < 3; ++row) {
                    for (int col = 0; col < 3; ++col) {
                        if (section8[row][col].equals(String.valueOf(num))) {
                            unavailableRows.add(row);
                        }
                    }
                }
                break;
            case 8:
                for (int i = 6; i < 8; ++i) {
                    String[][] section = this.subSections.get(i);
                    for (int row = 0; row < 3; ++row) {
                        for (int col = 0; col < 3; ++col) {
                            if (section[row][col].equals(String.valueOf(num))) {
                                unavailableRows.add(row);
                            }
                        }
                    }
                }
                break;
        }
        if (!unavailableRows.contains(1) && unavailableRows.contains(2) && unavailableRows.contains(3)) {
            return 1;
        } else if (unavailableRows.contains(1) && !unavailableRows.contains(2) && unavailableRows.contains(3)) {
            return 2;
        } else if (unavailableRows.contains(1) && unavailableRows.contains(2) && !unavailableRows.contains(3)) {
            return 3;
        } else {
            return 0;
        }
    }

    public int hashCode() {
        return Arrays.deepHashCode(this.board);
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
