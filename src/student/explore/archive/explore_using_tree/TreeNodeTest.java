package student.explore.archive.explore_using_tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeTest {

    private TreeNode root;
    private TreeNode child1;
    private TreeNode child2;
    private TreeNode child3;

    @Before
    public void setUp() {
        root = new TreeNode(1L);
        child1 = new TreeNode(2L);
        child2 = new TreeNode(3L);
        child3 = new TreeNode(4L);
    }

    @Test
    public void testGetId() {
        assertEquals(Long.valueOf(1), root.getId());
    }

    @Test
    public void testAddChildAndGetDescendants() {
        root.addChild(child1);
        root.addChild(child2);
        child2.addChild(child3);
        assertTrue(root.getDescendants().contains(child1.getId()));
        assertTrue(root.getDescendants().contains(child2.getId()));
        assertTrue(root.getDescendants().contains(child3.getId()));
        assertTrue(root.getDescendants().size() == 3);

        assertTrue(child1.getDescendants().isEmpty());

        assertTrue(child2.getDescendants().contains(child3.getId()));
        assertTrue(child2.getDescendants().size() == 1);

        assertTrue(child3.getDescendants().isEmpty());
    }

    @Test
    public void testAddChildAndGetChildren() {
        root.addChild(child1);
        root.addChild(child2);
        child2.addChild(child3);

        assertTrue(root.getChildren().size() == 2);
        assertTrue(root.getChildren().contains(child1));
        assertTrue(root.getChildren().contains(child2));

        assertTrue(child2.getChildren().size() == 1);
        assertTrue(child2.getChildren().contains(child3));

        assertTrue(child1.getChildren().isEmpty());
        assertTrue(child3.getChildren().isEmpty());
    }
}