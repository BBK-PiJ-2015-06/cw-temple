package student.ExploreAlgorithm3;

import game.ExplorationState;
import game.NodeStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ExploreMaze {

    private TreeNode treeRoot;
    private TreeNode treeLeaf;
    private ExplorationState state;
    private List<Long> traversedNodes;

    public ExploreMaze(ExplorationState state) {
        this.state = state;
        treeRoot = null;
        treeLeaf = null;
        traversedNodes = new ArrayList<>();
    }

    public void run() {
        if(treeRoot == null) {
            treeRoot = new TreeNode(state.getCurrentLocation(), state.getDistanceToTarget(), null);
        }
        updateLeafPointer();
        while(state.getDistanceToTarget() != 0) {
            exploreNeighbours();
            moveSprite();
        }
    }

    private void exploreNeighbours() {
        Collection<NodeStatus> neighbours = state.getNeighbours();
        Iterator<NodeStatus> iterator = neighbours.iterator();
        while(iterator.hasNext()) {
            NodeStatus nodeStatus = iterator.next();
            for(Long l : traversedNodes) {
                if(l.equals(nodeStatus.getId())) {
                    iterator.remove();
                }
            }
        }
        List<TreeNode> sortedNodes = new ArrayList<>();
        for(NodeStatus n : neighbours) {
            sortedNodes.add(new TreeNode(n.getId(), n.getDistanceToTarget(), treeLeaf));
        }
        sortedNodes.sort((n1, n2) -> n1.getDist() - n2.getDist());
        treeLeaf.getChildren().addAll(sortedNodes);
        updateLeafPointer();
    }

    private void moveSprite() {
        state.moveTo(treeLeaf.getId());
        traversedNodes.add(state.getCurrentLocation());
    }

    private void updateLeafPointer() {
        TreeNode aux = treeRoot;
        while(aux.getChildren().size() != 0) {
            aux = aux.getChildren().get(0);
        }
        treeLeaf = aux;
    }

}
