package implementations;

import model.algorithm.Algorithm;
import model.algorithm.CountingTransition;
import model.algorithm.StatePosition;
import model.algorithm.Transition;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class GameOfLife implements Algorithm {

    private static final List<Transition> TRANSITION_LIST = List.of(
        new CountingTransition(1,1,List.of(3),
                new StatePosition(0, 0, 0),
                new StatePosition(-1, 0),
                new StatePosition(0, -1),
                new StatePosition(-1, -1),
                new StatePosition(1, 0),
                new StatePosition(1, 1),
                new StatePosition(0, 1),
                new StatePosition(-1, 1),
                new StatePosition(1, -1)),
            new CountingTransition(0,1,List.of(2,5,6,7,8,9),
                    new StatePosition(1, 0, 0),
                    new StatePosition(-1, 0),
                    new StatePosition(0, -1),
                    new StatePosition(-1, -1),
                    new StatePosition(1, 0),
                    new StatePosition(1, 1),
                    new StatePosition(0, 1),
                    new StatePosition(-1, 1),
                    new StatePosition(1, -1))
    );
    @Override
    public int getStateCount() {
        return 2;
    }

    @Override
    public List<Transition> getTransitions() {
        return TRANSITION_LIST;
    }

    @Override
    public Map<Integer, Color> getColorMap() {
        return Map.of(0, Color.WHITE, 1, Color.BLACK);
    }
}
