package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;

import java.util.Stack;

public class Escape {

    private EscapeState currentState;

    public Escape(EscapeState state) {
        currentState = state;
    }

    public void escapeMaze() {
        ShortestPathFinder pathFinder = new ShortestPathFinder(currentState, currentState.getCurrentNode(), currentState.getExit());
        followPath(pathFinder.getPath());

        //Testing
        System.out.println(pathFinder);
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
}


