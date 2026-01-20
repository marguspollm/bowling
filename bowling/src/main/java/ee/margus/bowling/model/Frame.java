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

    public void addRoll(int pins) {
        if (pins > 10 || pins < 0)
            throw new RuntimeException("Invalid pin number");
        if (isComplete())
            throw new RuntimeException("Frame is complete");
        if (isLastFrame()) {
            if (rolls.size() == 1 &&
                    rolls.getFirst() < 10 &&
                    rolls.getFirst() + pins > 10)
                throw new RuntimeException("Too many pins");
            if (rolls.size() == 2) {
                if (!isStrike() && !isSpare())
                    throw new RuntimeException("Third roll is not allowed!");
                if (rolls.getFirst() == 10 &&
                        rolls.get(1) < 10 &&
                        rolls.get(1) + pins > 10)
                    throw new RuntimeException("Too many pins");
            }
        } else {
            if (rolls.size() == 1 &&
                    rolls.getFirst() + pins > 10)
                throw new RuntimeException("Too many pins");
        }
        rolls.add(pins);
    }

    public boolean isStrike() {
        return !rolls.isEmpty() &&
                rolls.getFirst() == 10;
    }

    public boolean isSpare() {
        return rolls.size() >= 2 &&
                rolls.getFirst() + rolls.get(1) == 10;
    }

    public boolean isComplete() {
        if (isLastFrame()) {
            if (rolls.size() == 3) return true;
            return rolls.size() == 2 &&
                    rolls.getFirst() + rolls.get(1) < 10;
        } else {
            if (isStrike()) return true;
            return rolls.size() == 2;
        }
    }
}
