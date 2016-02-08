package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Escape {

    private EscapeState currentState;
    private Node source;
    private Node destination;
    private Collection<Node> vertices;
    private int timeRemaining;
    private List<PathStatus> possiblePaths;
    private PriorityQueue<PathStatus> priorityPaths;

    public Escape(EscapeState state) {
        currentState = state;
        source = currentState.getCurrentNode();
        destination = currentState.getExit();
        vertices = currentState.getVertices();
        timeRemaining = currentState.getTimeRemaining();
        possiblePaths = new ArrayList<>();
        possiblePaths.add(new PathStatus(source));
    }

    public void findExit() {
        populatePaths(source, vertices);
        PathStatus pathToTake = decideOptimalPath();
        escapeMaze(pathToTake);
    }

    private void populatePaths(Node src, Collection<Node> vertices) {

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
