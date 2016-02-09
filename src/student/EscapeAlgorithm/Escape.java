package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Escape {

    private EscapeState currentState;

    private Map<Long, DijVertex> graph;
    private DijVertex destination;
    private DijVertex source;

    public Escape(EscapeState state) {
        currentState = state;
        buildGraph();
    }

    private void buildGraph() {
        graph = new HashMap<>();
        Collection<Node> nodes = currentState.getVertices();
        for(Node n : nodes) {
            graph.put(n.getId(), new DijVertex(n));
        }
    }

    public void escapeMaze() {
        print();
    }

    public void print() {
        graph.forEach((id, v) -> System.out.println(v));
    }
}
