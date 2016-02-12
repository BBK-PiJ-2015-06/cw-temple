package student;

import game.NodeStatus;

import java.util.List;

public interface GraphNode <T extends NodeStatus, Node> {

    T getNode();

    boolean isVisited();

    void setVisited(boolean b);

    List<GraphNode> getNeighbours();

    void addNeighbour(GraphNode graphNode);
}
