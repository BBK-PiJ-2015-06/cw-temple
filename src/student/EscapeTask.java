package student;

import game.EscapeState;
import game.Node;

import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;

public class EscapeTask implements Callable {

    private EscapeState state;
    private Node currentNode;
    private int goldCollected;
    private int timeElapsed;
    private List<Node> route;
    private Stack wayFinderRoute;

    public EscapeTask(EscapeState state, Node currentNode, int goldCollected, int timeElapsed, List<Node> route, Stack wayFinderRoute) {
        this.state = state;
        this.currentNode = currentNode;
        this.goldCollected = goldCollected;
        this.timeElapsed = timeElapsed;
        this.route = route;
        this.wayFinderRoute =wayFinderRoute;
    }

    @Override
    public Object call() throws Exception {
        Collection<Node> neighbours = currentNode.getNeighbours();
        System.out.println(neighbours);

        return new EscapeTaskResult(goldCollected, timeElapsed, route);
    }
}
