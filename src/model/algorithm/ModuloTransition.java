package model.algorithm;

import model.gui.Cell;

import java.util.List;

public class ModuloTransition extends Transition {
    private int mod;

    public ModuloTransition(int mod, StatePosition... transitions) {
        super(0, transitions);

        this.mod = mod;
    }

    @Override
    public boolean circumstancesApply(Cell[][] cells, int x, int y) {
        int sum = 0;
        for (StatePosition statePosition : super.getStatePositions()) {
            if (!super.isLegalTile(cells, x, y, statePosition)) {
                return false;
            }
            if (statePosition.x() == 0 && statePosition.y() == 0) {
                if (cells[x][y].getState() != statePosition.state()) {
                    return false;
                }
            }
            sum += cells[x + statePosition.x()][y + statePosition.y()].getState();
        }
        this.transitionToState = sum % mod;
        return true;
    }
}
