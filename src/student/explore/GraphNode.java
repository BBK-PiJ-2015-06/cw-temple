package student.explore;

import game.NodeStatus;
import java.util.Collection;

public class GraphNode {

    private Long id;
    private Collection<NodeStatus> neighbours;

    public GraphNode(Long id, Collection<NodeStatus> neighbours) {
        this.id = id;
        this.neighbours = neighbours;
    }

    public Long getId() {
        return id;
    }

    public Collection<NodeStatus> getNeighbours() {
        return neighbours;
    }
}
