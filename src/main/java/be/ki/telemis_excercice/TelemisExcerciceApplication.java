package be.ki.telemis_excercice;

import be.ki.telemis_excercice.services.DisplayGameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelemisExcerciceApplication implements CommandLineRunner {

    private final DisplayGameService displayGameService;

    @Value("${game.autostart:true}")
    private boolean autoStartGame;

    public TelemisExcerciceApplication(DisplayGameService displayGameService) {
        this.displayGameService = displayGameService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TelemisExcerciceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (autoStartGame) {
            displayGameService.play();
        }
    }
}
