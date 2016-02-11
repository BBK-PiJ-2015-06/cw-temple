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


    public Explore(ExplorationState state) {
        this.state = state;
        wholeGraph = new HashMap<>();
    }

    public void findOrb() {
        moveOffEntrance();
    }

    private void moveOffEntrance() {
        List<NodeStatus> neighbours = new ArrayList<>();
        neighbours.addAll(state.getNeighbours());
        NodeStatus destination = neighbours.get(0);
        addGraphNode(destination);
        state.moveTo(destination.getId());
        markAsVisited(wholeGraph.get(destination.getId()));
    }

    private void markAsVisited(GraphNode graphNode) {
        graphNode.setVisited(true);
    }

    private void addGraphNode(NodeStatus nodeStatus) {
        GraphNode node = new GraphNode(nodeStatus);
        wholeGraph.put(nodeStatus.getId(), node);
    }
}
