package student.ExploreAlgorithm4;

import game.ExplorationState;
import game.NodeStatus;

import java.util.Collection;
import java.util.TreeSet;

public class ExploreAlgorithm4 {

    private TreeNode root;
    private TreeNode leaf;

    private ExplorationState currentState;

    private Collection<NodeStatus> nodesVisited;
    private Collection<NodeStatus> nodesDiscovered;
    private Collection<NodeStatus> currentlyAccessible;

    public ExploreAlgorithm4(ExplorationState state) {
        currentState = state;
        root = null;
        leaf = null;
        nodesVisited = new TreeSet<>((n1, n2) -> n1.getDistanceToTarget() - n2.getDistanceToTarget());
        nodesDiscovered = new TreeSet<>((n1, n2) -> n1.getDistanceToTarget() - n2.getDistanceToTarget());
        currentlyAccessible = new TreeSet<>((n1, n2) -> n1.getDistanceToTarget() - n2.getDistanceToTarget());
    }

    public void run() {
        root = new TreeNode(currentState.getCurrentLocation(), currentState.getDistanceToTarget(), null);
        leaf = root;
        getNeighbourInfo();
        System.out.println(root);
    }

    public void getNeighbourInfo() {
        Collection<NodeStatus> neighbours = currentState.getNeighbours();
        nodesDiscovered.addAll(neighbours);
    }

}
