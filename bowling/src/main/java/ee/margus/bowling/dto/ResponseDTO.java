package ee.margus.bowling.dto;

import ee.margus.bowling.model.Bowling;
import lombok.Data;

@Data
public class ResponseDTO {
    private String id;
    private String name;
    private Bowling bowling;
}
