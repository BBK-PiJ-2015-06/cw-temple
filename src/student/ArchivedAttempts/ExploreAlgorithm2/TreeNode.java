package student.ArchivedAttempts.ExploreAlgorithm2;

import game.NodeStatus;
import student.PriorityQueue;

public class TreeNode {

    private long nodeId;
    private TreeNode[] children;

    public TreeNode(long nodeId) {
        this.nodeId = nodeId;
        this.children = new TreeNode[0];
    }

    public void addChildren(PriorityQueue<NodeStatus> neighbours) {
        int size = neighbours.size();
        children = new TreeNode[size];
        for(int i = size - 1; i >= 0; i--) {
            NodeStatus nodeStatus = neighbours.poll();
            children[i] = new TreeNode(nodeStatus.getId());
        }
    }

    public void removeChild() {
        if(children.length > 0) {
            TreeNode[] temp = new TreeNode[children.length - 1];
            for(int i = 0; i < temp.length; i++) {
                temp[i] = children[i + 1];
            }
            children = temp;
        }
    }

    public long getId() {
        return nodeId;
    }

    public TreeNode[] getChildren() {
        return children;
    }
}
