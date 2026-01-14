package ee.margus.bowling.service;

import ee.margus.bowling.model.Player;
import ee.margus.bowling.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BowlingService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player addRoll(int pins, String playerId) {
        Player player = playerRepository.getPlayer(playerId);
        player.getBowling().setFrames(pins);
        return player;
    }
}
