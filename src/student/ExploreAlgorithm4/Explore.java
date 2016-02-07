package student.ExploreAlgorithm4;

import game.ExplorationState;
import game.NodeStatus;

import java.util.Collection;

public class Explore {

    private TreeNode root;
    private ExplorationState currentState;
    private TreeNode spritePosition;

    public Explore(ExplorationState state) {
        currentState = state;
        root = new TreeNode(currentState.getCurrentLocation(), currentState.getDistanceToTarget());
        spritePosition = root;
        root.setParent(new TreeNode(0, 1000));

    }

    public void findOrb() {
        while(currentState.getDistanceToTarget() != 0) {
            int totalNeighbours = examineNeighbours();
            if(totalNeighbours == 0) {
                retraceSteps();
            }
            moveGeorge();
        }
    }

    private int examineNeighbours() {
        Collection<NodeStatus> neighbours = currentState.getNeighbours();
        for(NodeStatus n : neighbours) {
            if(n.getId() != spritePosition.getParent().getId()) {
                TreeNode tn = new TreeNode(n.getId(), n.getDistanceToTarget());
                tn.setParent(spritePosition);
                spritePosition.getChildren().add(tn, n.getDistanceToTarget());
            }
        }
        return spritePosition.getChildren().size();
    }

    private void moveGeorge() {
        TreeNode destination = spritePosition.getChildren().peek();
        currentState.moveTo(destination.getId());
        spritePosition = destination;
    }

    private void retraceSteps() {
        while(spritePosition.getChildren().size() == 0) {
            currentState.moveTo(spritePosition.getParent().getId());
            spritePosition = spritePosition.getParent();
            spritePosition.getChildren().poll();
        }
    }
}
