package ee.margus.bowling.controller;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @PostMapping("/player")
    public Player addPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) {
        return playerService.addPlayer(createPlayerDTO);
    }

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable String id) {
        return playerService.getPlayer(id);
    }

    @GetMapping("/all-players")
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }
}
