package student.ExploreAlgorithm;

import game.NodeStatus;
import student.PriorityQueue;
import student.PriorityQueueImpl;

public class TreeNode {

    private long id;
    private int distance;
    private PriorityQueue<TreeNode> children;
    private TreeNode parent;

    public TreeNode(long id, int distance) {
        this.id = id;
        this.distance = distance;
        children = new PriorityQueueImpl<>();
    }

    public TreeNode(NodeStatus nodeStatus) {
        id = nodeStatus.getId();
        distance = nodeStatus.getDistanceToTarget();
        children = new PriorityQueueImpl<>();
    }

    public long getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public PriorityQueue<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getParent() {
        return  parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void addChild(NodeStatus nodeStatus) {
        TreeNode child = new TreeNode(nodeStatus);
        children.add(child, child.getDistance());
    }

}

