package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExploreAlgorithm1 implements ExploreAlgorithm{

    private ExplorationState state;
    private List<Long> deadEnds;
    private List<Long> traversedNodes;

    public ExploreAlgorithm1(ExplorationState state) {
        this.state = state;
        this.deadEnds = new ArrayList<>();
        this.traversedNodes = new ArrayList<>();
    }

    @Override
    public void execute() {
        while(state.getDistanceToTarget() != 0) {
        }

    }

}
