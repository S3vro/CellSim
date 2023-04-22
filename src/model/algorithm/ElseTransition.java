package model.algorithm;

import model.gui.Cell;

public class ElseTransition extends Transition{

    private final Transition transition;
    public ElseTransition(int transitionToState, Transition transition, StatePosition... transitions) {
        super(transitionToState, transitions);
        this.transition = transition;
    }

    @Override
    public boolean circumstancesApply(Cell[][] cells, int x, int y) {
        return !this.transition.circumstancesApply(cells, x, y) && super.circumstancesApply(cells,x,y);
    }
}
