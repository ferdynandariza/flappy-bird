package io.github.ferdynandariza.flappybird.util;

import io.github.ferdynandariza.flappybird.constant.Constant;
import io.github.ferdynandariza.flappybird.runner.FlappyBird;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener{

    private final Timer gameLoopTimer;
    private final FlappyBird game;

    public GameLoop(FlappyBird game) {
        this.game = game;
        this.gameLoopTimer = new Timer(1000 / Constant.FRAME_PER_SECOND, this);
        start();
    }

    public void start() {
        gameLoopTimer.start();
    }
    public void stop() {
        gameLoopTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.updateFrame();
        game.repaint();
    }
}
