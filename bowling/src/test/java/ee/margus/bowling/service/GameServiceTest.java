package ee.margus.bowling.service;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.model.Game;
import ee.margus.bowling.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void givenNotUsedPlayerName_whenAddGame_then() {
        CreatePlayerDTO dto = new CreatePlayerDTO();
        dto.setName("Test");
        Game savedGame = new Game();
        savedGame.setId("Test");

        when(gameRepository.getGame("Test")).thenReturn(null);
        when(gameRepository.saveGame(any(Game.class))).thenReturn(savedGame);

        Game result = gameService.addGame(dto);
        assertNotNull(result);
        assertEquals("Test", result.getId());
    }

    @Test
    void givenNameAlreadyExists_whenAddGame_shouldThrowException_() {
        CreatePlayerDTO dto = new CreatePlayerDTO();
        dto.setName("Test");
        Game existing = new Game();
        existing.setId("Test");

        when(gameRepository.getGame("Test")).thenReturn(existing);

        assertThrows(RuntimeException.class, () -> gameService.addGame(dto));
    }

    @Test
    void givenExistingGameId_whenGetGame_thenReturnGame() {
        Game game = new Game();
        game.setId("test");

        when(gameRepository.getGame("test")).thenReturn(game);

        Game result = gameService.getGame("test");
        assertNotNull(result);
        assertEquals("test", result.getId());
    }

    @Test
    void whenGetAllGames_thenReturnList() {
        List<Game> games = List.of(new Game(), new Game());

        when(gameRepository.getAllGames()).thenReturn(games);

        List<Game> result = gameService.getAllGames();
        assertEquals(2, result.size());
    }

    @Test
    void givenPins_whenAddRoll_thenUpdateGameAndReturnAllGames() {
        Game game = new Game();
        game.setId("test");

        when(gameRepository.getGame("test")).thenReturn(game);

        Game result = gameService.addRoll(7, "test");
        assertEquals(7, result.getFrames().getFirst().getRolls().getFirst());
    }

    @Test
    void whenDeleteAllGames_thenDeleteGames() {
        gameService.delete(true);
        verify(gameRepository).delete(true);
    }
}