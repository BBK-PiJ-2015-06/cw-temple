package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExploreAlgorithm1 implements ExploreAlgorithm{

    private ExplorationState state;

    public ExploreAlgorithm1(ExplorationState state) {
        this.state = state;
    }

    @Override
    public void execute() {
        while(state.getDistanceToTarget() != 0) {
            List<NodeStatus> neighbours = new ArrayList<>();
            neighbours.addAll(state.getNeighbours());
            int max = neighbours.size();
            Random rn = new Random();
            state.moveTo(neighbours.get(rn.nextInt(max)).getId());
        }
    }
}
