package model.algorithm;

import model.gui.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transition {

    private final int transitionToState;
    private final List<StatePosition> transitionCircumstances = new ArrayList<>();

    public Transition(int transitionToState, StatePosition... transitions) {
        this.transitionToState = transitionToState;
        Collections.addAll(this.transitionCircumstances, transitions);
    }

    public int getTransitionToState() {
        return transitionToState;
    }

    public boolean circumstancesApply(Cell[][] cells, int x, int y) {
        for (StatePosition transitionCircumstance : this.transitionCircumstances) {
            if (!this.isLegalTile(cells, x,y,transitionCircumstance)) {
                return false;
            }
            if (transitionCircumstance.state() == -1) {
                continue;
            }
            if (transitionCircumstance.state() != cells[x + transitionCircumstance.x()][y + transitionCircumstance.y()].getState()) {
                //System.out.printf("In Trans looking at: (%d,%d) => %d%n", x + transitionCircumstance.x(), y + transitionCircumstance.y(), transitionToState);
                return false;
            }
        }

        return true;
    }

    protected List<StatePosition> getStatePositions() {
        return this.transitionCircumstances;
    }

    protected boolean isLegalTile(Cell[][] cells, int x,int y, StatePosition transitionCircumstance) {
        return y + transitionCircumstance.y() < cells.length && y + transitionCircumstance.y() >= 0
                && x + transitionCircumstance.x() < cells[0].length && x + transitionCircumstance.x() >= 0;
    }
}
