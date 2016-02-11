package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Explore {

    private ExplorationState state;
    private Map<Long, GraphNode> wholeGraph;
    private Map<Long, GraphNode> visitedGraph;
    private Map<Long, GraphNode> notVisitedGraph;


    public Explore(ExplorationState state) {
        this.state = state;
        wholeGraph = new HashMap<>();
        visitedGraph = new HashMap<>();
        notVisitedGraph = new HashMap<>();
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
