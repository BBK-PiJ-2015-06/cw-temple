package student.explore;

import student.*;

import java.util.*;

/**
 * A utilities class that generates the shortest path between two NodeStatus objects during
 * the exploration phase of the game. PathBuilder uses a simplified version of Dijkstra's
 * algorithm to compute the shortest path, using a common edge weight of 1.
 */
public class PathBuilder {

    /**
     * Generates the shortest path between two NodeStatus objects.
     * @param start the ID that represents the current NodeStatus object that the sprite resides upon
     * @param finish the ID that represents the destination NodeStatus object that the sprite needs to
     *               reach
     * @param map the current map that has been built during the exploration phase
     * @return a stack of NodeStatus ID's that represents the shortest path between the start and finish
     */
    public static Stack<Long> getPath(Long start, Long finish, Collection<GraphNode> map) {
        Map<Long, PathVertex> vertexMap = new HashMap<>();
        map.forEach(gn -> vertexMap.put(gn.getId(), new PathVertex(gn)));

        //Label starting vertex
        int ordering = 0;
        PathVertex current = vertexMap.get(start);
        current.setWorkingLabel(0);

        //Repeat labelling process until final destination is reached
        do {
            ordering ++;
            current.setOrderLabel(ordering);
            current.setFinalLabel(current.getWorkingLabel());

            //Sets the working labels (distance measure) for neighbours of current vertex
            final PathVertex finalCurrent = current;
            current.getNeighbours().stream().filter(vertexMap::containsKey)
                    .filter(l -> vertexMap.get(l).getWorkingLabel() == -1 || vertexMap.get(l).getWorkingLabel() > finalCurrent.getFinalLabel() + 1)
                    .forEach(l -> vertexMap.get(l).setWorkingLabel(finalCurrent.getFinalLabel() + 1));

            //Adds neighbours to priority queue ordered by working label
            student.PriorityQueue<PathVertex> queue = new PriorityQueueImpl<>();
            vertexMap.entrySet().stream().filter(e -> e.getValue().getFinalLabel() == -1 && e.getValue().getWorkingLabel() != -1)
                    .forEach(e -> queue.add(e.getValue(), e.getValue().getWorkingLabel()));

            //retrieves neighbour with lowest working label
            current = queue.poll();
        } while(!current.getNeighbours().contains(finish));

        current.setOrderLabel(ordering + 1);
        current.setFinalLabel(current.getWorkingLabel());

        Stack<Long> path = new Stack<>();
        path.push(current.getId());

        //Work backwards through the graph while building the shortest path
        while(!current.getNeighbours().contains(start)) {
            for(Long l : current.getNeighbours()) {
                if(vertexMap.containsKey(l)
                        && vertexMap.get(l).getOrderLabel() != 0
                        && current.getFinalLabel() - 1 == vertexMap.get(l).getFinalLabel()) {
                    path.push(l);
                    current = vertexMap.get(l);
                }
            }
        }
        return path;
    }
}