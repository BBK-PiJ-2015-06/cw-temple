package student;

import game.ExplorationState;
import game.NodeStatus;

import java.util.*;
import java.util.stream.Collectors;

public class Explore {

    private ExplorationState state;
    private Map<Long, GraphNode> wholeGraph;
    private Random randomGenerator;


    public Explore(ExplorationState state) {
        this.state = state;
        wholeGraph = new HashMap<>();
        randomGenerator = new Random();
    }

    public void findOrb() {
        moveOffEntrance();
        while(state.getDistanceToTarget() != 0) {
            addNeighbours();
            GraphNode destination = getDestination();
            moveSprite(destination);
        }
    }

    private void moveOffEntrance() {
        List<NodeStatus> neighbours = new ArrayList<>();
        neighbours.addAll(state.getNeighbours());
        NodeStatus destination = neighbours.get(0);
        addGraphNode(destination);
        state.moveTo(destination.getId());
        markAsVisited(wholeGraph.get(destination.getId()));
    }

    private void markAsVisited(GraphNode graphNode) {
        graphNode.setVisited(true);
    }

    private void addGraphNode(NodeStatus nodeStatus) {
        GraphNode node = new GraphNode(nodeStatus);
        wholeGraph.put(nodeStatus.getId(), node);
    }

    private void addNeighbours() {
        Collection<NodeStatus> neighbours = state.getNeighbours();
        GraphNode currentNode = wholeGraph.get(state.getCurrentLocation());
        for(NodeStatus ns : neighbours) {
            if(!wholeGraph.containsKey(ns.getId())) {
                addGraphNode(ns);
            }
            currentNode.addNeighbour(wholeGraph.get(ns.getId()));
        }
    }

    private GraphNode getDestination() {
        List<GraphNode> rankedNodes = new ArrayList<>();
        for(Map.Entry<Long, GraphNode> entry : wholeGraph.entrySet()) {
            if(!entry.getValue().isVisited()) {
                rankedNodes.add(entry.getValue());
            }
        }
        rankedNodes.sort((n1, n2) -> n1.getNode().getDistanceToTarget() - n2.getNode().getDistanceToTarget());
        int minDistance = rankedNodes.get(0).getNode().getDistanceToTarget();
        List<GraphNode> equiDistantNodes = rankedNodes
                                            .stream()
                                                .filter(n -> n.getNode().getDistanceToTarget() == minDistance)
                                                    .collect(Collectors.toList());
        if(equiDistantNodes.size() == 1) {
            return equiDistantNodes.get(0);
        } else {
            return equiDistantNodes.get(randomGenerator.nextInt(equiDistantNodes.size()));
        }
    }

    private void moveSprite(GraphNode destination) {}
}
