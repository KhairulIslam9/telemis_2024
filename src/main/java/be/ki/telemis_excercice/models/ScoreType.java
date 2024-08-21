package be.ki.telemis_excercice.models;

import be.ki.telemis_excercice.utils.GameUtil;
import lombok.Getter;

@Getter
public enum ScoreType {
    NONE(0, " "),
    STRIKE(GameUtil.STRIKE_LOOP_BONUS, "X"),
    SPARE(GameUtil.SPARE_LOOP_BONUS, "/");

    final int loop;
    final String display;

    ScoreType(int loop, String display) {
        this.loop = loop;
        this.display = display;
    }
}
