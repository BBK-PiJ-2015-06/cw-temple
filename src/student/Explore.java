package student;

import game.ExplorationState;

import java.util.Stack;

public class Explore {

    private ExplorationState state;
    private Stack<Long> currentPath;

    public Explore(ExplorationState state) {
        this.state = state;
    }

    public void findOrb() {
        while(state.getDistanceToTarget() != 0) {
            
        }
    }

}
