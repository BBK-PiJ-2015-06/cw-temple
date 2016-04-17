package student.explore;

import java.util.Collection;
import java.util.HashSet;

/**
 * A PathVertex is used by the PathBuilder class to help compute the shortest path
 * between two NodeStatus objects within the maze during the exploration phase.
 * A GraphNode is converted into a PathVertex which provides additional fields to
 * be used in the algorithm.
 */
public class PathVertex {

    private final Collection<Long> neighbours;
    private Long id;
    private int orderLabel, finalLabel, workingLabel;

    /**
     * Builds a PathVertex object using a GraphNode. Initialises all integer fields
     * to -1.
     *
     * @param gn The GraphNode to be converted to a PathVertex.
     */
    public PathVertex(GraphNode gn) {
        id = gn.getId();
        neighbours = new HashSet<>();
        gn.getNeighbours().forEach(n -> neighbours.add(n.getId()));
        orderLabel = -1;
        finalLabel = -1;
        workingLabel = -1;
    }

    /**
     * Returns the label representing the ordering of this PathVertex
     *
     * @return the ordering label
     */
    public int getOrderLabel() {
        return orderLabel;
    }

    /**
     * Sets the orderLabel field.
     *
     * @param orderLabel the new integer that this orderLabel will be set to
     */
    public void setOrderLabel(int orderLabel) {
        this.orderLabel = orderLabel;
    }

    /**
     * Returns the ID of this PathVertex.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the finalLabel field of this PathVertex.
     *
     * @return the finalLabel
     */
    public int getFinalLabel() {
        return finalLabel;
    }

    /**
     * Sets the finalLabel field
     *
     * @param finalLabel the new integer that this finalLabel will be set to
     */
    public void setFinalLabel(int finalLabel) {
        this.finalLabel = finalLabel;
    }

    /**
     * Returns the workingLabel field of this PathVertex.
     *
     * @return the workingLabel
     */
    public int getWorkingLabel() {
        return workingLabel;
    }

    /**
     * Sets the workingLabel field
     *
     * @param workingLabel the new integer that this workingLabel will be set to
     */
    public void setWorkingLabel(int workingLabel) {
        this.workingLabel = workingLabel;
    }

    /**
     * Returns the neighbours of this PathVertex
     *
     * @return the neighbours
     */
    public Collection<Long> getNeighbours() {
        return neighbours;
    }

}
