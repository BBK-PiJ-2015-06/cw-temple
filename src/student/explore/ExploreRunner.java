package student.explore;

import game.ExplorationState;
import game.NodeStatus;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * This class represents the algorithm used by the sprite to explore the maze. An object is
 * created for each instance of a game being played and incrementally builds a map as the
 * sprite explores the maze. This map is then used to enable the sprite to always move to the
 * tile that is closest to the orb that has not yet been visited. The algorithm uses a simplified
 * version of Dijkstra's shortest path finder to enable the sprite to take the shortest route to the
 * node that is closest to the orb, even if it is not a neighbour of the current tile that it resides upon.
 */
public class ExploreRunner {

    private ExplorationState currentState;
    private Set<Long> explored;
    private Set<NodeStatus> notExplored;
    private PriorityQueue<NodeStatus> rankedByDistance;
    private Collection<GraphNode> map;

    /**
     * Creates an ExploreRunner object using an ExplorationState object. This constructor gets
     * called from the Explorer class in package student.
     *
     * @param state the initial ExplorationState to be used when exploring the maze
     */
    public ExploreRunner(ExplorationState state) {
        currentState = state;
        explored = new HashSet<>();
        notExplored = new HashSet<>();
        rankedByDistance = new PriorityQueueImpl<>();
        map = new HashSet<>();
    }

    /**
     * Method used for finding the orb. This method builds the map with each node (and corresponding neighbours)
     * explored and updates the explored and not explored sets. Using these collections, a path is generated to
     * an as yet unexplored node that is closest to the orb. The aforementioned process is repeated until the sprite
     * resides upon the node where the orb is located.
     */
    public void findOrb() {
        while (currentState.getDistanceToTarget() != 0) {

            //Update map with current location
            Long currentLocation = currentState.getCurrentLocation();
            Collection<NodeStatus> neighbours = currentState.getNeighbours();
            map.add(new GraphNode(currentLocation, neighbours));

            //Update explored and not explored sets
            explored.add(currentLocation);
            notExplored.removeIf(n -> n.getId() == currentLocation);
            neighbours.stream().filter(n -> !notExplored.contains(n) && !explored.contains(n.getId()))
                    .forEach(n -> rankedByDistance.add(n, n.getDistanceToTarget()));
            neighbours.forEach(notExplored::add);

            //Find path to unexplored tile that's nearest to orb
            if (!neighbours.contains(rankedByDistance.peek())) {
                Stack<Long> path = PathBuilder.getPath(currentLocation, rankedByDistance.peek().getId(), map);
                while (!path.empty()) {
                    currentState.moveTo(path.pop());
                }
            }

            currentState.moveTo(rankedByDistance.poll().getId());
        }
    }
}
