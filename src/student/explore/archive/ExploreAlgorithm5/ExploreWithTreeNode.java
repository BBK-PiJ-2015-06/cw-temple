package student.explore.archive.ExploreAlgorithm5;

import game.ExplorationState;
import game.NodeStatus;

import java.util.*;

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

            //Order the notVisited nodes based on their distance to the orb
            List<NodeStatus> sortedNotVisited = new ArrayList<>();
            sortedNotVisited.addAll(notVisited);
            sortedNotVisited.sort((n1, n2) -> n1.getDistanceToTarget() - n2.getDistanceToTarget());

            //Decide on next destination (notVisited node that is closest to the orb)
            //If there is a tie in terms of distance, destination is randomly chosen from closest nodes
            NodeStatus destination;
            if(sortedNotVisited.size() > 1) {
                List<NodeStatus> equiDistant = new ArrayList<>();
                for(NodeStatus nodeStatus : sortedNotVisited) {
                    if(nodeStatus.getDistanceToTarget() == sortedNotVisited.get(0).getDistanceToTarget()) {
                        equiDistant.add(nodeStatus);
                    }
                }
                Random randomGenerator = new Random();
                destination = equiDistant.get(randomGenerator.nextInt(equiDistant.size()));
            } else {
                destination = sortedNotVisited.get(0);
            }

            //Move sprite to next destination
            for(TreeNode treeNode : pointer.getChildren()) {
                if(treeNode.getId() == destination.getId()) {
                    state.moveTo(treeNode.getId());
                    pointer = treeNode;
                }
            }

            while(pointer.getId() != destination.getId()) {
                if(pointer.getDescendants().contains(destination.getId())) {
                    for(TreeNode treeNode : pointer.getChildren()) {
                        if(treeNode.getId() == destination.getId()) {
                            state.moveTo(treeNode.getId());
                            pointer = treeNode;
                        } else if (treeNode.getDescendants().contains(destination.getId())) {
                            state.moveTo(treeNode.getId());
                            pointer = treeNode;
                        }
                    }
                } else {
                    state.moveTo(pointer.getParent().getId());
                    pointer = pointer.getParent();
                }
            }

            //Update notVisited set
            notVisited.remove(destination);
        }
    }
}
