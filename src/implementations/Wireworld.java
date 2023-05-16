package implementations;

import model.algorithm.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Wireworld implements Algorithm {

    private static final Transition COUNTING_STATES = new CountingTransition(2, 2, List.of(1, 2),
            new StatePosition(3, 0, 0),
            new StatePosition(-1, -1, 0),
            new StatePosition(-1, 0, -1),
            new StatePosition(-1, -1, -1),
            new StatePosition(-1, 1, 0),
            new StatePosition(-1, 1, 1),
            new StatePosition(-1, 0, 1),
            new StatePosition(-1, -1, 1),
            new StatePosition(-1, 1, -1)
    );

    private static final List<Transition> TRANSITION_LIST = List.of(
            new Transition(1,
                    new StatePosition(1, 0, 0),
                    new StatePosition(-1, -1, 0),
                    new StatePosition(-1, 0, -1),
                    new StatePosition(-1, -1, -1),
                    new StatePosition(-1, 1, 0),
                    new StatePosition(-1, 1, 1),
                    new StatePosition(-1, 0, 1),
                    new StatePosition(-1, -1, 1),
                    new StatePosition(-1, 1, -1)
            ),
            new Transition(0,
                    new StatePosition(2, 0, 0),
                    new StatePosition(-1, -1, 0),
                    new StatePosition(-1, 0, -1),
                    new StatePosition(-1, -1, -1),
                    new StatePosition(-1, 1, 0),
                    new StatePosition(-1, 1, 1),
                    new StatePosition(-1, 0, 1),
                    new StatePosition(-1, -1, 1),
                    new StatePosition(-1, 1, -1)
            ),
            new Transition(3,
                    new StatePosition(0, 0, 0),
                    new StatePosition(-1, -1, 0),
                    new StatePosition(-1, 0, -1),
                    new StatePosition(-1, -1, -1),
                    new StatePosition(-1, 1, 0),
                    new StatePosition(-1, 1, 1),
                    new StatePosition(-1, 0, 1),
                    new StatePosition(-1, -1, 1),
                    new StatePosition(-1, 1, -1)
            ),
            COUNTING_STATES,
            new ElseTransition(3, COUNTING_STATES,
                    new StatePosition(3, 0, 0),
                    new StatePosition(-1, -1, 0),
                    new StatePosition(-1, 0, -1),
                    new StatePosition(-1, -1, -1),
                    new StatePosition(-1, 1, 0),
                    new StatePosition(-1, 1, 1),
                    new StatePosition(-1, 0, 1),
                    new StatePosition(-1, -1, 1),
                    new StatePosition(-1, 1, -1)
            )
    );

    @Override
    public int getStateCount() {
        return 4;
    }

    @Override
    public List<Transition> getTransitions() {
        return TRANSITION_LIST;
    }

    @Override
    public Map<Integer, Color> getColorMap() {
        return Map.of(0, Color.BLACK, 1, Color.WHITE, 2, Color.BLUE, 3, Color.ORANGE);
    }
}
