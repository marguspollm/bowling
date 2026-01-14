package ee.margus.bowling.service;

import ee.margus.bowling.model.Bowling;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BowlingServiceTest {
    @Mock
    private PlayerService playerService;
    @InjectMocks
    private BowlingService bowlingService;

    @Test
    void whenAddRoll_thenUpdateFrameAndReturnPlayer() {
        int pins = 5;
        String playerId = "test-id";
        Player player = new Player();
        player.setId("test-id");
        player.setName("Test");
        player.setBowling(new Bowling());

        when(playerService.getPlayer(any(String.class))).thenReturn(player);

        Player result = bowlingService.addRoll(pins, playerId);

        assertEquals(result, player);
        assertEquals(5, result.getBowling().getTotalScore());
    }
}