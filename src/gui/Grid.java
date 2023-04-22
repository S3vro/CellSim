package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Grid extends JPanel implements MouseListener {
    private final int width;
    private final int height;

    private final int rowHeight;
    private final int columnWidth;

    private final int rows;
    private final int columns;

    private final int states;

    private final Cell[][] cells;

    public Grid(int width, int height, int rows, int columns, int states){

        this.width = width;
        this.height = height;

        this.rows = rows;
        this.columns = columns;

        rowHeight = height / rows;
        columnWidth = width / columns;

        addMouseListener(this);

        this.setPreferredSize(new Dimension(width,height));
        this.states = states;
        ColorMap map = new ColorMap(states);
        cells = new Cell[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j] = new Cell(new Point(i * columnWidth,j * rowHeight),columnWidth,rowHeight, map);
            }
        }
    }

    public void update() {
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width,height);
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLACK);

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j].draw(g);
            }
        }
    }

    public void reset() {
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = cells[i][j];
                cell.setState(0);
                update();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point mousePos = new Point(mouseEvent.getX(),mouseEvent.getY());
        Cell cell = cells[(int)(mousePos.x/columnWidth)][(int)(mousePos.y/rowHeight)];
        cell.setState((cell.getState() + 1) % this.states);
        this.update();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
