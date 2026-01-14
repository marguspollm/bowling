package ee.margus.bowling.controller;

import ee.margus.bowling.dto.RollDTO;
import ee.margus.bowling.model.Bowling;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.service.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BowlingController {
    @Autowired
    private BowlingService bowlingService;

    @PostMapping("/roll")
    public Player addRoll(@RequestBody RollDTO rollDTO){
        return bowlingService.addRoll(rollDTO.getPins(), rollDTO.getPlayerId());
    }
}
