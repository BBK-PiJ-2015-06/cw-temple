package student;

import game.NodeStatus;

import java.util.ArrayList;
import java.util.List;

public class ExploreNode implements GraphNode {

    private NodeStatus node;
    private boolean visited = false;
    private List<GraphNode> neighbours;

    public ExploreNode(NodeStatus node) {
        this.node = node;
        neighbours = new ArrayList<>();
    }

    public ExploreNode() {
        node = null;
        visited = true;
    }

    @Override
    public NodeStatus getNode() {
        return node;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean b) {
        visited = b;
    }

    @Override
    public List<GraphNode> getNeighbours() {
        return neighbours;
    }

    @Override
    public void addNeighbour(GraphNode graphNode) {
        neighbours.add(graphNode);
    }
}
