package game;

import javax.swing.*;

public class App {

    private static void initWindow() {
        new Game();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::initWindow);
    }
}
