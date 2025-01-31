package io.github.ferdynandariza.flappybird.runner;

import io.github.ferdynandariza.flappybird.constant.Constant;
import io.github.ferdynandariza.flappybird.model.Bird;
import io.github.ferdynandariza.flappybird.model.GameState;
import io.github.ferdynandariza.flappybird.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FlappyBird extends JPanel implements KeyListener {

    private final Bird bird;
    private final GameLoop gameLoop;
    private final PipeManager pipeManager;
    private final CollisionDetector collisionDetector;
    private final GameRenderer renderer;

    private Boolean gameOver = false;
    private Double score = 0.0;

    public FlappyBird() {
        setPreferredSize(new Dimension(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird();
        pipeManager = new PipeManager();
        gameLoop = new GameLoop(this);
        collisionDetector = new CollisionDetector();
        renderer = new GameRenderer(bird, pipeManager.getPipes(), new GameState(score, gameOver));
    }

    public void updateFrame() {
        bird.move();
        pipeManager.movePipes();
        pipeManager.calculateScore(bird, this);
        if (collisionDetector.checkCollision(bird, pipeManager.getPipes()) || collisionDetector.checkBirdFalling(bird)) {
            gameOver = true;
            gameLoop.stop();
            pipeManager.stop();
        }
        renderer.updateState(new GameState(score, gameOver));
    }

    public void increaseScore() {
        score += 0.5;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
            if (gameOver) restart();
        }
    }

    private void restart() {
        bird.reset();
        pipeManager.restart();
        gameLoop.start();
        gameOver = false;
        score = 0.0;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
