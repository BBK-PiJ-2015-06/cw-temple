package student.escape;

import game.Edge;
import game.EscapeState;
import game.Node;

import java.util.*;

/**
 * This class uses an algorithm  to compute the shortest
 * path between two vertices given a map of the maze.
 */
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

    /**
     * Builds the shortest path object
     * @param state the current EscapeState of the sprite
     * @param start the starting vertex
     * @param end the destination vertex
     */
    public ShortestPathFinder(EscapeState state, Node start, Node end) {
        this.state = state;
        buildGraph();
        source = graph.get(start.getId());
        destination = graph.get(end.getId());
        calculatePath();
    }

    /**
     * Converts the collection of Nodes returned by the escape phase of
     * the coursework into a graph of DijVertices ready to perform the shortest
     * path algorithm upon.
     */
    private void buildGraph() {
        graph = new HashMap<>();
        workingVertices = new HashMap<>();
        labelledVertices = new HashMap<>();
        Collection<Node> nodes = state.getVertices();
        for(Node n : nodes) {
            graph.put(n.getId(), new DijVertex(n));
        }
    }

    /**
     * Calculates the shortest path between the start and end vertices.
     */
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

    /**
     * Returns the distance of the shortest path computed by this object.
     * @return the distance of the shortest path
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the shortest path as a stack.
     * @return the shortest path between then start and end vertices
     */
    public Stack<DijVertex> getPath() {
        return path;
    }

    /**
     * Labels a DijVertice's final value
     * @param v the vertex to be labelled
     */
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

    /**
     * Retrieves the neighbours of a DijVertex
     * @param v the DijVertex
     * @return the neighbours
     */
    private List<Long> getNeighbours(DijVertex v) {
        Set<Edge> edges = v.getNode().getExits();
        List<Long> neighbours = new ArrayList<>();
        for(Edge e : edges) {
            neighbours.add(e.getOther(v.getNode()).getId());
        }
        return neighbours;
    }

    /**
     * Updates the workingValue fields of all neighbours of the DijVertex.
     * @param v the DijVertex
     * @param neighbours the neighbours the vertex whose working values are to
     *                   be computed.
     */
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

    /**
     * Sorts the working values of all vertices computed and returns the smallest of these.
     * @return the DijVertex within the current graph with the smallest working value.
     */
    private DijVertex smallestWorkingValue() {
        List<DijVertex> list = new ArrayList<>(workingVertices.values());
        list.sort((v1, v2) -> v1.getWorkingValue() - v2.getWorkingValue());
        return list.get(0);
    }

    /**
     * Akin to the final stage of the shortest path algorithm, this method builds the path
     * stack from the destination to the current location.
     * @return the route to be taken
     */
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

}
