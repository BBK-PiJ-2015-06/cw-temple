package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;
import java.util.stream.Collectors;

public class Escape {

    private EscapeState currentState;
    private Collection<Node> goldNodes;
    private Collection<Node> outOfReach;
    private PriorityQueue<Node> goldRank;

    public Escape(EscapeState state) {
        currentState = state;
        goldNodes = currentState.getVertices();
        outOfReach = new ArrayList<>();
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
        goldNodes = goldNodes.stream().filter(n ->
                n.getTile().getGold() > 0 && !outOfReach.contains(n)).collect(Collectors.toList());
    }

    private void updateGoldRank() {
        for(Node n : goldNodes) {
            ShortestPathFinder toGold = new ShortestPathFinder(currentState, currentState.getCurrentNode(), n);
            ShortestPathFinder toExit = new ShortestPathFinder(currentState, n, currentState.getExit());

            int distanceToGold = toGold.getDistance();
            int distanceToExit = toExit.getDistance();
            int total = distanceToExit + distanceToGold;

            if(total < currentState.getTimeRemaining()) {
                goldRank.add(n, distanceToGold - n.getTile().getGold());
            } else {
                outOfReach.add(n);
            }
        }
    }
}


