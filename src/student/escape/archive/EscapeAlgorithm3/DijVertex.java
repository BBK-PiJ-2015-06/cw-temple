/*
package student.escape.archive.EscapeAlgorithm3;

import game.Node;

public class DijVertex {

    private Node node;
    private int order = -1;
    private int workingValue = -1;
    private int finalValue = -1;

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
        if(workingValue == -1 || newValue < workingValue) {
            workingValue = newValue;
        }
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

    @Override
    public String toString() {
        return "Node Id: " + node.getId()
                + "\nOrder: " + order
                + "\nWorkingValue: " + workingValue
                + "\nFinalValue: " + finalValue
                + '\n';
    }
}
*/