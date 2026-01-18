package ee.margus.bowling.repository;

import ee.margus.bowling.model.Game;
import ee.margus.bowling.model.Player;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private final Map<String, Game> games = new LinkedHashMap<>();

    public Game saveGame(Game game) {
        games.put(game.getId(), game);
        return game;
    }

    public Game getGame(String id) {
        return games.get(id);
    }

    public List<Game> getAllGames() {
        return games.values().stream().toList();
    }

    public void delete(boolean confirm) {
        List<Game> unFinishedGames = games.values().stream().filter(game -> !game.isFinished()).toList();
        if(!unFinishedGames.isEmpty() && !confirm) throw new RuntimeException("Some games are still in progress!");
        games.clear();
    }

    public List<String> getGameIds(){
        return games.keySet().stream().toList();
    }
}
