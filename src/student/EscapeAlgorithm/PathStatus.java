package student.EscapeAlgorithm;

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
}
