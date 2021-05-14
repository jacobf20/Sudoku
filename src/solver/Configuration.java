package solver;

import java.util.Collection;

public interface Configuration {
    public boolean isGoal();
    public boolean isValid();
    public Collection<Configuration> getSuccessors();
}
