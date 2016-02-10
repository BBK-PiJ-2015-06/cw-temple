package student.ExploreAlgorithm;

import game.NodeStatus;
import student.PriorityQueue;

public class NodeStatusTree {

    private NodeStatus nodeStatus;
    private NodeStatusTree parent;
    private PriorityQueue<NodeStatusTree> children;

    public NodeStatusTree(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public NodeStatus getNodeStatus() {
        return nodeStatus;
    }

    public NodeStatusTree getParent() {
        return parent;
    }

    public PriorityQueue<NodeStatusTree> getChildren() {
        return children;
    }

    public void setParent(NodeStatusTree parent) {
        this.parent = parent;
    }

    public void setChildren(PriorityQueue<NodeStatusTree> children) {
        this.children = children;
    }
}
