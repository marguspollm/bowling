package ee.margus.bowling.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingTest {

    @Test
    void givenNormalRolls_thenScoreIsPinsSum() {
        Game bowling = new Game();
        bowling.setFrame(4);
        bowling.setFrame(1);
        bowling.setFrame(3);
        bowling.setFrame(1);

        assertEquals(9, bowling.getTotalScore());
    }

    @Test
    void givenSpare_thenSpareScoreIsFromNextRoll() {
        Game bowling = new Game();
        bowling.setFrame(4);
        bowling.setFrame(6);
        bowling.setFrame(3);
        bowling.setFrame(1);

        assertEquals(17, bowling.getTotalScore());
    }

    @Test
    void givenStrike_thenStrikeScoreIsFromNextTwoRolls() {
        Game bowling = new Game();
        bowling.setFrame(10);
        bowling.setFrame(6);
        bowling.setFrame(3);

        assertEquals(28, bowling.getTotalScore());
    }

    @Test
    void givenMultipleStrikes_thenStrikeScoreIsFromNextTwoRolls() {
        Game bowling = new Game();
        bowling.setFrame(10);
        bowling.setFrame(10);
        bowling.setFrame(10);

        assertEquals(60, bowling.getTotalScore());
    }

    @Test
    void givenPerfectScore_thenReturn300() {
        Game bowling = new Game();
        for (int i = 0; i < 12; i++) {
            bowling.setFrame(10);
        }
        assertEquals(300, bowling.getTotalScore());
    }

    @Test
    void givenStrikeOnLastRoll_thenAllowThreeRolls(){
        Game bowling = new Game();
        for (int i = 0; i < 9; i++) {
            bowling.setFrame(0);
            bowling.setFrame(0);
        }
        bowling.setFrame(10);
        bowling.setFrame(10);
        bowling.setFrame(10);

        assertEquals(30, bowling.getTotalScore());
    }

    @Test
    void givenSpareOnLastRoll_thenAllowThreeRolls(){
        Game bowling = new Game();
        for (int i = 0; i < 9; i++) {
            bowling.setFrame(0);
            bowling.setFrame(0);
        }
        bowling.setFrame(6);
        bowling.setFrame(4);
        bowling.setFrame(10);

        assertEquals(20, bowling.getTotalScore());
    }

    @Test
    void givenRollAfterTenRolls_thenThrowException(){
        Game bowling = new Game();
        for (int i = 0; i < 12; i++) {
            bowling.setFrame(10);
        }

        assertThrows(RuntimeException.class, () -> bowling.setFrame(5));
    }
}