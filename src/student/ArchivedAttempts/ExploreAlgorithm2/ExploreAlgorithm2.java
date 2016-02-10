/*
package student.ArchivedAttempts.ExploreAlgorithm2;

import game.ExplorationState;
import game.NodeStatus;
import student.PriorityQueueImpl;

import java.util.Collection;

public class ExploreAlgorithm2 implements ExploreAlgorithm{

    private TreeNode tree;
    private ExplorationState state;

    public ExploreAlgorithm2(ExplorationState state) {
        this.state = state;
        tree = new TreeNode(state.getCurrentLocation());
    }

    private void exploreNeighbours() {
        Collection<NodeStatus> neighbours = state.getNeighbours();
        PriorityQueueImpl<NodeStatus> orderedNeighbours = new PriorityQueueImpl<>();
        for(NodeStatus n : neighbours) {
            orderedNeighbours.add(n, n.getDistanceToTarget());
        }
        TreeNode aux = tree;
        while(aux.getChildren().length != 0) {
            aux = aux.getChildren()[0];
        }
        aux.addChildren(orderedNeighbours);
        if(deadEnd()) {
            removePath();
        }
        moveSprite();
    }

    private void moveSprite() {
        TreeNode aux = tree;
        while(aux.getChildren().length != 0) {
            aux = aux.getChildren()[0];
        }
        state.moveTo(aux.getId());
    }

    private boolean deadEnd() {
        if(state.getNeighbours().size() == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    public void removePath() {
        TreeNode aux = tree;
        while(aux.getChildren()[0].getChildren().length !=0) {
            aux = aux.getChildren()[0];
        }
        aux.removeChild();
    }

    @Override
    public void execute() {
        while(state.getDistanceToTarget() != 0) {
            exploreNeighbours();
        }
    }
}
*/
