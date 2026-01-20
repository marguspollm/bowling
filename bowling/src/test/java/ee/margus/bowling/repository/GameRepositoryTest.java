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
    void whenSaveGame_thenReturn() {
        Player player = new Player();
        player.setId("test-id");
        player.setName("test");
        Game game = new Game();
        game.setId("test");
        game.setPlayer(player);
        Game saved = repository.save(game);

        assertEquals(game, saved);
        assertEquals(1, repository.getAll().size());
    }

    @Test
    void get_thenReturnException() {
        assertThrows(RuntimeException.class, () -> repository.get("bad-id"));
    }

    @Test
    void whenGetGame_thenReturn() {
        Game game = new Game();
        game.setId("test");

        repository.save(game);
        assertEquals(game, repository.get("test"));
    }

    @Test
    void getAll() {
        for (int i = 0; i < 3; i++) {
            Game game = new Game();
            game.setId("test" + i);
            repository.save(game);
        }

        assertEquals(3, repository.getAll().size());
    }

    @Test
    void givenConfirmIsTrueAndUnfinishedGames_deleteAllALlGames() {
        Game game = new Game();
        game.setId("test");

        repository.save(game);
        repository.deleteAll(true);

        assertEquals(0, repository.getAll().size());
    }

    @Test
    void givenConfirmIsTrueAndNoUnfinishedGames_thenThrowException() {
        Game game = new Game();
        game.setId("test");
        game.getFrames().getLast().setRolls(List.of(1, 1));

        repository.save(game);
        repository.deleteAll(true);

        assertEquals(0, repository.getAll().size());
    }

    @Test
    void givenConfirmIsFalseAndNoUnfinishedGames_thenDeleteAllGames() {
        Game game = new Game();
        game.setId("test");
        game.getFrames().getLast().setRolls(List.of(1, 1));

        repository.save(game);
        repository.deleteAll(false);

        assertEquals(0, repository.getAll().size());
    }

    @Test
    void givenConfirmIsFalseAndUnfinishedGames_thenThrowException() {
        Game game = new Game();
        game.setId("test");
        game.getFrames().getLast().setRolls(List.of(1));

        repository.save(game);

        assertThrows(RuntimeException.class, () -> repository.deleteAll(false));
    }
}