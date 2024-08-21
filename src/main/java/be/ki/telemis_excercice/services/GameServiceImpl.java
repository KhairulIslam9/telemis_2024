package be.ki.telemis_excercice.services;

import be.ki.telemis_excercice.models.Frame;
import be.ki.telemis_excercice.models.FrameStatus;
import be.ki.telemis_excercice.models.Game;
import be.ki.telemis_excercice.models.ScoreType;
import be.ki.telemis_excercice.models.errors.GameException;
import be.ki.telemis_excercice.utils.GameUtil;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class GameServiceImpl implements GameService {

    public void lancer(Game game, int pinsFall) {
        int indexRound = game.getCurrentRound();
        int indexFrame = game.getCurrentFrame();

        Map<Integer, Frame> frames = game.getFrames();
        Frame currentFrame = frames.get(indexFrame);

        if (isFrameDone(currentFrame, pinsFall, indexRound)) {
            indexFrame++;
            indexRound = 0;
        } else {
            indexRound++;
        }

        currentFrame.getRounds().add(pinsFall);

        game.setCurrentFrame(indexFrame);
        game.setCurrentRound(indexRound);

        handleFinish(game, currentFrame, indexFrame);

    }

    public void score(Game game) {
        int totalScore = 0;

        for (Map.Entry<Integer, Frame> entry : game.getFrames().entrySet()) {
            Frame frame = entry.getValue();

            if (frame.isStarted()) {
                totalScore += frame.getFrameScore();

                if (frame.isStrike() || frame.isSpare()) {
                    totalScore += addScoreBonus(game, entry.getKey(), frame.getScoreType());
                }

                frame.setScoreToDisplay(totalScore);
            }

        }
    }

    private void handleFinish(Game game, Frame currentFrame, int indexFrame) {
        if (indexFrame > GameUtil.MAX_FRAMES ) {

            if (currentFrame.isBonus()) {
                Frame nextFrame = game.getFrames().getOrDefault(indexFrame, null);

                if (nextFrame == null) {
                    game.setFinished(true);
                }

            } else if (currentFrame.isStrike() || currentFrame.isSpare()) {

                for (int i = 1; i <= currentFrame.getScoreType().getLoop(); i++) {
                    game.getFrames().computeIfAbsent(GameUtil.MAX_FRAMES + i, k -> new Frame(FrameStatus.BONUS));
                }

            } else {
                game.setFinished(true);
            }

        }
    }

    private int addScoreBonus(Game game, int frameIndex, ScoreType scoreType) {
        int strikeScore = 0;
        int loop = scoreType.getLoop();

        while (loop > 0) {
            frameIndex++;
            Frame nextFrame = game.getFrames().getOrDefault(frameIndex, null);

            if (nextFrame != null && !nextFrame.getRounds().isEmpty()) {
                for (Integer round : nextFrame.getRounds()) {
                   if (loop > 0) {
                        strikeScore += round;
                        loop--;
                    }
                }
            } else {
                loop--;
            }
        }

        return strikeScore;
    }


    private boolean isFrameDone(Frame frame, int pinsFall, int indexRound) {
        int totalPinsFall = pinsFall;
        if (!frame.isStarted()) {
            frame.setStatus(FrameStatus.STARTED);
        }

        for (Integer roundFall : frame.getRounds()) {
            totalPinsFall += roundFall;
        }

        if (totalPinsFall > GameUtil.MAX_FALL_PINS) {
            int pinsLeft = GameUtil.MAX_FALL_PINS - totalPinsFall + pinsFall;
            throw new GameException("Il ne reste plus que " +  pinsLeft + " quille(s)");
        }

        frame.setFrameScore(totalPinsFall);


        if (pinsFall == GameUtil.MAX_FALL_PINS && frame.getRounds().isEmpty()) {
            frame.setScoreType(ScoreType.STRIKE);
            return true;
        }

        if (totalPinsFall == GameUtil.MAX_FALL_PINS) {
            frame.setScoreType(ScoreType.SPARE);
            return true;
        }

        if (indexRound >= GameUtil.MAX_THROWS) {
           return true;
        }

        return frame.isBonus();
    }

}
