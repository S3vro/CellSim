package gui;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public final class ColorMap {

    private final HashMap<Integer, Color> colorMap = new HashMap<>();

    public ColorMap(int states) {
        colorMap.put(0, Color.WHITE);
        for (int i = 1; i < states; i++) {
            Random random = new Random();
            float red = random.nextFloat() / 2f + 0.5f;
            float green = random.nextFloat() / 2f + 0.5f;
            float blue = random.nextFloat() / 2f + 0.5f;
            colorMap.put(i, new Color(red, green, blue));
        }
    }

    public Color getColorForState(int state) {
        return this.colorMap.get(state);
    }
}
