package student;

import game.NodeStatus;

public class GraphNode {

    private NodeStatus node;
    private boolean visited = false;

    public GraphNode(NodeStatus node) {
        this.node = node;
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
}
