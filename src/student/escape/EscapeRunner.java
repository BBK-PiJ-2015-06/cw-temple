package student.escape;

import game.EscapeState;
import game.Node;
import student.PriorityQueueImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * This class represents the algorithm used to enable George to escape from the
 * maze, while picking up gold that he encounters. This class makes heavy use of
 * Dijkstra's algorithm to continuously compute the shortest distance to the exit
 * so that he always makes it out of the maze before time runs out. The decision of
 * which Node George wil attempt to reach next is calculated based upon a gold and
 * distance factor (considers the shortest distance to the node and the amount of gold
 * located there and en route)
 */
public class EscapeRunner {

    private static final double GOLD_FACTOR = 1;
    private static final double DIST_FACTOR = 3;
    private EscapeState currentState;
    private Collection<Node> goldNodes;
    private Map<Long, Node> outOfReach;
    private student.PriorityQueue<Node> goldRank;

    /**
     * Constructs the EscapeRunner object, using an EscapeState.
     *
     * @param state the current EscapeState
     */
    public EscapeRunner(EscapeState state) {
        currentState = state;
        goldNodes = currentState.getVertices();
        outOfReach = new HashMap<>();
        goldRank = new PriorityQueueImpl<>();

        if (currentState.getCurrentNode().getTile().getGold() > 0) {
            currentState.pickUpGold();
        }
        updateGoldNodes();
        updateGoldRank();
    }

    /**
     * The main algorithm used by this EscapeRunner object to perform the escape phase
     * of the coursework.
     */
    public void escapeMaze() {
        while (currentState.getCurrentNode().getId() != currentState.getExit().getId()) {
            if (goldRank.size() == 0) {
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

    /**
     * Moves the sprite along the path specified.
     *
     * @param path a path to be followed during the escape phase
     */
    private void followPath(Stack<DijVertex> path) {
        path.pop();
        while (!path.empty()) {
            Node nextNode = path.pop().getNode();
            currentState.moveTo(nextNode);
            if (nextNode.getTile().getGold() > 0) {
                currentState.pickUpGold();
            }
        }
    }

    /**
     * Updates the goldNodes field. The updated field will only contain those nodes within
     * the maze that contain gold and are within reach within the allocated time. A node is deemed
     * to be within reach if the time taken to reach it plus the time taken to reach the exit is less
     * than the remaining time limit.
     */
    private void updateGoldNodes() {
        goldNodes = goldNodes.stream().filter(n ->
                n.getTile().getGold() > 0 && !outOfReach.containsKey(n.getId())).collect(Collectors.toList());
    }

    /**
     * Updates the goldRank field based upon the revised goldNodes list. Nodes are ranked in order of priority
     * based upon the amount of gold on the node as well as the distance to the node from the current location.
     */
    private void updateGoldRank() {
        goldRank = new PriorityQueueImpl<>();
        for (Node n : goldNodes) {
            ShortestPathFinder toGold = new ShortestPathFinder(currentState, currentState.getCurrentNode(), n);
            ShortestPathFinder toExit = new ShortestPathFinder(currentState, n, currentState.getExit());

            int distanceToGold = toGold.getDistance();
            int distanceToExit = toExit.getDistance();
            int total = distanceToExit + distanceToGold;

            if (total < currentState.getTimeRemaining()) {
                goldRank.add(n, distanceToGold * DIST_FACTOR - n.getTile().getGold() * GOLD_FACTOR);
            } else {
                outOfReach.put(n.getId(), n);
            }
        }
    }
}

