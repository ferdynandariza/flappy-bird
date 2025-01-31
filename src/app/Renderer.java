package app;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Renderer {

    private final Image BACKGROUND_IMAGE = new ImageIcon(getClass().getResource("../resources/background.png")).getImage();

    private final Bird bird;
    private final ArrayList<Pipe> pipes;
    private GameState state;

    public Renderer(
            Bird bird,
            ArrayList<Pipe> pipes,
            GameState state
    ) {
        this.bird = bird;
        this.pipes = pipes;
        this.state = state;
    }

    public void draw(Graphics g) {
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
        String scoreString = String.valueOf(state.getScore().intValue());
        if (state.getGameOver()) {
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
            return Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/font/retro-gaming.ttf"))
                    .deriveFont(size);
        } catch (IOException | FontFormatException e) {
            return new Font("SansSerif", Font.BOLD, (int) size);
        }
    }

    public void updateState(GameState state) {
        this.state = state;
    }
}
