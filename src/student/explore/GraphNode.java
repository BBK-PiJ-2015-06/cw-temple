package student.explore;

import game.NodeStatus;
import java.util.Collection;

/**
 * This class represents a basic node within the graph used by ExploreRunner.
 * It holds information about a tile's ID as, well as it's traversable neighbours.
 * GraphNodes are created during the explore phase as the sprite explores the maze,
 * building the graph gradually to enable the shortest path algorithm to work sufficiently.
 */
public class GraphNode {

    private Long id;
    private Collection<NodeStatus> neighbours;

    /**
     * Creates a GraphNode object
     * @param id the ID of the NodeStatus object represented by this GraphNode
     * @param neighbours a collection of NodeStatus objects that are neighbours of the node that the ID
     *                   corresponds to.
     */
    public GraphNode(Long id, Collection<NodeStatus> neighbours) {
        this.id = id;
        this.neighbours = neighbours;
    }

    /**
     * Returns the ID of this GraphNode
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the neighbours of this GraphNode
     * @return the neighbours
     */
    public Collection<NodeStatus> getNeighbours() {
        return neighbours;
    }
}
