package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExploreAlgorithm1 implements ExploreAlgorithm{

    private ExplorationState state;
    private NodeComponent component;
    private List<Long> deadEnds;
    private List<Long> traversedNodes;

    public ExploreAlgorithm1(ExplorationState state) {
        this.state = state;
        this.component = new NodeComposite(state.getCurrentLocation());
        this.deadEnds = new ArrayList<>();
        this.traversedNodes = new ArrayList<>();
    }

    @Override
    public void execute() {
        while(state.getDistanceToTarget() != 0) {
            if(!contains(traversedNodes, state.getCurrentLocation())) {
                addAvailableExits();
            }
            state.moveTo(component.getChildren().peek().getId());

        }
    }

    private void addAvailableExits() {
        Collection<NodeStatus> exits = state.getNeighbours();
        for(NodeStatus n : exits) {
            if(!contains(deadEnds, n.getId())) {
                component.addChildNode(new NodeComposite(n.getId()), n.getDistanceToTarget());
            }
        }
    }

    private boolean contains(List<Long> list, long id) {
        boolean output = false;
        for(Long l : list) {
            if(l.equals(id)) {
                output = true;
            }
        }
        return output;
    }

}
