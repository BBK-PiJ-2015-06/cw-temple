package student.EscapeAlgorithm;

import game.Node;

public class DijVertex {

    private Node node;
    private int order;
    private int workingValue;
    private int finalValue;

    public DijVertex(Node node) {
        this.node = node;
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
}