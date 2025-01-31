package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    private final Image BACKGROUND_IMAGE = new ImageIcon(getClass().getResource("../resources/background.png")).getImage();
    private final Image BIRD_IMAGE = new ImageIcon(getClass().getResource("../resources/flappy-bird.png")).getImage();
    private final Image TOP_PIPE_IMAGE = new ImageIcon(getClass().getResource("../resources/top-pipe.png")).getImage();
    private final Image BOTTOM_PIPE_IMAGE = new ImageIcon(getClass().getResource("../resources/bottom-pipe.png")).getImage();

    private int velocityY = -9;
    private int velocityX = -4;
    private final Bird bird;

    private final ArrayList<Pipe> pipes;

    private final Timer GAME_LOOP;
    private final Timer PLACE_PIPES_TIMER;

    private boolean gameOver = false;
    private double score = 0;

    FlappyBird() {
        setPreferredSize(new Dimension(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird(BIRD_IMAGE);
        pipes = new ArrayList<>();
        PLACE_PIPES_TIMER = new Timer(1500, (e) -> placePipes());
        PLACE_PIPES_TIMER.start();

        GAME_LOOP = new Timer(1000 / Constant.FRAME_PER_SECOND, this);
        GAME_LOOP.start();
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

    private void draw(Graphics g) {
        drawBackground(g);
        drawBird(g);
        drawPipes(g);
        drawScore(g);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(BACKGROUND_IMAGE, 0, 0, Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT, null);
    }

    private void drawBird(Graphics g) {
        g.drawImage(bird.getImage(), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);
    }

    private void drawPipes(Graphics g) {
        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(getCustomFont());
        String scoreString = String.valueOf((int) score);
        if (gameOver) {
            g.drawString("Game Over:", 40, Constant.BOARD_HEIGHT / 2 - 50);
            g.drawString(scoreString, Constant.BOARD_WIDTH / 2 - 20, Constant.BOARD_HEIGHT / 2);
            g.setFont(getCustomFont(16));
            g.drawString("Press [SPACE] to restart", 44, Constant.BOARD_HEIGHT - 20);
        } else {
            g.drawString(scoreString, Constant.BOARD_WIDTH / 2 - 20, 80);
        }
    }

    private Font getCustomFont() {
        return getCustomFont(40f);
    }

    private Font getCustomFont(float size) {
        try {
            return  Font.createFont(Font.TRUETYPE_FONT, new File("../resources/font/retro-gaming.ttf"))
                    .deriveFont(size);
        } catch (IOException | FontFormatException e) {
            return new Font("SansSerif", Font.BOLD, (int) size);
        }
    }

    private void move() {
        moveBird();
        movePipes();
        if (isBirdFalling()) gameOver = true;
    }

    private void moveBird() {
        velocityY += Constant.GRAVITY;
        bird.setY(bird.getY() + velocityY);
        bird.setY(Math.max(bird.getY(), 0));
    }

    private void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.setX(pipe.getX() + velocityX);
            calculateScore(bird, pipe);
            if (collide(bird, pipe)) gameOver = true;
        }
    }

    private void calculateScore(Bird bird, Pipe pipe) {
        if (!pipe.isPassed() && isBirdBehindPipe(bird, pipe)) {
            pipe.setPassed(true);
            score += 0.5;
        }
    }

    private boolean isBirdBehindPipe(Bird bird, Pipe pipe) {
        return bird.getX() > pipe.getX() + Constant.PIPE_WIDTH;
    }

    private boolean collide(Bird bird, Pipe pipe) {
        return bird.getX() < pipe.getX() + pipe.getWidth() &&
                bird.getX() + bird.getWidth() > pipe.getX() &&
                bird.getY() < pipe.getY() + pipe.getHeight() &&
                bird.getY() + bird.getHeight() > pipe.getY();
    }

    private boolean isBirdFalling() {
        return bird.getY() > Constant.BOARD_HEIGHT;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            PLACE_PIPES_TIMER.stop();
            GAME_LOOP.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
            if (gameOver) resetState();
        }
    }

    private void resetState() {
        bird.setY(Constant.DEFAULT_BIRD_y);
        velocityY = 0;
        gameOver = false;
        pipes.clear();
        score = 0;
        GAME_LOOP.start();
        PLACE_PIPES_TIMER.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
