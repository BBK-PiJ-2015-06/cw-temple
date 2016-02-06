package student.ExploreAlgorithm2;

import game.NodeStatus;
import student.PriorityQueue;

public class ArbitraryTreeNode {

    private long nodeId;
    private ArbitraryTreeNode[] children;

    public ArbitraryTreeNode(long nodeId) {
        this.nodeId = nodeId;
        this.children = new ArbitraryTreeNode[0];
    }

    public void addChildren(PriorityQueue<NodeStatus> neighbours) {
        int size = neighbours.size();
        children = new ArbitraryTreeNode[size];
        for(int i = size - 1; i >= 0; i--) {
            NodeStatus nodeStatus = neighbours.poll();
            children[i] = new ArbitraryTreeNode(nodeStatus.getId());
        }
    }

    public void removeChild() {
        if(children.length > 0) {
            ArbitraryTreeNode[] temp = new ArbitraryTreeNode[children.length - 1];
            for(int i = 0; i < temp.length; i++) {
                temp[i] = children[i + 1];
            }
            children = temp;
        }
    }

    public long getId() {
        return nodeId;
    }

    public ArbitraryTreeNode[] getChildren() {
        return children;
    }
}
