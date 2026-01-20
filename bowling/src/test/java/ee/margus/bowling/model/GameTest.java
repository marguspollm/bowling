package ee.margus.bowling.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void givenNormalRolls_thenScoreIsPinsSum() {
        Game game = new Game();
        game.addRoll(4);
        game.addRoll(1);
        game.addRoll(3);
        game.addRoll(1);
        game.calculateFrameScores();

        assertEquals(9, game.getTotalScore());
    }

    @Test
    void givenSpare_thenSpareScoreIsFromNextRoll() {
        Game game = new Game();
        game.addRoll(4);
        game.addRoll(6);
        game.addRoll(3);
        game.addRoll(1);
        game.calculateFrameScores();

        assertEquals(17, game.getTotalScore());
        assertFalse(game.isFinished());
    }

    @Test
    void givenStrike_thenStrikeScoreIsFromNextTwoRolls() {
        Game game = new Game();
        game.addRoll(10);
        game.addRoll(6);
        game.addRoll(3);
        game.calculateFrameScores();

        assertEquals(28, game.getTotalScore());
    }

    @Test
    void givenMultipleStrikes_thenStrikeScoreIsFromNextTwoRolls() {
        Game game = new Game();
        game.addRoll(10);
        game.addRoll(10);
        game.addRoll(10);
        game.calculateFrameScores();

        assertEquals(60, game.getTotalScore());
        assertEquals(10, game.getFrames().size());
    }

    @Test
    void givenPerfectScore_thenReturn300() {
        Game game = new Game();
        for (int i = 0; i < 12; i++) {
            game.addRoll(10);
        }
        game.calculateFrameScores();

        assertEquals(300, game.getTotalScore());
        assertTrue(game.isFinished());
    }

    @Test
    void givenStrikeOnLastRoll_thenAllowThreeRolls() {
        Game game = new Game();
        for (int i = 0; i < 9; i++) {
            game.addRoll(0);
            game.addRoll(0);
        }
        game.addRoll(10);
        game.addRoll(10);
        game.addRoll(10);
        game.calculateFrameScores();

        assertEquals(30, game.getTotalScore());
    }

    @Test
    void givenSpareOnLastRoll_thenAllowThreeRolls() {
        Game game = new Game();
        for (int i = 0; i < 9; i++) {
            game.addRoll(0);
            game.addRoll(0);
        }
        game.addRoll(6);
        game.addRoll(4);
        game.addRoll(10);
        game.calculateFrameScores();

        assertEquals(20, game.getTotalScore());
    }

    @Test
    void givenRollAfterTenRolls_thenThrowException() {
        Game game = new Game();
        for (int i = 0; i < 12; i++) {
            game.addRoll(10);
        }

        assertThrows(RuntimeException.class, () -> game.addRoll(5));
    }

    @Test
    void givenTenthFrameIsComplete_whenIsGameFinished_thenReturnTrue() {
        Game game = new Game();
        game.getFrames().getLast().setRolls(List.of(1, 2));

        assertTrue(game.isFinished());
    }
}