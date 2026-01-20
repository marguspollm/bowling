package ee.margus.bowling.service;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.dto.GameDTO;
import ee.margus.bowling.model.Game;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public GameDTO createGame(CreatePlayerDTO dto) {
        if (gameRepository.exists(dto.name())) throw new RuntimeException("Cant use same name");

        Player player = createPlayer(dto);
        Game game = new Game();
        game.setId(player.getName());
        game.setPlayer(player);
        gameRepository.save(game);
        return new GameDTO(game.getId(), game.getTotalScore(), game.getFrames(), player);
    }

    private Player createPlayer(CreatePlayerDTO createPlayerDTO) {
        String id = UUID.randomUUID().toString();
        Player player = new Player();
        player.setId(id);
        player.setName(createPlayerDTO.name());
        return player;
    }

    public Game addRoll(int pins, String gameId) {
        Game game = getGame(gameId);
        game.addRoll(pins);
        game.calculateFrameScores();
        gameRepository.save(game);

        return game;
    }

    public Game getGame(String id) {
        return gameRepository.get(id);
    }

    public List<Game> getAllGames() {
        return gameRepository.getAll();
    }

    public void delete(boolean confirm) {
        gameRepository.deleteAll(confirm);
    }

    public GameDTO updateGame(Game game) {
        gameRepository.update(game);
        return new GameDTO(game.getId(), game.getTotalScore(), game.getFrames(), game.getPlayer());
    }
}