import solver.Configuration;
import solver.Solver;
import solver.SudokuConfig;

import java.io.IOException;
import java.util.Optional;

public class Sudoku {
    public static void main (String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Sudoku filename");
        } else {
            try {
                SudokuConfig init = new SudokuConfig(args[0]);
                System.out.println(init.toString());
                Solver sol = new Solver();
                Optional<Configuration> result = sol.solve(init);
                if (result.isPresent()) {
                    System.out.println("Solution:\n" + result.get());
                } else {
                    System.out.println("No Solution.");
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}
