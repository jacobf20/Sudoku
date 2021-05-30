package solver;

import java.util.Optional;

public class Solver {

    private long numConfigs;

    public Solver() {
        this.numConfigs = 0;
    }

    public Optional<Configuration> solve(Configuration config) {
        if (config.isGoal()) {
            return Optional.of(config);
        } else {
            for (Configuration child : config.getSuccessors()) {
                ++this.numConfigs;
                if (child.isValid()) {
                    System.out.println("\tValid Successor:\n" + child);
                    Optional<Configuration> sol = solve(child);
                    if (sol.isPresent()) {
                        return sol;
                    }
                }
                System.out.println("\tInvalid Successor:\n" + child);
            }
        }
        return Optional.empty();
    }

    public long getNumConfigs() {return this.numConfigs;}
}
