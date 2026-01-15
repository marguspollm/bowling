package ee.margus.bowling.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Frame {
    private List<Integer> rolls = new ArrayList<>();
    private int score;
    private boolean lastFrame = false;
    private boolean strike;
    private boolean spare;
    private boolean complete;

    public void addRoll(int pins) {
        if (rolls.size() == 1 && rolls.getFirst() + pins > 10) throw new RuntimeException("Too many pins");
        rolls.add(pins);
    }

    public boolean isStrike() {
        return !rolls.isEmpty() && rolls.getFirst() == 10;
    }

    public boolean isSpare() {
        return rolls.size() >= 2 && rolls.getFirst() + rolls.get(1) == 10;
    }

    public boolean isComplete() {
            if (isStrike()) return true;
            return rolls.size() == 2;
    }

    public void updateStrike() {
        strike = isStrike();
    }

    public void updateSpare() {
        spare = isSpare();
    }

    public void updateComplete() {
        complete = isComplete();
    }

    public void updateFields() {
        updateStrike();
        updateSpare();
        updateComplete();
    }
}
