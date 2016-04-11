package student.explore;

import game.ExplorationState;
import game.NodeStatus;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ExploreRunner {

    private ExplorationState currentState;
    private Set<Long> explored;
    private Set<NodeStatus> notExplored;
    private PriorityQueue<NodeStatus> rankedByDistance;
    private Collection<GraphNode> map;

    public ExploreRunner(ExplorationState state) {
        currentState = state;
        explored = new HashSet<>();
        notExplored = new HashSet<>();
        rankedByDistance = new PriorityQueueImpl<>();
        map = new HashSet<>();
    }

    public void findOrb() {
        while(currentState.getDistanceToTarget() != 0) {
            Long currentLocation = currentState.getCurrentLocation();
            Collection<NodeStatus> neighbours = currentState.getNeighbours();
            map.add(new GraphNode(currentLocation, neighbours));

            explored.add(currentLocation);
            notExplored.removeIf(n -> n.getId() == currentLocation);

            neighbours.stream().filter(n -> !notExplored.contains(n) && !explored.contains(n.getId()))
                    .forEach(n -> rankedByDistance.add(n, n.getDistanceToTarget()));

            neighbours.forEach(notExplored::add);

            if(!neighbours.contains(rankedByDistance.peek())) {
                Stack<Long> path = PathBuilder.getPath(currentLocation, rankedByDistance.peek().getId(), map);
                while(!path.empty()) currentState.moveTo(path.pop());
            }
            currentState.moveTo(rankedByDistance.poll().getId());
        }
    }
}
