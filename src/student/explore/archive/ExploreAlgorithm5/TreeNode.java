package student.explore.archive.ExploreAlgorithm5;

import java.util.HashSet;
import java.util.Set;

public class TreeNode {

    private Long id;
    private TreeNode parent;
    private Set<TreeNode> children;
    private Set<Long> descendants;

    public TreeNode(Long id) {
        this.id = id;
        children = new HashSet<>();
        descendants = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public TreeNode getParent() {
        return parent;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
        TreeNode aux = this.parent;
        while(aux != null) {
            aux.addDescendant(this.id);
            aux = aux.getParent();
        }
    }

    private void addDescendant(Long id) {
        descendants.add(id);
    }

    public void addChild(TreeNode child) {
        children.add(child);
        child.setParent(this);
    }

    public Set<TreeNode> getChildren() {
        return children;
    }

    public Set<Long> getDescendants() {
        return descendants;
    }
}