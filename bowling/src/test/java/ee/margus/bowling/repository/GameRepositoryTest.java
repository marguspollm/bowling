package ee.margus.bowling.repository;

import ee.margus.bowling.model.Game;
import ee.margus.bowling.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {
    private GameRepository repository;

    @BeforeEach
    void setUp() {
        repository = new GameRepository();
    }

    @Test
    void whenSaveGame_thenReturnGame() {
        Player player = new Player();
        player.setId("test-id");
        player.setName("test");
        Game game = new Game();
        game.setId("test");
        game.setPlayer(player);
        Game saved = repository.saveGame(game);

        assertEquals(game, saved);
        assertEquals(1, repository.getAllGames().size());
    }

    @Test
    void getGame_thenReturnException() {
        assertNull(repository.getGame("bad-id"));
    }

    @Test
    void whenGetGame_thenReturnGame() {
        Game game = new Game();
        game.setId("test");

        repository.saveGame(game);
        assertEquals(game, repository.getGame("test"));
    }

    @Test
    void getAllGames() {
        for (int i = 0; i < 3; i++) {
            Game game = new Game();
            game.setId("test" + i);
            repository.saveGame(game);
        }

        assertEquals(3, repository.getAllGames().size());
    }

    @Test
    void givenConfirmIsTrueAndUnfinishedGames_deleteALlGames() {
        Game game = new Game();
        game.setId("test");

        repository.saveGame(game);
        repository.delete(true);

        assertEquals(0, repository.getAllGames().size());
    }

    @Test
    void givenConfirmIsTrueAndNoUnfinishedGames_thenThrowException() {
        Game game = new Game();
        game.setId("test");
        game.getFrames().getLast().setRolls(List.of(1, 1));

        repository.saveGame(game);
        repository.delete(true);

        assertEquals(0, repository.getAllGames().size());
    }

    @Test
    void givenConfirmIsFalseAndNoUnfinishedGames_thenDeleteAllGames() {
        Game game = new Game();
        game.setId("test");
        game.getFrames().getLast().setRolls(List.of(1, 1));

        repository.saveGame(game);
        repository.delete(false);

        assertEquals(0, repository.getAllGames().size());
    }

    @Test
    void givenConfirmIsFalseAndUnfinishedGames_thenThrowException() {
        Game game = new Game();
        game.setId("test");
        game.getFrames().getLast().setRolls(List.of(1));

        repository.saveGame(game);

        assertThrows(RuntimeException.class, () -> repository.delete(false));
    }
}