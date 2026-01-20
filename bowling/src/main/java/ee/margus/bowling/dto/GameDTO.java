package ee.margus.bowling.dto;

import ee.margus.bowling.model.Frame;
import ee.margus.bowling.model.Player;

import java.util.List;

public record GameDTO(String id,
                      int totalScore,
                      List<Frame> frames,
                      Player player) {
}
