package be.ki.telemis_excercice.models;

import be.ki.telemis_excercice.utils.GameUtil;
import lombok.Data;

import java.util.Map;


@Data
public class Game {
    private int currentFrame;
    private int currentRound;
    private boolean finished;
    private Map<Integer, Frame> frames;
    private String player;

    public Game() {
        currentFrame = 1;
        currentRound = 0;
        finished = false;
        frames = GameUtil.initGame();
        player = "";
    }
}
