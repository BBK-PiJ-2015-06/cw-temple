package student;

import game.NodeStatus;

public interface NodeComponent {

    void addChildNode(NodeComponent nodeComponent, int dist);
    void removeChildNode();
    PriorityQueue<NodeComponent> getChildren();
    long getId();

}
