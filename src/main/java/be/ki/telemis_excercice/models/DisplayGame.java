package be.ki.telemis_excercice.models;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DisplayGame {
    private Set<Integer> frames;
    private List<String> framesRoundScore;
    private List<Integer> frameScores;

}
