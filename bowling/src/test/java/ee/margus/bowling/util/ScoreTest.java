package ee.margus.bowling.util;

import ee.margus.bowling.model.Frame;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreTest {

    @Test
    void whenOpenFrames_thenScoredCorrectly() {
        Frame frame1 = new Frame();
        frame1.addRoll(2);
        frame1.addRoll(3);

        Frame frame2 = new Frame();
        frame2.addRoll(3);
        frame2.addRoll(4);

        List<Integer> scores = Score.scoreFrames(List.of(frame1, frame2));
        assertEquals(List.of(5, 12), scores);
    }

    @Test
    void whenSpare_thenAddNextRollScore() {
        Frame frame1 = new Frame();
        frame1.addRoll(2);
        frame1.addRoll(8);

        Frame frame2 = new Frame();
        frame2.addRoll(3);
        frame2.addRoll(4);

        List<Integer> scores = Score.scoreFrames(List.of(frame1, frame2));
        assertEquals(List.of(13, 20), scores);
    }

    @Test
    void whenStrike_thenAddNextRollScore() {
        Frame frame1 = new Frame();
        frame1.addRoll(10);

        Frame frame2 = new Frame();
        frame2.addRoll(3);
        frame2.addRoll(4);

        List<Integer> scores = Score.scoreFrames(List.of(frame1, frame2));
        assertEquals(List.of(17, 24), scores);
    }
}