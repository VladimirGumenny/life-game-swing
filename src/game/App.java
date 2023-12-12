package game;

import javax.swing.*;
import java.awt.*;

public class App {

    private static void initWindow() {
        var frame = new JFrame("Life on swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        Header header = new Header();
        frame.add(header, BorderLayout.NORTH);

        Board board = new Board();
        frame.add(board, BorderLayout.CENTER);

        frame.addKeyListener(board);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::initWindow);
    }
}
