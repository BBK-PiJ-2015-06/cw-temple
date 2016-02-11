package student;

public class DijVertex <T> {

    private T t;
    private int order = -1;
    private int workingValue = -1;
    private int finalValue = -1;

    public DijVertex(T t) {
        this.t = t;
    }

    public T getNode() {
        return t;
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

}