package ee.margus.bowling.service;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.model.Game;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private PlayerService playerService;

    @Test
    void whenAddPlayer_thenReturnSavedPlayer() {
        CreatePlayerDTO dto = new CreatePlayerDTO();
        dto.setName("Test");
        Player player = new Player();
        player.setName("Test");
        player.setBowling(new Game());

        when(gameRepository.savePlayer(any(Player.class))).thenReturn(player);

        assertEquals(player, playerService.addPlayer(dto));
    }

    @Test
    void whenGetPlayer_thenReturnPlayerFromRepository() {
        Player player = new Player();
        player.setId("test-id");
        player.setName("Test");

        when(gameRepository.getPlayer("test-id")).thenReturn(player);

        Player result = playerService.getPlayer("test-id");

        assertEquals(player, result);
        verify(gameRepository).getPlayer("test-id");
    }
}