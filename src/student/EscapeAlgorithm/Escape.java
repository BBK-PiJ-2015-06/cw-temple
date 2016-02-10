package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;

import java.util.Collection;
import java.util.Stack;
import java.util.stream.Collectors;

public class Escape {

    private EscapeState currentState;
    private Collection<Node> goldNodes;

    public Escape(EscapeState state) {
        currentState = state;
        goldNodes = currentState.getVertices();
        updateGoldNodes();
    }

    public void escapeMaze() {
        ShortestPathFinder pathFinder = new ShortestPathFinder(currentState, currentState.getCurrentNode(), currentState.getExit());
        followPath(pathFinder.getPath());
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

    private void updateGoldNodes() {
        goldNodes = goldNodes.stream().filter(n -> n.getTile().getGold() > 0).collect(Collectors.toList());
    }
}


