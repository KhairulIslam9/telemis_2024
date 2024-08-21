package be.ki.telemis_excercice.utils;

import be.ki.telemis_excercice.models.DisplayGame;
import be.ki.telemis_excercice.models.Frame;
import be.ki.telemis_excercice.models.ScoreType;

import java.util.*;

public class DisplayUtils {

    public static DisplayGame display(Map<Integer, Frame> frames) {
        DisplayGame display = new DisplayGame();
        display.setFrames(frames.keySet());
        Collection<Frame> frameCollection =  frames.values();

        List<String> displayRoundScore = new ArrayList<>();
        List<Integer> frameScore = new ArrayList<>();

        frameCollection.forEach(frame -> {
            List<Integer> rounds = frame.getRounds();

            if (!frame.isBonus()) {
                for (int i = 0; i <= GameUtil.MAX_THROWS; i++) {
                    displayRoundScore.add(isStrikeOrSpare(frame, i));
                }
            } else if (!rounds.isEmpty()) {
                displayRoundScore.add(isStrikeOrSpare(frame, 0));
            }

            frameScore.add(frame.getScoreToDisplay());
        });

        display.setFramesRoundScore(displayRoundScore);
        display.setFrameScores(frameScore);

        return display;
    }

    private static String isStrikeOrSpare(Frame frame, int index) {
        if (frame.isStrike() && index == 0) {
            return ScoreType.STRIKE.getDisplay();
        }

        List<Integer> rounds = frame.getRounds();
        int size = rounds.size();

        if (frame.isSpare() && index == size - 1) {
            return ScoreType.SPARE.getDisplay();
        }

        if (index < size) {
            return rounds.get(index).toString();
        }

        return ScoreType.NONE.getDisplay();
    }

}
