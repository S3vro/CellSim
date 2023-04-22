package model.algorithm;

import model.gui.Cell;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public interface Algorithm {

    int getStateCount();
    default void step(Cell[][] cells, int x, int y) {
        getTransitions().forEach(transition -> {
            if (transition.circumstancesApply(cells, x, y)) {
                cells[x][y].setState(transition.getTransitionToState());
            }
        });
    };
    List<Transition> getTransitions();

    default Map<Integer, Color> getColorMap(){
        HashMap<Integer, Color> colorMap = new HashMap<>();
        for (int i = 1; i < getStateCount(); i++) {
            Random random = new Random();
            float red = random.nextFloat() / 2f + 0.5f;
            float green = random.nextFloat() / 2f + 0.5f;
            float blue = random.nextFloat() / 2f + 0.5f;
            colorMap.put(i, new Color(red, green, blue));
        }
        return colorMap;
    };

}
