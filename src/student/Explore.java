package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.List;

public class Explore {

    private ExplorationState state;

    public Explore(ExplorationState state) {
        this.state = state;
    }

    public void findOrb() {
        moveOffEntrance();
    }

    private void moveOffEntrance() {
        List<NodeStatus> neighbours = new ArrayList<>();
        neighbours.addAll(state.getNeighbours());
        NodeStatus destination = neighbours.get(0);
        state.moveTo(destination.getId());
    }
}
