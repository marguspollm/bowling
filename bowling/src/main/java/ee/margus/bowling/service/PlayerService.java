package ee.margus.bowling.service;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.model.Bowling;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player addPlayer(CreatePlayerDTO createPlayerDTO) {
        Player player = new Player();
        String id = UUID.randomUUID().toString();
        player.setId(id);
        player.setName(createPlayerDTO.getName());
        player.setBowling(new Bowling());
        return playerRepository.savePlayer(player);
    }

    public Player getPlayer(String id) {
        return playerRepository.getPlayer(id);
    }

    public List<Player> getPlayers() {
        return playerRepository.getAllPlayers();
    }
}
