package student;

import game.ExplorationState;

public class ExploreWithTreeNode {

    private ExplorationState state;
    private TreeNode root;

    public ExploreWithTreeNode(ExplorationState state) {
        this.state = state;
        root = new TreeNode(this.state.getCurrentLocation());
    }

    public void findOrb() {
        
    }
}
