package student.ArchivedAttempts.EscapeAlgorithm4;

import game.EscapeState;
import game.Node;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class EscapeTask implements Callable {

    private EscapeState state;
    private Node currentNode;
    private int goldCollected;
    private int timeElapsed;
    private List<Node> route;
    private Stack<Node> wayFinderRoute;

    public EscapeTask(EscapeState state, Node startNode, int goldCollected, int timeElapsed, List<Node> route, Stack<Node> wayFinderRoute) {
        this.state = state;
        this.currentNode = startNode;
        this.goldCollected = goldCollected;
        this.timeElapsed = timeElapsed;
        this.route = route;
        this.wayFinderRoute =wayFinderRoute;
        this.wayFinderRoute.push(currentNode);
    }

    @Override
    public Object call() throws Exception {
        while(!currentNode.equals(state.getExit())) {
            route.add(currentNode);
            Collection<Node> neighbours = currentNode.getNeighbours();
            List<Node> newNeighbours = neighbours.stream().filter(n -> !route.contains(n)).collect(Collectors.toList());

            Node nextNode;
            if(!newNeighbours.isEmpty()) {
                Random random = new Random();
                int max = newNeighbours.size() - 1;
                nextNode = newNeighbours.get(random.nextInt(max + 1));
                goldCollected = goldCollected + nextNode.getTile().getGold();
                timeElapsed = timeElapsed + currentNode.getEdge(nextNode).length();
                wayFinderRoute.push(currentNode);
            } else {
                nextNode = wayFinderRoute.pop();
                timeElapsed = timeElapsed + currentNode.getEdge(nextNode).length();
            }
            currentNode = nextNode;
        }
        return new EscapeTaskResult(goldCollected, timeElapsed, route);
    }
}
