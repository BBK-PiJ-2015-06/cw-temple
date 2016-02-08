package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
        populatePaths(source, source.getNeighbours());
        PathStatus pathToTake = decideOptimalPath();
        escapeMaze(pathToTake);
    }

    private void populatePaths(Node src, Collection<Node> neighbours) {
        List<Node> accessibleNeighbours = new ArrayList<>();
        for(Node n : neighbours) {
            if(vertices.contains(n)) {
                accessibleNeighbours.add(n);
            }
        }
        if(!possiblePaths.isEmpty()) {
            Iterator iterator = possiblePaths.iterator();
            while(iterator.hasNext()) {
                PathStatus p = (PathStatus)iterator.next();
                branch(p, accessibleNeighbours);
            }
        }
    }

    private void branch(PathStatus existingPath, List<Node> neighbours) {
        if(existingPath.getPath().containsAll(neighbours)) {
            deadEndpaths.add(existingPath);
        } else {
            for(Node n : neighbours) {
                if(!existingPath.getPath().contains(n)) {
                    PathStatus updatedPath = new PathStatus(source);
                    for(int i = 1; i < existingPath.getPath().size(); i++) {
                        updatedPath.addNode(existingPath.getPath().get(i));
                    }
                    updatedPath.addNode(n);
                    if(n.equals(destination)) {
                        successfulPaths.add(updatedPath);
                    } else {
                        possiblePaths.add(updatedPath);
                    }
                }
            }
        }
        possiblePaths.remove(existingPath);
    }

    private PathStatus decideOptimalPath() {
        return null;
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
