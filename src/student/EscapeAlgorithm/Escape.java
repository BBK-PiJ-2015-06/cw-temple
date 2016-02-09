package student.EscapeAlgorithm;

import game.Edge;
import game.EscapeState;
import game.Node;

import java.util.*;

public class Escape {

    private EscapeState currentState;
    private Map<Long, DijVertex> graph;
    private DijVertex destination;
    private int currentOrder = 0;

    public Escape(EscapeState state) {
        currentState = state;
        buildGraph();
        destination = graph.get(currentState.getExit().getId());
    }

    private void buildGraph() {
        graph = new HashMap<>();
        Collection<Node> nodes = currentState.getVertices();
        for(Node n : nodes) {
            graph.put(n.getId(), new DijVertex(n));
        }
    }

    public void escapeMaze() {
        Stack<DijVertex> test = getShortestPathFrom(currentState);
        print();
    }

    public Stack<DijVertex> getShortestPathFrom(EscapeState state) {
        DijVertex currentVertex = graph.get(state.getCurrentNode().getId());
        label(currentVertex);
        List<Long> neighbours = getNeighbours(currentVertex);
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
    }

    private List<Long> getNeighbours(DijVertex v) {
        Set<Edge> edges = v.getNode().getExits();
        List<Long> neighbours = new ArrayList<>();
        for(Edge e : edges) {
            neighbours.add(e.getOther(v.getNode()).getId());
        }
        return neighbours;

    }

    public void print() {
        graph.forEach((id, v) -> System.out.println(v));
    }
}
