package student.EscapeAlgorithm;

import game.Edge;
import game.EscapeState;
import game.Node;

import java.util.*;

public class ShortestPathFinder {

    private EscapeState state;
    private Map<Long, DijVertex> graph;
    private Map<Long, DijVertex> workingVertices;
    private Map<Long, DijVertex> labelledVertices;
    private DijVertex source;
    private DijVertex destination;
    private int currentOrder = 1;

    private int distance;
    private Stack<DijVertex> path;

    public ShortestPathFinder(EscapeState state, Node start, Node end) {
        this.state = state;
        rebuildGraph();
        source = graph.get(start.getId());
        destination = graph.get(end.getId());
        calculatePath();
    }

    private void rebuildGraph() {
        graph = new HashMap<>();
        workingVertices = new HashMap<>();
        labelledVertices = new HashMap<>();
        Collection<Node> nodes = state.getVertices();
        for(Node n : nodes) {
            graph.put(n.getId(), new DijVertex(n));
        }
    }

    private void calculatePath() {
        DijVertex currentVertex = source;
        label(currentVertex);
        while(currentVertex.getNode().getId() != destination.getNode().getId()) {
            List<Long> neighbours = getNeighbours(currentVertex);
            assignWorkingValues(currentVertex, neighbours);
            currentVertex = smallestWorkingValue();
            label(currentVertex);
        }
        this.distance = currentVertex.getFinalValue();
        this.path = traceRoute();
    }

    public int getDistance() {
        return distance;
    }

    public Stack<DijVertex> getPath() {
        return path;
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

    @Override
    public String toString() {
        return "From node: " + source.getNode().getId()
                + "\nTo node: " + destination.getNode().getId()
                + "\nTime to follow: " + distance
                + "\n";
    }
}