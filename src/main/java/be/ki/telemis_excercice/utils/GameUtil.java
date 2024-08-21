package be.ki.telemis_excercice.utils;

import be.ki.telemis_excercice.models.Frame;

import java.util.HashMap;
import java.util.Map;

public class GameUtil {
    public static final int MAX_FRAMES = 5;
    public static final int MAX_THROWS = 2;
    public static final int MAX_FALL_PINS = 15;
    public static final int STRIKE_LOOP_BONUS = 3;
    public static final int SPARE_LOOP_BONUS = 2;


    public static Map<Integer, Frame> initGame() {
        Map<Integer, Frame> frames = new HashMap<>();
        for (int i = 1; i <= MAX_FRAMES; i++) {
            frames.put(i, new Frame());
        };

        return frames;
    }
}
