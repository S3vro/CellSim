package model.gui;

import java.awt.*;
import java.util.Map;

public final class Cell {

    private final Point position;
    private final int width;
    private final int height;
    private int state;
    private int newState;
    private Color color;

    private final Map<Integer, Color> colorMap;


    public Cell(Point position, int width, int height, Map<Integer, Color> colorMap) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.colorMap = colorMap;
        this.color = Color.WHITE;
        this.state = 0;
        this.newState = 0;
    }

    public void setState(int state) {
        this.newState = state;
    }

    public void update() {
        this.state = this.newState;
        this.color = this.colorMap.get(this.state);
    }

    public int getState() {
        return this.state;
    }

    public void draw(Graphics g) {
        this.update();
        g.setColor(this.color);
        g.fillRect(this.position.x, this.position.y, this.width,this.height);
        g.setColor(Color.BLACK);
        g.drawRect(this.position.x, this.position.y, this.width, this.height);
    }
}
