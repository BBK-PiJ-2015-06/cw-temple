package student.explore;

import student.*;

import java.util.*;

public class PathBuilder {

    public static Stack<Long> getPath(Long start, Long finish, Collection<GraphNode> map) {
        Map<Long, PathVertex> vertexMap = new HashMap<>();
        map.forEach(gn -> vertexMap.put(gn.getId(), new PathVertex(gn)));

        int ordering = 0;
        PathVertex current = vertexMap.get(start);
        current.setWorkingLabel(0);

        do {
            ordering ++;
            current.setOrderLabel(ordering);
            current.setFinalLabel(current.getWorkingLabel());

            final PathVertex finalCurrent = current;
            current.getNeighbours().stream().filter(vertexMap::containsKey)
                    .filter(l -> vertexMap.get(l).getWorkingLabel() == -1 || vertexMap.get(l).getWorkingLabel() > finalCurrent.getFinalLabel() + 1)
                    .forEach(l -> vertexMap.get(l).setWorkingLabel(finalCurrent.getFinalLabel() + 1));

            student.PriorityQueue<PathVertex> queue = new PriorityQueueImpl<>();
            vertexMap.entrySet().stream().filter(e -> e.getValue().getFinalLabel() == -1 && e.getValue().getWorkingLabel() != -1)
                    .forEach(e -> queue.add(e.getValue(), e.getValue().getWorkingLabel()));

            current = queue.poll();
        } while(!current.getNeighbours().contains(finish));

        current.setOrderLabel(ordering + 1);
        current.setFinalLabel(current.getWorkingLabel());

        Stack<Long> path = new Stack<>();
        path.push(current.getId());

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