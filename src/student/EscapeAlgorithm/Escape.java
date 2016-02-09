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
    private DijVertex source;
    private DijVertex destination;
    private int currentOrder = 1;

    public Escape(EscapeState state) {
        currentState = state;
        buildGraph();
        source = graph.get(currentState.getCurrentNode().getId());
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
        Stack<DijVertex> route = getShortestPathFrom(currentState);
        followPath(route);
    }

    private Stack<DijVertex> getShortestPathFrom(EscapeState state) {
        DijVertex currentVertex = source;
        label(currentVertex);
        while(currentVertex.getNode().getId() != destination.getNode().getId()) {
            List<Long> neighbours = getNeighbours(currentVertex);
            assignWorkingValues(currentVertex, neighbours);
            currentVertex = smallestWorkingValue();
            label(currentVertex);
            System.out.println(currentVertex);
        }
        Stack<DijVertex> route = traceRoute();
        return route;
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
        if(graph.containsKey(v.getNode().getId())) {
            graph.remove(v.getNode().getId());
        } else {
            workingVertices.remove(v.getNode().getId());
        }

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
            DijVertex neighbour;
            if(labelledVertices.containsKey(l)) {
                neighbour = labelledVertices.get(l);
            } else if(workingVertices.containsKey(l)) {
                neighbour = workingVertices.get(l);
            } else {
                neighbour = graph.get(l);
            }
            if(neighbour.getOrder() == -1) {
                neighbour.setWorkingValue(v.getFinalValue() + v.getNode().getEdge(neighbour.getNode()).length());
                workingVertices.put(l, neighbour);
                graph.remove(l);
            }

        }
    }

    private DijVertex smallestWorkingValue() {
        List<DijVertex> list = new ArrayList<>(workingVertices.values());
        list.sort((v1, v2) -> v1.getWorkingValue() - v2.getWorkingValue());
        return list.get(0);
    }

    private Stack<DijVertex> traceRoute() {
        Stack<DijVertex> route = new Stack<>();
        route.push(destination);
        DijVertex temp = destination;
        while(!temp.equals(source)) {
            List<Long> neighbours = getNeighbours(temp);
            List<DijVertex> vToStack = new ArrayList<>();
            for(Long l : neighbours) {
                if(labelledVertices.containsKey(l)) {
                    DijVertex newTemp = labelledVertices.get(l);
                    if(newTemp.getFinalValue() + temp.getNode().getEdge(newTemp.getNode()).length() == temp.getFinalValue()) {
                        vToStack.add(newTemp);
                    }
                }
            }
            route.push(vToStack.get(0));
            temp = vToStack.get(0);
        }
        return route;
    }

    private void followPath(Stack<DijVertex> path) {
        path.pop();
        while (!path.empty()) {
            Node nextNode = path.pop().getNode();
            currentState.moveTo(nextNode);
            if(nextNode.getTile().getGold() > 0) {
                currentState.pickUpGold();
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
