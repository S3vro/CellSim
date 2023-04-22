package model.gui;

import model.algorithm.Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Grid extends JPanel implements MouseListener {
    private final int width;
    private final int height;

    private final int rowHeight;
    private final int columnWidth;

    private final int rows;
    private final int columns;

    private final Algorithm algorithm;

    private final Cell[][] cells;

    public Grid(int width, int height, int rows, int columns, Algorithm algorithm){

        this.width = width;
        this.height = height;

        this.rows = rows;
        this.columns = columns;

        rowHeight = height / rows;
        columnWidth = width / columns;

        addMouseListener(this);

        this.setPreferredSize(new Dimension(width,height));
        this.algorithm = algorithm;
        cells = new Cell[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                cells[i][j] = new Cell(new Point(i * columnWidth,j * rowHeight),columnWidth,rowHeight, this.algorithm.getColorMap());
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

    public void load(List<List<Integer>> list) {
        for (int x = 0; x < this.rows; x++) {
            for (int y = 0; y < this.columns; y++) {
                cells[x][y].setState(list.get(x).get(y));
            }
        }
        this.update();
    }

    public void save(File file) {
        try {
            StringBuilder saveStringBuilder = new StringBuilder();
            for (int x = 0; x < this.rows; x++) {
                for (int y = 0; y < this.columns; y++) {
                    saveStringBuilder.append(cells[x][y].getState()).append(" ");
                }
                saveStringBuilder.deleteCharAt(saveStringBuilder.length() - 1);
                saveStringBuilder.append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(saveStringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void step() {
        for (int x = 0; x < this.rows; x++) {
            for (int y = 0; y < this.columns; y++) {
                this.algorithm.step(this.cells, x,y);
            }
        }
        this.update();

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
        cell.setState((cell.getState() + 1) % this.algorithm.getStateCount());
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
