package model.algorithm;

import model.gui.Cell;

import java.util.List;

public class CountingTransition extends Transition {
    private final int countingState;
    private final List<Integer> requiredCount;

    public CountingTransition(int transitionToState, int countingState, List<Integer> requiredCount, StatePosition... transitions) {
        super(transitionToState, transitions);
        this.countingState = countingState;
        this.requiredCount = requiredCount;
    }

    @Override
    public boolean circumstancesApply(Cell[][] cells, int x, int y) {
        for (StatePosition statePosition : super.getStatePositions()) {
            if (!super.isLegalTile(cells, x,y,statePosition)) {
                return false;
            }
            if (statePosition.x() == 0 && statePosition.y() == 0) {
                if (cells[x][y].getState() != statePosition.state()) {
                    return false;
                }
            }
        }
        return this.requiredCount.contains((int) super.getStatePositions().stream().
                filter(statePosition ->
                        cells[x + statePosition.x()][y + statePosition.y()].getState() == this.countingState)
                .count());
    }
}
