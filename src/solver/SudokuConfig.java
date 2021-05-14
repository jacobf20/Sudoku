package solver;

import java.util.Collection;

public class SudokuConfig implements Configuration {
    public Collection<Configuration> getSuccessors() {return null;}
    public boolean isValid() {return false;}
    public boolean isGoal() {return false;}
}
