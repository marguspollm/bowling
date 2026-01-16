package ee.margus.bowling.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private String id;
    private int totalScore;
    private List<Frame> frames = createAllFrames();
    private Player player;

    private List<Frame> createAllFrames(){
        List<Frame> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(new Frame());
        }
        Frame lastFrame = new Frame();
        lastFrame.setLastFrame(true);
        list.add(lastFrame);
        return list;
    }

    public void setFrame(int pins) {
        Frame currentFrame = frames.stream().filter(frame -> !frame.isComplete()).toList().getFirst();
        currentFrame.addRoll(pins);
    }

    public void calculateFrameScores() {
        var framesWithRolls = frames.stream().filter(frame -> !frame.getRolls().isEmpty()).toList();
        int frameSize = framesWithRolls.size();
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

    public boolean isFinished(){
        return frames.getLast().isComplete();
    }
}
