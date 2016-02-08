package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

public class Escape {

    private EscapeState currentState;
    private Node source;
    private Node destination;
    private PriorityQueue<PathStatus> possiblePaths;

    public Escape(EscapeState state) {
        currentState = state;
        source = currentState.getCurrentNode();
        destination = currentState.getExit();
        possiblePaths = new PriorityQueueImpl<>();
    }

    public void findExit() {}
}
