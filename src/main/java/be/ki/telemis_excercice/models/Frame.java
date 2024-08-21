package be.ki.telemis_excercice.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Frame {
    private List<Integer> rounds;
    private int frameScore;
    private int scoreToDisplay;
    private FrameStatus status;
    private ScoreType scoreType;

    public Frame() {
        rounds = new ArrayList<>();
        frameScore = 0;
        scoreToDisplay = 0;
        status = FrameStatus.NONE;
        scoreType = ScoreType.NONE;
    }

    public Frame(FrameStatus status) {
        this();
        setStatus(status);
    }

    public boolean isStrike() {
        return scoreType == ScoreType.STRIKE;
    }

    public boolean isSpare() {
        return scoreType == ScoreType.SPARE;
    }

    public boolean isBonus() {
        return status == FrameStatus.BONUS;
    }

    public boolean isStarted() {
        return status != FrameStatus.NONE;
    }
}
