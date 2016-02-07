package student.ArchivedAttempts.ExploreAlgorithm1;

import game.ExplorationState;
import game.NodeStatus;
import student.ArchivedAttempts.ExploreAlgorithm2.ExploreAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExploreAlgorithm1 implements ExploreAlgorithm {

    private ExplorationState state;
    private List<NodeStatus> visitedNodes;
    private List<NodeStatus> currentNeighbours;

    public ExploreAlgorithm1(ExplorationState state) {
        this.state = state;
        visitedNodes = new ArrayList<>();
    }

    @Override
    public void execute() {
        while(state.getDistanceToTarget() != 0) {
            List<NodeStatus> neighbours = new ArrayList<>();
            neighbours.addAll(state.getNeighbours());

            currentNeighbours = new ArrayList<>();
            currentNeighbours.addAll(neighbours);

            for(NodeStatus n : currentNeighbours) {
                if(visitedNodes.contains(n)) {
                    neighbours.remove(n);
                }
            }
            int max = neighbours.size();
            Random rn = new Random();
            if(max != 0) {
                state.moveTo(neighbours.get(rn.nextInt(max)).getId());
            } else {
                state.moveTo(currentNeighbours.get(rn.nextInt(currentNeighbours.size())).getId());
            }
        }
    }
}
