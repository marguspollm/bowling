package ee.margus.bowling.controller;

import ee.margus.bowling.dto.CreatePlayerDTO;
import ee.margus.bowling.dto.GameDTO;
import ee.margus.bowling.dto.RollDTO;
import ee.margus.bowling.model.Game;
import ee.margus.bowling.model.Player;
import ee.margus.bowling.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createGame_thenReturnCreatedGame() throws Exception {
        String name = "Test";
        CreatePlayerDTO dto = new CreatePlayerDTO(name);
        GameDTO game = new GameDTO(name, 0, List.of(), new Player());

        when(gameService.createGame(any())).thenReturn(game);

        mockMvc.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("Test"));
    }

    @Test
    void getAllGames_thenReturnAllGamesList() throws Exception {
        Game game1 = new Game();
        game1.setId("1");
        Game game2 = new Game();
        game2.setId("2");

        when(gameService.getAllGames()).thenReturn(List.of(game1, game2));

        mockMvc.perform(get("/all-games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deleteAllGames() throws Exception {
        mockMvc.perform(delete("/del?confirm=true"))
                .andExpect(status().isOk());
    }

    @Test
    void addRoll_thenReturnUpdatedGameList() throws Exception {
        RollDTO dto = new RollDTO(3, "test");

        Game game = new Game();
        game.setId("test");

        when(gameService.addRoll(anyInt(), anyString())).thenReturn(game);

        mockMvc.perform(post("/roll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test"));
    }
}