package student;

import game.Node;

import java.util.List;

public class EscapeTaskResult {

    private int goldCollected;
    private int timeElapsed;
    private List<Node> route;

    public EscapeTaskResult(int goldCollected, int timeElapsed, List<Node> route) {
        this.goldCollected = goldCollected;
        this.timeElapsed = timeElapsed;
        this.route = route;
    }

    public int getGoldCollected() {
        return goldCollected;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public List<Node> getRoute() {
        return route;
    }
}
