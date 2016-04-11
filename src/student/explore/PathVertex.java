package student.explore;

import java.util.Collection;
import java.util.HashSet;

public class PathVertex {

    private Long id;
    private final Collection<Long> neighbours;
    private int orderLabel, finalLabel, workingLabel;

    public PathVertex(GraphNode gn) {
        id = gn.getId();
        neighbours = new HashSet<>();
        gn.getNeighbours().forEach(n -> neighbours.add(n.getId()));
        orderLabel = -1;
        finalLabel = -1;
        workingLabel = -1;
    }

    public int getOrderLabel() {
        return orderLabel;
    }

    public void setOrderLabel(int orderLabel) {
        this.orderLabel = orderLabel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFinalLabel() {
        return finalLabel;
    }

    public void setFinalLabel(int finalLabel) {
        this.finalLabel = finalLabel;
    }

    public int getWorkingLabel() {
        return workingLabel;
    }

    public void setWorkingLabel(int workingLabel) {
        this.workingLabel = workingLabel;
    }

    public Collection<Long> getNeighbours() {
        return neighbours;
    }

}
