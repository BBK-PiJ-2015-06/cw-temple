package student.ArchivedAttempts.EscapeAlgorithm4;

import game.EscapeState;
import game.Node;
import student.PriorityQueue;
import student.PriorityQueueImpl;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


public class Explorer {

    private static final int MYTHREADS = 30;

    /**
     * Escape from the cavern before the ceiling collapses, trying to collect as much
     * gold as possible along the way. Your solution must ALWAYS escape before time runs
     * out, and this should be prioritized above collecting gold.
     * <p>
     * You now have access to the entire underlying graph, which can be accessed through EscapeState.
     * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
     * will return a collection of all nodes on the graph.
     * <p>
     * Note that time is measured entirely in the number of steps taken, and for each step
     * the time remaining is decremented by the weight of the edge taken. You can use
     * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
     * on your current tile (this will fail if no such gold exists), and moveTo() to move
     * to a destination node adjacent to your current node.
     * <p>
     * You must return from this function while standing at the exit. Failing to do so before time
     * runs out or returning from the wrong location will be considered a failed run.
     * <p>
     * You will always have enough time to escape using the shortest path from the starting
     * position to the exit, although this will not collect much gold.
     *
     * @param state the information available at the current state
     */
    public void escape(EscapeState state) {
        ExecutorService executorService = Executors.newFixedThreadPool(MYTHREADS);
        Set<EscapeTaskResult> paths = new HashSet<>();

        for(int i = 0; i < state.getVertices().size(); i++) {
            Future future = executorService.submit(new EscapeTask(state, state.getCurrentNode(), 0, 0, new ArrayList<>(), new Stack<>()));
            try {
                EscapeTaskResult result = (EscapeTaskResult)future.get();
                paths.add(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        Set<EscapeTaskResult> correctPaths = paths.stream().filter(p -> p.getTimeElapsed() < state.getTimeRemaining()).collect(Collectors.toSet());

        PriorityQueue<EscapeTaskResult> orderedPaths = new PriorityQueueImpl<>();

        for(EscapeTaskResult etr : correctPaths) {
            orderedPaths.add(etr, 0 - etr.getGoldCollected());
        }

        List<Node> pathToTake = orderedPaths.peek().getRoute();
        for(int i = 1; i < pathToTake.size(); i++) {
            state.moveTo(pathToTake.get(i));
            if(state.getCurrentNode().getTile().getGold() > 0) {
                state.pickUpGold();
            }
        }
        state.moveTo(state.getExit());
    }
}
