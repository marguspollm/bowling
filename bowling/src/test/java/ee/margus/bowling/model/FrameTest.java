package ee.margus.bowling.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void givenValidPins_whenAddRoll_thenAddPinsToRoll() {
        Frame frame = new Frame();
        frame.addRoll(5);
        frame.addRoll(4);

        assertTrue(frame.isComplete());
        assertEquals(List.of(5, 4), frame.getRolls());
    }

    @Test
    void givenInvalidPins_whenAddRoll_thenThrowException() {
        Frame frame = new Frame();
        frame.addRoll(5);

        assertThrows(RuntimeException.class, () -> frame.addRoll(6));
    }

    @Test
    void whenFrameCreated() {
        Frame frame = new Frame();

        assertFalse(frame.isStrike());
        assertFalse(frame.isComplete());
    }

    @Test
    void givenStrike_thenFrameIsStrikeAndComplete() {
        Frame frame = new Frame();
        frame.addRoll(10);

        assertTrue(frame.isStrike());
        assertTrue(frame.isComplete());
    }

    @Test
    void whenSpare_thenFrameIsSpareAndComplete() {
        Frame frame = new Frame();
        frame.addRoll(3);
        frame.addRoll(7);

        assertTrue(frame.isSpare());
        assertTrue(frame.isComplete());
    }

    @Test
    void whenOneRoll_thenCompletedIsFalse() {
        Frame frame = new Frame();
        frame.addRoll(2);

        assertFalse(frame.isComplete());
        assertFalse(frame.isSpare());
        assertFalse(frame.isStrike());
    }

    @Test
    void givenStrike_whenLastFrame_thenAllowThreeRolls() {
        Frame frame = new Frame();
        frame.setLastFrame(true);
        frame.addRoll(10);
        frame.addRoll(10);
        frame.addRoll(10);

        assertTrue(frame.isComplete());
    }

    @Test
    void givenSpare_whenLastFrame_thenAllowThreeRolls() {
        Frame frame = new Frame();
        frame.setLastFrame(true);
        frame.addRoll(6);
        frame.addRoll(4);
        frame.addRoll(5);

        assertTrue(frame.isComplete());
    }

    @Test
    void givenTwoNormalRolls_whenLastFrame_thenAllowTwoRolls() {
        Frame frame = new Frame();
        frame.setLastFrame(true);
        frame.addRoll(3);
        frame.addRoll(5);

        assertTrue(frame.isComplete());
    }

    @Test
    void givenInvalidPins_whenLastFrame_throwsException() {
        Frame frame = new Frame();
        frame.setLastFrame(true);
        frame.addRoll(7);

        assertThrows(RuntimeException.class, () -> frame.addRoll(6));
    }
}