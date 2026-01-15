package ee.margus.bowling.controller;

import ee.margus.bowling.dto.ResponseDTO;
import ee.margus.bowling.dto.RollDTO;
import ee.margus.bowling.service.BowlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class BowlingController {
    @Autowired
    private BowlingService bowlingService;

    @PostMapping("/roll")
    public ResponseDTO addRoll(@RequestBody RollDTO rollDTO) {
        return bowlingService.addRoll(rollDTO.getPins(), rollDTO.getPlayerId());
    }
}
