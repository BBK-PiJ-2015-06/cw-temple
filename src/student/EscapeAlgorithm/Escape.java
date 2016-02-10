package student.EscapeAlgorithm;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.*;
import java.util.stream.Collectors;

public class Escape {

    private EscapeState currentState;
    private Collection<Node> goldNodes;
    private Map<Long, Node> outOfReach;
    private PriorityQueue<Node> goldRank;

    public Escape(EscapeState state) {
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
                goldRank.add(n, distanceToGold - n.getTile().getGold());
            } else {
                outOfReach.put(n.getId(), n);
            }
        }
    }
}


