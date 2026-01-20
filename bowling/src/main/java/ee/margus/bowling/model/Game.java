package ee.margus.bowling.model;

import ee.margus.bowling.util.Score;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {
    private static final int TOTAL_FRAMES = 10;

    private String id;
    private int totalScore;
    private List<Frame> frames;
    private Player player;

    public Game() {
        this.frames = createAllFrames();
    }

    private List<Frame> createAllFrames() {
        List<Frame> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(new Frame());
        }
        Frame lastFrame = new Frame();
        lastFrame.setLastFrame(true);
        list.add(lastFrame);
        return list;
    }

    public void addRoll(int pins) {
        if (isFinished())
            throw new RuntimeException("Game is already finished!");
        Frame current = getCurrentFrame();
        current.addRoll(pins);
    }

    private Frame getCurrentFrame() {
        return frames.stream()
                .filter(frame ->
                        !frame.isComplete()).findFirst()
                .orElseThrow();
    }

    public void calculateFrameScores() {
        List<Integer> scores = Score.scoreFrames(frames);
        for (int i = 0; i < scores.size(); i++) {
            frames.get(i).setScore(scores.get(i));
        }
        totalScore = scores.isEmpty() ? 0 : scores.getLast();
    }

    public boolean isFinished() {
        return frames.getLast().isComplete();
    }
}
