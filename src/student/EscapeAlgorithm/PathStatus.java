package student.EscapeAlgorithm;

import game.Edge;
import game.Node;

import java.util.ArrayList;
import java.util.List;

public class PathStatus {

    private List<Node> path;
    private int pathSize = 0;
    private int timeTaken = 0;
    private int goldEnRoute = 0;

    public PathStatus(Node source) {
        path = new ArrayList<>();
        path.add(source);
        pathSize++;
    }

    public void addNode(Node nextNode) {
        Node currentNode = path.get(pathSize - 1);
        if(!currentNode.getNeighbours().contains(nextNode)) {
            throw new IllegalArgumentException("Next node is not adjacent to current node");
        }
        path.add(nextNode);
        Edge edge = currentNode.getEdge(nextNode);
        timeTaken = timeTaken + edge.length();
        goldEnRoute = goldEnRoute + nextNode.getTile().getOriginalGold();
        pathSize++;
    }

    public List<Node> getPath() {
        return path;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public int getGoldEnRoute() {
        return goldEnRoute;
    }
}
