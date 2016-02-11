package student;

import game.NodeStatus;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private NodeStatus node;
    private boolean visited = false;
    private List<GraphNode> neighbours;

    public GraphNode(NodeStatus node) {
        this.node = node;
        neighbours = new ArrayList<>();
    }

    public NodeStatus getNode() {
        return node;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean b) {
        visited = b;
    }

    public List<GraphNode> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<GraphNode> neighbours) {
        this.neighbours = neighbours;
    }
}
