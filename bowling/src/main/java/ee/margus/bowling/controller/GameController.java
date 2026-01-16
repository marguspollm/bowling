package ee.margus.bowling.controller;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.dto.RollDTO;
import ee.margus.bowling.model.Game;
import ee.margus.bowling.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/roll")
    public List<Game> addRoll(@RequestBody RollDTO rollDTO) {
        return gameService.addRoll(rollDTO.getPins(), rollDTO.getGameId());
    }

    @PostMapping("/create")
    public Game addPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) {
        return gameService.addGame(createPlayerDTO);
    }

    @GetMapping("/game/{id}")
    public Game getPlayer(@PathVariable String id) {
        return gameService.getGame(id);
    }

    @GetMapping("/all-games")
    public List<Game> getPlayers() {
        return gameService.getAllGames();
    }

    @DeleteMapping("/del")
    public void deleteAllGames(@RequestParam(defaultValue = "false") boolean confirm){
        gameService.delete(confirm);
    }
}
