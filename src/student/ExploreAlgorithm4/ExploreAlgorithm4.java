package student.ExploreAlgorithm4;

import game.ExplorationState;
import game.NodeStatus;
import java.util.TreeSet;

public class ExploreAlgorithm4 {

    private TreeNode root;
    private TreeNode leaf;

    private ExplorationState currentState;

    private TreeSet<NodeStatus> nodesVisited;
    private TreeSet<NodeStatus> nodesDiscovered;
    private TreeSet<NodeStatus> currentlyAccessible;

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
        while(currentState.getDistanceToTarget() != 0) {
           getNeighbourInfo();
           decideDirection();
       }
    }

    private void getNeighbourInfo() {
        updateNeighbours();
        for(NodeStatus n : currentlyAccessible) {
            if(!nodesVisited.contains(n)) {
                nodesDiscovered.add(n);
            }
            leaf.getChildren().add(new TreeNode(n.getId(), n.getDistanceToTarget(), leaf));
        }
    }

    private void decideDirection() {
        NodeStatus destination = nodesDiscovered.first();
        if(currentlyAccessible.contains(destination)) {
            moveExplorerTo(destination);
        } else {
            backTrack();
            decideDirection();
        }
    }

    private void moveExplorerTo(NodeStatus destination) {
        currentState.moveTo(destination.getId());
        nodesVisited.add(destination);
        nodesDiscovered.remove(destination);
        for(TreeNode tn : leaf.getChildren()) {
            if(tn.getId() == destination.getId()) {
                leaf = tn;
            }
        }
        currentlyAccessible.clear();
    }

    private void backTrack() {
        currentState.moveTo(leaf.getParent().getId());
        leaf = leaf.getParent();
        updateNeighbours();
    }

    private void updateNeighbours() {
        currentlyAccessible.clear();
        currentlyAccessible.addAll(currentState.getNeighbours());
    }

    @Override
    public String toString() {
        return "Root: " + "\n" + root
                + "\n"
                + "\nLeaf: " + "\n" + leaf
                + "\n"
                + "\nVisited: " + "\n" + nodesVisited
                + "\n"
                + "\nDiscovered: " + "\n" + nodesDiscovered
                + "\n"
                + "\nAccessible: " + "\n" + currentlyAccessible;
    }
}