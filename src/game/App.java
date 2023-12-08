package game;

import javax.swing.*;

public class App {

    private static void initWindow() {
        var window = new JFrame("Life on swing");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        window.add(board);
        window.addKeyListener(board);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initWindow();
            }
        });
    }
}
