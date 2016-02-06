package student;

import game.NodeStatus;

public interface NodeComponent {

    void addChildNode();
    void removeChildNode();
    PriorityQueue<NodeStatus> getChildren();

}
