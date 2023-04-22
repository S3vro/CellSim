package gui;

import java.awt.*;

public final class Cell {

    private final Point position;
    private final int width;
    private final int height;
    private int state;
    private Color color;

    private final ColorMap colorMap;


    public Cell(Point position, int width, int height, ColorMap colorMap) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.colorMap = colorMap;
        this.color = Color.WHITE;
    }

    public void setState(int state) {
        this.state = state;
        this.color = colorMap.getColorForState(state);
    }

    public int getState() {
        return this.state;
    }

    public Color getColor() {
        return color;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(position.x, position.y, width,height);
        g.setColor(Color.BLACK);
        g.drawRect(position.x, position.y, width, height);
    }
}
