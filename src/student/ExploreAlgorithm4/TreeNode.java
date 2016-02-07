package student.ExploreAlgorithm4;

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
        this.children = new PriorityQueueImpl<>();
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

}
