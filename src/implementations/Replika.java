package implementations;

import model.algorithm.Algorithm;
import model.algorithm.ModuloTransition;
import model.algorithm.StatePosition;
import model.algorithm.Transition;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Replika implements Algorithm {
    private static final List<Transition> TRANSITION_LIST = List.of(
            new ModuloTransition( 7,
                    new StatePosition(1, -1, 0),
                    new StatePosition(1, 0, -1),
                    new StatePosition(1, 1, 0),
                    new StatePosition(1, 0, 1)
            ));

    @Override
    public int getStateCount() {
        return 7;
    }

    @Override
    public List<Transition> getTransitions() {
        return TRANSITION_LIST;
    }

    @Override
    public Map<Integer, Color> getColorMap() {
        return Map.of(0, new Color(255 / 7 * 7, 255 / 7 * 7, 255 / 7 * 7),
                1, new Color(255 / 7 * 6, 255 / 7 * 6, 255 / 7 * 6),
                2, new Color(255 / 7 * 5, 255 / 7 * 5, 255 / 7 * 5),
                3, new Color(255 / 7 * 4, 255 / 7 * 4, 255 / 7 * 4),
                4, new Color(255 / 7 * 3, 255 / 7 * 3, 255 / 7 * 3),
                5, new Color(255 / 7 * 2, 255 / 7 * 2, 255 / 7 * 2),
                6, new Color(255 / 7 * 1, 255 / 7 * 1, 255 / 7 * 1));
    }
}
