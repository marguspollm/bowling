package ee.margus.bowling.repository;

import ee.margus.bowling.model.Player;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {
    private final Map<String, Player> players = new ConcurrentHashMap<>();

    public Player savePlayer(Player player){
        players.put(player.getId(), player);
        return player;
    }

    public Player getPlayer(String id){
        return players.get(id);
    }
}
