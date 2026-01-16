package ee.margus.bowling.service;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.model.Frame;
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

    public Game addGame(CreatePlayerDTO createPlayerDTO) {
        if(gameRepository.getGame(createPlayerDTO.getName()) != null) throw new RuntimeException("Cant use same name");
        Player player = createPlayer(createPlayerDTO);
        Game game = new Game();
        game.setId(player.getName());
        game.setPlayer(player);
        return gameRepository.saveGame(game);
    }

    private Player createPlayer(CreatePlayerDTO createPlayerDTO){
        String id = UUID.randomUUID().toString();
        Player player = new Player();
        player.setId(id);
        player.setName(createPlayerDTO.getName());
        return player;
    }

    public Game getGame(String id) {
        return gameRepository.getGame(id);
    }

    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }

    public List<Game> addRoll(int pins, String gameId) {
        Game game = getGame(gameId);
        game.setFrame(pins);
        game.calculateFrameScores();
        game.getFrames().forEach(Frame::updateFields);
        game.isFinished();

        return getAllGames();
    }

    public void delete(boolean confirm) {
        gameRepository.delete(confirm);
    }
}
