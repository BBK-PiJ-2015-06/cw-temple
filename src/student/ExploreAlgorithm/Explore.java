package student.ExploreAlgorithm;

import game.ExplorationState;
import game.NodeStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Explore {

    private ExplorationState currentState;
    private Map<Long, NodeStatus> visitedNodes;
    private Map<Long, NodeStatus> notVisited;
    private PriorityQueue<NodeStatus> nextMove;

    public Explore(ExplorationState state) {
        currentState = state;
        visitedNodes = new HashMap<>();
        notVisited = new HashMap<>();
        visitedNodes.put(currentState.getCurrentLocation(), null);
        for(NodeStatus ns : currentState.getNeighbours()) {
            notVisited.put(ns.getId(), ns);
        }
    }

    public void findOrb() {
        while(currentState.getDistanceToTarget() != 0) {
            Stack<NodeStatus> route = getNextMove();
            moveSprite(route);
        }
    }

    private Stack<NodeStatus> getNextMove() {

    }

    private void moveSprite(Stack<NodeStatus> route) {
        while(!route.empty()) {
            NodeStatus nextNode = route.pop();
            currentState.moveTo(nextNode.getId());
            if(!visitedNodes.containsKey(nextNode.getId())) {
                visitedNodes.put(nextNode.getId(), nextNode);
            }
            for(NodeStatus ns : currentState.getNeighbours()) {
                if(!notVisited.containsKey(ns.getId())) {
                    notVisited.put(ns.getId(), ns);
                }
            }
        }
    }
}
