package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

public class Escape {

    private EscapeState currentState;
    private Node source;
    private Node destination;
    private int timeRemaining;
    private PriorityQueue<PathStatus> possiblePaths;

    public Escape(EscapeState state) {
        currentState = state;
        source = currentState.getCurrentNode();
        destination = currentState.getExit();
        timeRemaining = currentState.getTimeRemaining();
        possiblePaths = new PriorityQueueImpl<>();
    }

    public void findExit() {
        MazeGraph graph = buildGraph();
        populatePaths(graph);
        PathStatus pathToTake = decideOptimalPath();
        escapeMaze(pathToTake);
    }

    private MazeGraph buildGraph() {
        return null;
    }

    private void populatePaths(MazeGraph graph) {

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
    }
}
