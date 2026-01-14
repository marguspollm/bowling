package ee.margus.bowling.repository;

import ee.margus.bowling.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerRepositoryTest {
    private PlayerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PlayerRepository();
    }

    @Test
    void whenSavePlayer_thenReturnPlayer() {
        Player player = new Player();
        player.setId("test-id");
        player.setName("test");
        Player saved = repository.savePlayer(player);

        assertEquals(player, saved);
    }

    @Test
    void getPlayer_thenReturnException() {
        assertNull(repository.getPlayer("bad-id"));
    }

    @Test
    void whenGetPlayer_thenReturnPlayer() {
        Player player = new Player();
        player.setId("test-id");
        player.setName("Test");

        repository.savePlayer(player);
        assertEquals(player, repository.getPlayer("test-id"));
    }
}