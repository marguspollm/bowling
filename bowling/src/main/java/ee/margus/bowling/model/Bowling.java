package ee.margus.bowling.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bowling {
    private int totalScore;
    private List<Frame> frames = new ArrayList<>();


    public void setFrames(int pins) {
        createFrame();
        int current = frames.size() - 1;
        frames.get(current).addRoll(pins);
        calculateFrameScores();
    }

    private void createFrame() {
        if (frames.size() == 10 && frames.getLast().isComplete()) throw new RuntimeException("Max frames reached");
        if (frames.isEmpty() || frames.getLast().isComplete()) {
            frames.add(new Frame());
            if (frames.size() == 10) frames.getLast().setLastFrame(true);
        }
    }

    public void calculateFrameScores() {
        int frameSize = frames.size();
        int score = 0;
        for (int i = 0; i < frameSize; i++) {
            Frame frame = frames.get(i);
            List<Integer> rolls = frame.getRolls();
            score += rolls.stream().mapToInt(Integer::intValue).sum();

            if (!frame.isLastFrame()) {
                if (frame.isStrike()) {
                    score += getStrikeScore(i);
                } else if (frame.isSpare()) {
                    score += getSpareScore(i);
                }
            }
            frame.setScore(score);
        }
        totalScore = score;
    }

    private int getSpareScore(int i) {
        List<Integer> next = getNextRolls(i, 1);
        return next.stream().mapToInt(Integer::intValue).sum();
    }

    private int getStrikeScore(int i) {
        List<Integer> next = getNextRolls(i, 2);
        return next.stream().mapToInt(Integer::intValue).sum();
    }

    private List<Integer> getNextRolls(int index, int count) {
        List<Integer> result = new ArrayList<>();
        for (int i = index + 1; i < frames.size(); i++) {
            for (int roll : frames.get(i).getRolls()) {
                result.add(roll);
                if (result.size() == count) return result;
            }
        }
        return result;
    }
}
