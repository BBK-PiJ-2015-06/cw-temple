package student.EscapeAlgorithm;

import game.Node;

import java.util.ArrayList;
import java.util.List;

public class DijVertex {

    private Node node;
    private int order;
    private int workingValue;
    private int finalValue;

    private List<DijVertex> neighbours;

    public DijVertex(Node node) {
        this.node = node;
        neighbours = new ArrayList<>();
    }

    public Node getNode() {
        return node;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public void setWorkingValue(int newValue) {
        workingValue = newValue;
    }

    public int getWorkingValue() {
        return workingValue;
    }

    public void setFinalValue(int finalValue) {
        this.finalValue = finalValue;
    }

    public int getFinalValue() {
        return finalValue;
    }

    public void addNeighbours(List<Node> neighbours) {
        for(Node n : neighbours) {
            this.neighbours.add(new DijVertex(n));
        }
    }

    public List<DijVertex> getNeighbours() {
        return neighbours;
    }

    public int getEdgeWeight(DijVertex neighbour) {
        return this.node.getEdge(neighbour.getNode()).length();
    }
}