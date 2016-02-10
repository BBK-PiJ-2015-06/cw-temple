package student.ExploreAlgorithm;

import game.NodeStatus;
import student.PriorityQueue;

public class NodeStatusTree {

    private long nodeId;
    private int nodeDistance;
    private NodeStatusTree parent;
    private PriorityQueue<NodeStatusTree> children;

    public NodeStatusTree(NodeStatus nodeStatus) {
        nodeId = nodeStatus.getId();
        nodeDistance = nodeStatus.getDistanceToTarget();
    }

    public NodeStatusTree(long id, int dist) {
        nodeId = id;
        nodeDistance = dist;
    }

    public long getId() {
        return nodeId;
    }

    public int getDistanceToExit() {
        return nodeDistance;
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
