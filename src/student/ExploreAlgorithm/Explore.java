package student.ExploreAlgorithm;

import game.ExplorationState;
import game.NodeStatus;

import java.util.*;

public class Explore {

    private TreeNode spritePosition;
    private ExplorationState currentState;
    private Map<Long, TreeNode> visited;

    public Explore(ExplorationState state) {
        currentState = state;
        spritePosition = new TreeNode(currentState.getCurrentLocation(), currentState.getDistanceToTarget());
        spritePosition.setParent(new TreeNode(0, 1000));
        visited = new HashMap<>();
        visited.put(spritePosition.getId(), spritePosition);
    }

    public void findOrb() {
        while(currentState.getDistanceToTarget() != 0) {
            int totalNeighbours = examineNeighbours();
            if(totalNeighbours == 0) {
                retraceSteps();
            }
            assertCorrectPath();
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

    private void moveGeorge(TreeNode destination) {
        currentState.moveTo(destination.getId());
        spritePosition = destination;
        visited.put(destination.getId(), destination);
    }

    private void retraceSteps() {
        while(spritePosition.getChildren().size() == 0) {
            currentState.moveTo(spritePosition.getParent().getId());
            spritePosition = spritePosition.getParent();
            spritePosition.getChildren().poll();
        }
    }

    private void assertCorrectPath() {
        TreeNode destination = spritePosition.getChildren().peek();
        while(isTraversed(destination)) {
            if(spritePosition.getChildren().size() == 1) {
                spritePosition.getChildren().poll();
                retraceSteps();
            } else {
                spritePosition.getChildren().poll();
            }
            destination = spritePosition.getChildren().peek();
        }
        moveGeorge(destination);
    }

    private boolean isTraversed(TreeNode node) {
        return visited.containsKey(node.getId());
    }
}

