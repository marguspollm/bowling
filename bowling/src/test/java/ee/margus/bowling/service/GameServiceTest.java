package ee.margus.bowling.service;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.dto.GameDTO;
import ee.margus.bowling.model.Game;
import ee.margus.bowling.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void givenNotUsedPlayerName_whenCreateGame_thenCreateGame() {
        CreatePlayerDTO dto = new CreatePlayerDTO("Test");

        when(gameRepository.exists(anyString())).thenReturn(false);

        GameDTO result = gameService.createGame(dto);
        assertEquals("Test", result.id());
    }

    @Test
    void givenNameAlreadyExists_whenCreateGame_shouldThrowException() {
        CreatePlayerDTO dto = new CreatePlayerDTO("Test");

        when(gameRepository.exists("Test")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> gameService.createGame(dto));
    }

    @Test
    void givenExistingGameId_whenGetGame_thenReturnGame() {
        Game game = new Game();
        game.setId("test");

        when(gameRepository.get("test")).thenReturn(game);

        Game result = gameService.getGame("test");
        assertNotNull(result);
        assertEquals("test", result.getId());
    }

    @Test
    void whenGetAllGames_thenReturnList() {
        List<Game> games = List.of(new Game(), new Game());

        when(gameRepository.getAll()).thenReturn(games);

        List<Game> result = gameService.getAllGames();
        assertEquals(2, result.size());
    }

    @Test
    void givenPins_whenAddRoll_thenUpdateGameAndReturnAllGames() {
        Game game = new Game();
        game.setId("test");

        when(gameRepository.get("test")).thenReturn(game);

        Game result = gameService.addRoll(7, "test");
        assertEquals(7, result.getFrames().getFirst().getRolls().getFirst());
    }

    @Test
    void whenDeleteAllGames_thenDeleteGames() {
        gameService.delete(true);
        verify(gameRepository).deleteAll(true);
    }
}