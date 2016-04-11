package student.escape;

import game.EscapeState;
import game.Node;
import student.*;

import java.util.*;
import java.util.stream.Collectors;

public class EscapeRunner {

    private EscapeState currentState;
    private Collection<Node> goldNodes;
    private Map<Long, Node> outOfReach;
    private student.PriorityQueue<Node> goldRank;

    private static final double GOLD_FACTOR = 1;
    private static final double DIST_FACTOR = 1.9;

    public EscapeRunner(EscapeState state) {
        currentState = state;
        goldNodes = currentState.getVertices();
        outOfReach = new HashMap<>();
        goldRank = new PriorityQueueImpl<>();

        if(currentState.getCurrentNode().getTile().getGold() > 0) {
            currentState.pickUpGold();
        }
        updateGoldNodes();
        updateGoldRank();
    }

    public void escapeMaze() {
        while(currentState.getCurrentNode().getId() != currentState.getExit().getId()) {
            if(goldRank.size() == 0) {
                ShortestPathFinder toExit = new ShortestPathFinder(currentState, currentState.getCurrentNode(), currentState.getExit());
                followPath(toExit.getPath());
            } else {
                ShortestPathFinder toGold = new ShortestPathFinder(currentState, currentState.getCurrentNode(), goldRank.poll());
                followPath(toGold.getPath());
                updateGoldNodes();
                updateGoldRank();
            }
        }
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
                n.getTile().getGold() > 0 && !outOfReach.containsKey(n.getId())).collect(Collectors.toList());
    }

    private void updateGoldRank() {
        goldRank = new PriorityQueueImpl<>();
        for(Node n : goldNodes) {
            ShortestPathFinder toGold = new ShortestPathFinder(currentState, currentState.getCurrentNode(), n);
            ShortestPathFinder toExit = new ShortestPathFinder(currentState, n, currentState.getExit());

            int distanceToGold = toGold.getDistance();
            int distanceToExit = toExit.getDistance();
            int total = distanceToExit + distanceToGold;

            if(total < currentState.getTimeRemaining()) {
                goldRank.add(n, (distanceToGold * DIST_FACTOR) - (n.getTile().getGold() * GOLD_FACTOR));
            } else {
                outOfReach.put(n.getId(), n);
            }
        }
    }
}

