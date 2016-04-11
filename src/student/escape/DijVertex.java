package student.escape;

import game.Node;

/**
 * This class represents the vertex used by the ShortestPathFinder to compute
 * a route that takes the least amount of time between two nodes during the
 * escape phase of the game. It acts as a wrapper for the game Node, as well as
 * including additional fields to help with the implementation of Dijkstra's
 * algorithm.
 */
public class DijVertex {

    private Node node;
    private int order = -1;
    private int workingValue = -1;
    private int finalValue = -1;

    /**
     * Creates a DijVertex using a Node object.
     * @param node the Node object that is to be wrapped within this DijVertex
     */
    public DijVertex(Node node) {
        this.node = node;
    }

    /**
     * Returns the Node object of this vertex
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Sets the order field of this DijVertex.
     * @param order the integer that this order will be set to
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Returns the order label of this DijVertex.
     * @return the order that this object was computed by the ShortestPathFinder
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the workingValue field of this DijVertex.
     * @param newValue the integer that this workingValue will be reset to
     */
    public void setWorkingValue(int newValue) {
        if(workingValue == -1 || newValue < workingValue) {
            workingValue = newValue;
        }
    }

    /**
     * Returns the current working value of this DijVertex.
     * @return the value that this vertex currently has as a distance measure
     */
    public int getWorkingValue() {
        return workingValue;
    }

    /**
     * Sets the finalValue field of this DijVertex.
     * @param finalValue the integer that this finalValue will be set to
     */
    public void setFinalValue(int finalValue) {
        this.finalValue = finalValue;
    }

    /**
     * Returns the final value of this DijVertex.
     * @return the value that this vertex finally represents as a distance measure
     */
    public int getFinalValue() {
        return finalValue;
    }

}