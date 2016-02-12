package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.*;

public class Explore {

    private ExplorationState state;
    private List<Long> visited;
    private Stack<NodeStatus> currentPath;

    public Explore(ExplorationState state) {
        this.state = state;
        visited = new ArrayList<>();
        currentPath = new Stack<>();
    }

    public void findOrb() {
        while(state.getDistanceToTarget() != 0) {
            //Add current location to visited list
            visited.add(state.getCurrentLocation());

            //Finds all neighbours associated with current location
            Collection<NodeStatus> neighbours = state.getNeighbours();

            //Removes those neighbours that have already been traversed
            neighbours.removeIf(n -> visited.contains(n.getId()));

            //Ranks neighbours based upon their distance to orb
            List<NodeStatus> rankedNeighbours = new ArrayList<>();
            rankedNeighbours.addAll(neighbours);
            rankedNeighbours.sort((n1, n2) -> n1.getDistanceToTarget() - n2.getDistanceToTarget());

            //Decides destination of next move
            NodeStatus destination;
            if(rankedNeighbours.size() > 1) {
                if(rankedNeighbours.get(0).getDistanceToTarget() == rankedNeighbours.get(1).getDistanceToTarget()) {
                    Random randomGenerator = new Random();
                    destination = rankedNeighbours.get(randomGenerator.nextInt(2));
                } else {
                    destination = rankedNeighbours.get(0);
                }
            } else if(rankedNeighbours.size() == 1){
                destination = rankedNeighbours.get(0);
            } else {
                currentPath.pop();
                destination = currentPath.pop();
            }

            //Moves to next destination and pushes to stack
            state.moveTo(destination.getId());
            currentPath.push(destination);
        }
    }
}
