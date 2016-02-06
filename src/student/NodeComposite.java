package student;

public class NodeComposite implements NodeComponent{

    PriorityQueue<NodeComponent> childrenNodes;
    long id;

    public NodeComposite(long id) {
        this.id = id;
        this.childrenNodes = new PriorityQueueImpl<>();
    }

    @Override
    public void addChildNode(NodeComponent nodeComponent, int dist) {
        childrenNodes.add(nodeComponent, 0 - dist);
    }

    @Override
    public void removeChildNode() {
        childrenNodes.poll();
    }

    @Override
    public PriorityQueue<NodeComponent> getChildren() {
        return childrenNodes;
    }

    @Override
    public long getId() {
        return id;
    }

}
