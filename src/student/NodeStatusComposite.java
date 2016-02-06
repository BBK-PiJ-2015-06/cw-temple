package student;

import game.NodeStatus;

public class NodeStatusComposite implements NodeComponent{

    PriorityQueue<NodeComponent> childrenNodes;
    NodeStatus nodeStatus;

    public NodeStatusComposite(NodeStatus nodeStatus) {
        this.nodeStatus = nodeStatus;
        this.childrenNodes = new PriorityQueueImpl<>();
    }

    @Override
    public void addChildNode(NodeComponent nodeComponent) {
        childrenNodes.add(nodeComponent, 0 - this.nodeStatus.getDistanceToTarget());
    }

    @Override
    public void removeChildNode() {
        childrenNodes.poll();
    }

    @Override
    public PriorityQueue<NodeComponent> getChildren() {
        return childrenNodes;
    }

}
