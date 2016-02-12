package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ExploreWithTreeNode {

    private ExplorationState state;
    private TreeNode root;
    private TreeNode pointer;
    private Set<NodeStatus> notVisited;

    public ExploreWithTreeNode(ExplorationState state) {
        this.state = state;
        root = new TreeNode(this.state.getCurrentLocation());
        pointer = root;
        notVisited = new HashSet<>();
    }

    public void findOrb() {
        while(state.getDistanceToTarget() != 0) {

            //Find all neighbours associated with current location
            Collection<NodeStatus> neighbours = state.getNeighbours();

            //Removes the neighbour that corresponds to this nodes parent and adds remaining neighbours as children
            if(pointer.getParent() != null) {
                neighbours.removeIf(n -> pointer.getParent().getId().equals(n.getId()));
            }
            neighbours.forEach(n -> pointer.addChild(new TreeNode(n.getId())));

            //Add neighbours to notVisited set
            notVisited.addAll(neighbours);

            //Decide on next destination
        }
    }
}
