package ee.margus.bowling.service;

import ee.margus.bowling.model.Game;
import ee.margus.bowling.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @Mock
    private PlayerService playerService;
    @InjectMocks
    private GameService gameService;

    @Test
    void whenAddRoll_thenUpdateFrameAndReturnPlayer() {
        int pins = 5;
        String playerId = "test-id";
        Player player = new Player();
        player.setId("test-id");
        player.setName("Test");
        player.setBowling(new Game());

        when(playerService.getPlayer(any(String.class))).thenReturn(player);

        Player result = gameService.addRoll(pins, playerId);

        assertEquals(result, player);
        assertEquals(5, result.getBowling().getTotalScore());
    }
}