package student;

import game.NodeStatus;

import java.util.List;

public interface GraphNode {

    NodeStatus getNode();

    boolean isVisited();

    void setVisited(boolean b);

    List<GraphNode> getNeighbours();

    void addNeighbour(GraphNode graphNode);
}
