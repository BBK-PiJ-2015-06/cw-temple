package student.ExploreAlgorithm4;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private long id;
    private int dist;
    private List<TreeNode> children;
    private TreeNode parent;

    public TreeNode(long id, int dist, TreeNode parent) {
        this.id = id;
        this.dist = dist;
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    public long getId() {
        return id;
    }

    public int getDist() {
        return dist;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getParent() {
        return  parent;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nDist: " + dist +
                "\nChildren: " + children.toString();
    }

}
