package be.ki.telemis_excercice.services;

import be.ki.telemis_excercice.models.Game;

public interface GameService {
    void lancer(Game game, int pinsFall);
    void score(Game game);
}
