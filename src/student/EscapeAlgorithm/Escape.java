package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.*;

public class Escape {

    private EscapeState currentState;
    private Node source;
    private Node destination;
    private Collection<Node> vertices;
    private int timeRemaining;

    private List<PathStatus> possiblePaths;
    private List<PathStatus> deadEndpaths;
    private List<PathStatus> successfulPaths;
    private PriorityQueue<PathStatus> prioritisedPaths;

    public Escape(EscapeState state) {
        currentState = state;
        source = currentState.getCurrentNode();
        destination = currentState.getExit();
        vertices = currentState.getVertices();
        timeRemaining = currentState.getTimeRemaining();
        possiblePaths = new ArrayList<>();
        possiblePaths.add(new PathStatus(source));
        deadEndpaths = new ArrayList<>();
        successfulPaths = new ArrayList<>();
        prioritisedPaths = new PriorityQueueImpl<>();
    }

    public void findExit() {
        while(!possiblePaths.isEmpty()) {
            possiblePaths = populatePaths(possiblePaths);
            for(PathStatus ps : successfulPaths) {
                if(possiblePaths.contains(ps)) {
                    possiblePaths.remove(ps);
                }
            }
        }
        generateRoutes();
        PathStatus pathToTake = decideOptimalPath();
        escapeMaze(pathToTake);
    }

    private List<PathStatus> populatePaths(List<PathStatus> oldPaths) {
        List<PathStatus> output = new ArrayList<>();
        for(PathStatus path : oldPaths) {
            output.addAll(branch(path));
        }
        return output;
    }

    private List<PathStatus> branch(PathStatus pathStatus) {
        List<PathStatus> output = new ArrayList<>();
        List<Node> neighbours = generateAccessibleNeighbours(pathStatus.getLastNode());
        for(Node neighbour : neighbours) {
            if(!pathStatus.getPath().contains(neighbour)) {
                output.add(pathStatus.copyPathStatusWith(neighbour));
            }
        }

        if(output.isEmpty()) {
            deadEndpaths.add(pathStatus);
        } else {
            for(PathStatus p : output) {
                if(p.getLastNode().equals(destination)) {
                    successfulPaths.add(p);
                }
            }
        }

        return output;
    }

    private List<Node> generateAccessibleNeighbours(Node node) {
        Collection<Node> allNeighbours = node.getNeighbours();
        List<Node> accessibleNeighbours = new ArrayList<>();
        for(Node neighbour : allNeighbours) {
            if(vertices.contains(neighbour)) {
                accessibleNeighbours.add(neighbour);
            }
        }
        return accessibleNeighbours;
    }

    private void generateRoutes() {}

    private PathStatus decideOptimalPath() {
        if(prioritisedPaths.size() == 0) {
            throw new IllegalStateException("No paths have been put in the prioritised list");
        }
        PathStatus route = prioritisedPaths.peek();
        while(route.getTimeTaken() >= timeRemaining) {
            prioritisedPaths.poll();
            route = prioritisedPaths.peek();
        }
        return route;
    }

    private void escapeMaze(PathStatus path) {
        if(path == null) {
            throw new NullPointerException("Path to be traversed is null");
        }
        if(path.getTimeTaken() > timeRemaining) {
            throw new IllegalStateException("Path will take too long to traverse");
        }
        path.getPath().remove(0);
        for(Node n : path.getPath()) {
            currentState.moveTo(n);
            if(currentState.getCurrentNode().getTile().getGold() > 0) {
                currentState.pickUpGold();
            }
        }
    }
}