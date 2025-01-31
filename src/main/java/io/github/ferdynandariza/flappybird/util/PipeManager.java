package io.github.ferdynandariza.flappybird.util;

import io.github.ferdynandariza.flappybird.constant.Constant;
import io.github.ferdynandariza.flappybird.constant.ResourcePath;
import io.github.ferdynandariza.flappybird.runner.FlappyBird;
import io.github.ferdynandariza.flappybird.model.Bird;
import io.github.ferdynandariza.flappybird.model.Pipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PipeManager {

    private final Timer pipesTimer;
    private final Image TOP_PIPE_IMAGE = new ImageIcon(getClass().getResource(ResourcePath.TOP_PIPE)).getImage();

    private final Image BOTTOM_PIPE_IMAGE = new ImageIcon(getClass().getResource(ResourcePath.BOTTOM_PIPE)).getImage();

    private final ArrayList<Pipe> pipes = new ArrayList<>();
    private int pipeVelocity = -4;

    public PipeManager() {
        pipesTimer = new Timer(1500, (e) -> placePipes());
        pipesTimer.start();
    }

    public void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.setX(pipe.getX() + pipeVelocity);
        }
    }

    public void calculateScore(Bird bird, FlappyBird game) {
        for (Pipe pipe : pipes) {
            if (!pipe.isPassed() && isBirdBehindPipe(bird, pipe)) {
                pipe.setPassed(true);
                game.increaseScore();
            }
        }
    }

    private boolean isBirdBehindPipe(Bird bird, Pipe pipe) {
        return bird.getX() > pipe.getX() + Constant.PIPE_WIDTH;
    }

    public void restart() {
        pipes.clear();
        pipesTimer.start();
    }

    public void stop() {
        pipesTimer.stop();
    }

    private void placePipes() {
        int openSpace = Constant.BOARD_HEIGHT / 4;
        double randomSubstrate = Math.random() * (Constant.PIPE_HEIGHT / 2.0);

        Pipe topPipe = new Pipe(TOP_PIPE_IMAGE);
        int topPipeY = (int) (Constant.DEFAULT_PIPE_Y - Constant.PIPE_HEIGHT / 4.0 - randomSubstrate);
        placePipe(topPipe, topPipeY);

        Pipe bottomPipe = new Pipe(BOTTOM_PIPE_IMAGE);
        int bottomPipeY = topPipe.getY() + Constant.PIPE_HEIGHT + openSpace;
        placePipe(bottomPipe, bottomPipeY);
    }

    private void placePipe(Pipe pipe, int y) {
        pipe.setY(y);
        pipes.add(pipe);
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }
}
