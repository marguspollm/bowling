package ee.margus.bowling.service;

import ee.margus.bowling.dto.ResponseDTO;
import ee.margus.bowling.model.Frame;
import ee.margus.bowling.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BowlingService {
    @Autowired
    private PlayerService playerService;

    public ResponseDTO addRoll(int pins, String playerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        Player player = playerService.getPlayer(playerId);
        player.getBowling().setFrames(pins);
        player.getBowling().getFrames().forEach(Frame::updateFields);

        responseDTO.setId(player.getId());
        responseDTO.setName(player.getName());
        responseDTO.setBowling(player.getBowling());
        return responseDTO;
    }
}
