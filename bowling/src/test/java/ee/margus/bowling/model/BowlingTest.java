package ee.margus.bowling.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingTest {

    @Test
    void givenNormalRolls_thenScoreIsPinsSum() {
        Bowling bowling = new Bowling();
        bowling.setFrames(4);
        bowling.setFrames(1);
        bowling.setFrames(3);
        bowling.setFrames(1);

        assertEquals(9, bowling.getTotalScore());
    }

    @Test
    void givenSpare_thenSpareScoreIsFromNextRoll() {
        Bowling bowling = new Bowling();
        bowling.setFrames(4);
        bowling.setFrames(6);
        bowling.setFrames(3);
        bowling.setFrames(1);

        assertEquals(17, bowling.getTotalScore());
    }

    @Test
    void givenStrike_thenStrikeScoreIsFromNextTwoRolls() {
        Bowling bowling = new Bowling();
        bowling.setFrames(10);
        bowling.setFrames(6);
        bowling.setFrames(3);

        assertEquals(28, bowling.getTotalScore());
    }

    @Test
    void givenMultipleStrikes_thenStrikeScoreIsFromNextTwoRolls() {
        Bowling bowling = new Bowling();
        bowling.setFrames(10);
        bowling.setFrames(10);
        bowling.setFrames(10);

        assertEquals(60, bowling.getTotalScore());
    }

    @Test
    void givenPerfectScore_thenReturn300() {
        Bowling bowling = new Bowling();
        for (int i = 0; i < 12; i++) {
            bowling.setFrames(10);
        }
        assertEquals(300, bowling.getTotalScore());
    }

    @Test
    void givenStrikeOnLastRoll_thenAllowThreeRolls(){
        Bowling bowling = new Bowling();
        for (int i = 0; i < 9; i++) {
            bowling.setFrames(0);
            bowling.setFrames(0);
        }
        bowling.setFrames(10);
        bowling.setFrames(10);
        bowling.setFrames(10);

        assertEquals(30, bowling.getTotalScore());
    }

    @Test
    void givenSpareOnLastRoll_thenAllowThreeRolls(){
        Bowling bowling = new Bowling();
        for (int i = 0; i < 9; i++) {
            bowling.setFrames(0);
            bowling.setFrames(0);
        }
        bowling.setFrames(6);
        bowling.setFrames(4);
        bowling.setFrames(10);

        assertEquals(20, bowling.getTotalScore());
    }

    @Test
    void givenRollAfterTenRolls_thenThrowException(){
        Bowling bowling = new Bowling();
        for (int i = 0; i < 12; i++) {
            bowling.setFrames(10);
        }

        assertThrows(RuntimeException.class, () -> bowling.setFrames(5));
    }
}