package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGame);
    }

    private static void createAndShowGame() {
        JFrame frame = new GameWindow(Constant.GAME_TITLE, Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        frame.setVisible(true);
    }
}