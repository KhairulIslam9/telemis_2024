package be.ki.telemis_excercice.services;

import be.ki.telemis_excercice.models.DisplayGame;
import be.ki.telemis_excercice.models.Game;
import be.ki.telemis_excercice.models.errors.GameException;
import be.ki.telemis_excercice.utils.DisplayUtils;
import be.ki.telemis_excercice.utils.GameUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class DisplayGameService {

    private final GameServiceImpl gameService;
    private final Scanner scanner = new Scanner(System.in);


    public void play() {
        System.out.println("Bienvenue ");
        System.out.println("Veuillez entrer votre nom");

        boolean played = false;
        Game game = new Game();
        game.setPlayer(scanner.nextLine());

        while (!played) {

            System.out.println("Combien de quilles sont tombées ?");
            int lancer = scanner.nextInt();

            try {
                this.gameService.lancer(game, lancer);
                this.gameService.score(game);

                DisplayGame displayGame = DisplayUtils.display(game.getFrames());
                displayFrame(displayGame.getFrames());
                displayDash(displayGame.getFrames());
                displayRound(displayGame.getFramesRoundScore());
                displayDash(displayGame.getFrames());
                displayFrame(displayGame.getFrameScores());
                displayDash(displayGame.getFrames());

                played = game.isFinished();
            } catch (GameException e) {
                System.out.println(e.getMessage());
                System.out.println("Veillez entrez le bon nombre!");
            }
        }

        System.out.println("Merci d'avoir jouer");
    }

    private void displayFrame(Collection<Integer> toDisplay) {
        List displayList = new ArrayList<>(toDisplay);
        for (int i = 0; i < GameUtil.MAX_FRAMES; i++) {
            Integer value = (Integer) displayList.get(i);
            String display = value != 0 ? value.toString() : " ";
            if (display.length() < 2) {
                display = " " + display + " ";
            } else if (display.length() < 3) {
                display += " ";
            }
            System.out.print("|      " + display + "     ");
        }
        System.out.println("|");
    }

    private void displayRound(Collection<String> toDisplay) {
        toDisplay.forEach(k -> {
            if (k.length() < 2) {
                k += " ";
            }
            System.out.print("| " + k + " ");
        });
        System.out.println("|");
    }

    private void displayDash(Collection<Integer> toDisplay) {
        toDisplay.forEach(d -> {
            System.out.print("---------------");
        });
        System.out.println("-");
    }
}
