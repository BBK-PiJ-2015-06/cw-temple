package student;

import game.NodeStatus;

public interface NodeComponent {

    void addChildNode(NodeComponent nodeComponent);
    void removeChildNode();
    PriorityQueue<NodeComponent> getChildren();

}
