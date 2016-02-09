package student.EscapeAlgorithm;

import game.Edge;
import game.EscapeState;
import game.Node;

import java.util.*;

public class Escape {

    private EscapeState currentState;
    private Map<Long, DijVertex> graph;
    private Map<Long, DijVertex> workingVertices;
    private Map<Long, DijVertex> labelledVertices;
    private DijVertex destination;
    private int currentOrder = 1;

    public Escape(EscapeState state) {
        currentState = state;
        buildGraph();
        destination = graph.get(currentState.getExit().getId());
    }

    private void buildGraph() {
        graph = new HashMap<>();
        workingVertices = new HashMap<>();
        labelledVertices = new HashMap<>();
        Collection<Node> nodes = currentState.getVertices();
        for(Node n : nodes) {
            graph.put(n.getId(), new DijVertex(n));
        }
    }

    public void escapeMaze() {
        Stack<DijVertex> test = getShortestPathFrom(currentState);
        print();
    }

    private Stack<DijVertex> getShortestPathFrom(EscapeState state) {
        DijVertex currentVertex = graph.get(state.getCurrentNode().getId());
        label(currentVertex);
        List<Long> neighbours = getNeighbours(currentVertex);
        assignWorkingValues(currentVertex, neighbours);

        return null;
    }

    private void label(DijVertex v) {
        if(v.getWorkingValue() == -1) {
            v.setFinalValue(0);
        } else {
            v.setFinalValue(v.getWorkingValue());
        }
        v.setOrder(currentOrder);
        currentOrder++;
        labelledVertices.put(v.getNode().getId(), v);
        graph.remove(v.getNode().getId());
    }

    private List<Long> getNeighbours(DijVertex v) {
        Set<Edge> edges = v.getNode().getExits();
        List<Long> neighbours = new ArrayList<>();
        for(Edge e : edges) {
            neighbours.add(e.getOther(v.getNode()).getId());
        }
        return neighbours;

    }

    private void assignWorkingValues(DijVertex v, List<Long> neighbours) {
        for(Long l : neighbours) {
            DijVertex neighbour = graph.get(l);
            if(neighbour.getOrder() == -1) {
                neighbour.setWorkingValue(v.getFinalValue() + v.getNode().getEdge(neighbour.getNode()).length());
                workingVertices.put(l, neighbour);
                graph.remove(l);
            }

        }
    }

    public void print() {
        System.out.println("Entire graph: ");
        graph.forEach((id, v) -> System.out.println(v));

        System.out.println("Labeled vertices: ");
        labelledVertices.forEach((id, v) -> System.out.println(v));

        System.out.println("Working vertices: ");
        workingVertices.forEach((id, v) -> System.out.println(v));
    }
}
