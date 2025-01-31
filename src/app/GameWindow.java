package app;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird = new FlappyBird();
        add(flappyBird);
        pack();
        flappyBird.requestFocus();
    }
}
