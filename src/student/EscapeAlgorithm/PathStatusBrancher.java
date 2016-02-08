package student.EscapeAlgorithm;

import game.Node;

import java.util.ArrayList;
import java.util.List;

public class PathStatusBrancher {

    private PathStatus existing;
    private List<Node> neighboursToAdd;

    public PathStatusBrancher(PathStatus existing, List<Node> neighboursToAdd) {
        this.existing = existing;
        this.neighboursToAdd = neighboursToAdd;
    }

    public List<PathStatus> branch() {
        List<PathStatus> output = new ArrayList<>();
        if(!existing.getPath().containsAll(neighboursToAdd)) {
            for(Node node : neighboursToAdd) {
                if(!existing.getPath().contains(node)) {
                    PathStatus updated = new PathStatus(existing.getPath().get(0));
                    for(int i = 1; i < existing.getPath().size(); i++) {
                        updated.addNode(existing.getPath().get(i));
                    }
                    updated.addNode(node);
                    output.add(updated);
                }
            }
        }
        return output;
    }

}
