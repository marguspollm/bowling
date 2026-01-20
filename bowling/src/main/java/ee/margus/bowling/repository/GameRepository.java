package ee.margus.bowling.repository;

import ee.margus.bowling.model.Game;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private final Map<String, Game> games = new ConcurrentHashMap<>();

    public Game save(Game game) {
        games.put(game.getId(), game);
        return game;
    }

    public Game get(String id) {
        return games.get(id);
    }

    public List<Game> getAll() {
        return games.values().stream().toList();
    }

    public void deleteAll(boolean confirm) {
        boolean unFinishedGames = games.values()
                .stream()
                .anyMatch(
                        game -> !game.isFinished()
                );
        if (unFinishedGames && !confirm) throw new RuntimeException("Some games are still in progress!");
        games.clear();
    }

    public boolean exists(String name) {
        var i = games.values()
                .stream()
                .anyMatch(game -> name.equals(game.getId())
                );
        return i;
    }

    public void update(Game game) {
        if(!exists(game.getId())) throw new RuntimeException("Game doesn't exist!");
        games.put(game.getId(), game);
    }
}
