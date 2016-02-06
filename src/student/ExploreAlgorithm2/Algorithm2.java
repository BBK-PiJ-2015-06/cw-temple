package student.ExploreAlgorithm2;

import game.ExplorationState;
import game.NodeStatus;
import student.ExploreAlgorithm;
import student.PriorityQueueImpl;

import java.util.Collection;

public class Algorithm2 implements ExploreAlgorithm{

    private ArbitraryTreeNode tree;
    private ExplorationState state;

    public Algorithm2(ExplorationState state) {
        this.state = state;
        tree = new ArbitraryTreeNode(state.getCurrentLocation());
    }

    public void exploreNeighbours() {
        Collection<NodeStatus> neighbours = state.getNeighbours();
        PriorityQueueImpl<NodeStatus> orderedNeighbours = new PriorityQueueImpl<>();
        for(NodeStatus n : neighbours) {
            orderedNeighbours.add(n, n.getDistanceToTarget());
        }
        ArbitraryTreeNode aux = tree;
        while(aux.getChildren().length != 0) {
            aux = aux.getChildren()[0];
        }
        aux.addChildren(orderedNeighbours);
    }

    public void moveSprite() {
        ArbitraryTreeNode aux = tree;
        while(aux.getChildren().length != 0) {
            aux = aux.getChildren()[0];
        }
        state.moveTo(aux.getId());
    }

    public boolean deadEnd() {
        if(state.getNeighbours().size() == 0 ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void execute() {

    }
}
