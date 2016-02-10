package student.ExploreAlgorithm;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Explore {

    private ExplorationState currentState;
    private List<NodeStatusTree> roots;

    public Explore(ExplorationState state) {
        currentState = state;
        roots = new ArrayList<>();
        Collection<NodeStatus> neighbours = currentState.getNeighbours();
        for(NodeStatus ns : neighbours) {
            roots.add(new NodeStatusTree(ns));
        }
    }
}
