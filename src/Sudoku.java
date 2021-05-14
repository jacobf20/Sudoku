import solver.SudokuConfig;

import java.io.IOException;

public class Sudoku {
    public static void main (String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Sudoku filename");
        } else {
            try {
                SudokuConfig init = new SudokuConfig(args[0]);
                System.out.println(init.toString());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}
