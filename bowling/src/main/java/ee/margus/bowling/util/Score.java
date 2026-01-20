package ee.margus.bowling.util;

import ee.margus.bowling.model.Frame;

import java.util.ArrayList;
import java.util.List;

public class Score {
    public static List<Integer> scoreFrames(List<Frame> frames) {
        List<Integer> scores = new ArrayList<>();
        int total = 0;

        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.getRolls().isEmpty()) break;

            int score = frame.getRolls().stream().mapToInt(Integer::intValue).sum();

            if (!frame.isLastFrame()) {
                if (frame.isStrike()) total += getStrikeScore(frames, i);
                else if (frame.isSpare()) total += getSpareScore(frames, i);
            }
            total += score;
            scores.add(total);
        }
        return scores;
    }

    private static int getSpareScore(List<Frame> frames, int i) {
        List<Integer> next = getNextRolls(frames, i, 1);
        return next.stream().mapToInt(Integer::intValue).sum();
    }

    private static int getStrikeScore(List<Frame> frames, int i) {
        List<Integer> next = getNextRolls(frames, i, 2);
        return next.stream().mapToInt(Integer::intValue).sum();
    }

    private static List<Integer> getNextRolls(List<Frame> frames, int index, int count) {
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
