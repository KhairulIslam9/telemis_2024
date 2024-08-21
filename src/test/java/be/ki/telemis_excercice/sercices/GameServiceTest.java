package be.ki.telemis_excercice.sercices;

import be.ki.telemis_excercice.models.Frame;
import be.ki.telemis_excercice.models.Game;
import be.ki.telemis_excercice.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
public class GameServiceTest {
    private Game game;
    @Autowired
    private GameService gameService;


    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testLancerSpare() {
        gameService.lancer(game, 7);
        gameService.lancer(game, 3);
        gameService.lancer(game, 5);
        gameService.lancer(game, 10);

        assertEquals(2, game.getCurrentFrame());
        assertEquals(1, game.getCurrentRound());

        Frame frame = game.getFrames().get(1);
        assertNotNull(frame);
        assertEquals(15, frame.getFrameScore());
        assertTrue(frame.isSpare());
    }

    @Test
    public void testLancerRegular() {
        gameService.lancer(game, 5);
        gameService.lancer(game, 3);

        assertEquals(1, game.getCurrentFrame());
        assertEquals(2, game.getCurrentRound());

        Frame frame = game.getFrames().get(1);
        assertNotNull(frame);
        assertEquals(8, frame.getFrameScore());
        assertFalse(frame.isStrike());
        assertFalse(frame.isSpare());
    }

    @Test
    public void testLancerFullStrike() {
        // Frame 1: Strike
        gameService.lancer(game, 15);
        // Frame 2: Strike
        gameService.lancer(game, 15);
        // Frame 3: Strike
        gameService.lancer(game, 15);
        // Frame 4: Strike
        gameService.lancer(game, 15);
        // Frame 5: Strike
        gameService.lancer(game, 15);
        // Bonus 1: Strike
        gameService.lancer(game, 15);
        // Bonus 1: Strike
        gameService.lancer(game, 15);
        // Bonus 1: Strike
        gameService.lancer(game, 15);

        gameService.score(game);

        assertEquals(60, game.getFrames().get(1).getScoreToDisplay());
        assertEquals(120, game.getFrames().get(2).getScoreToDisplay());
        assertEquals(180, game.getFrames().get(3).getScoreToDisplay());
        assertEquals(240, game.getFrames().get(4).getScoreToDisplay());
        assertEquals(300, game.getFrames().get(5).getScoreToDisplay());
    }

    @Test
    public void testExample1() {
        // Frame 1:
        gameService.lancer(game, 8);
        gameService.lancer(game, 1);
        gameService.lancer(game, 1);
        // Frame 2: Spare
        gameService.lancer(game, 8);
        gameService.lancer(game, 7);
        // Frame 3:
        gameService.lancer(game, 1);
        gameService.lancer(game, 2);
        gameService.lancer(game, 1);
        // Frame 4: Strike
        gameService.lancer(game, 15);
        // Frame 5:
        gameService.lancer(game, 1);
        gameService.lancer(game, 2);
        gameService.lancer(game, 1);

        gameService.score(game);

        assertEquals(10, game.getFrames().get(1).getScoreToDisplay());
        assertEquals(28, game.getFrames().get(2).getScoreToDisplay());
        assertEquals(32, game.getFrames().get(3).getScoreToDisplay());
        assertEquals(51, game.getFrames().get(4).getScoreToDisplay());
        assertEquals(55, game.getFrames().get(5).getScoreToDisplay());
    }

    @Test
    public void testExample2() {
        // Frame 1: Strike
        gameService.lancer(game, 15);
        // Frame 2:
        gameService.lancer(game, 8);
        gameService.lancer(game, 1);
        gameService.lancer(game, 2);
        // Frame 3: Spare
        gameService.lancer(game, 1);
        gameService.lancer(game, 2);
        gameService.lancer(game, 12);
        // Frame 4:
        gameService.lancer(game, 6);
        gameService.lancer(game, 4);
        gameService.lancer(game, 1);
        // Frame 5: Strike
        gameService.lancer(game, 15);
        // Bonus 1:
        gameService.lancer(game, 8);
        // Bonus 2:
        gameService.lancer(game, 2);
        // Bonus 3:
        gameService.lancer(game, 3);

        gameService.score(game);

        assertEquals(26, game.getFrames().get(1).getScoreToDisplay());
        assertEquals(37, game.getFrames().get(2).getScoreToDisplay());
        assertEquals(62, game.getFrames().get(3).getScoreToDisplay());
        assertEquals(73, game.getFrames().get(4).getScoreToDisplay());
        assertEquals(101, game.getFrames().get(5).getScoreToDisplay());
    }
}
