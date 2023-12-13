package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    // controls the delay between each tick in ms
    private static final int DELAY = 300;
    private final Header header;
    private final Board board;

    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private final Timer timer;
    private int stepNumber = 0;

    public Game() {
        var frame = new JFrame("Life on swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        header = new Header();
        frame.add(header, BorderLayout.NORTH);

        board = new Board();
        frame.add(board, BorderLayout.CENTER);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.doStep();
        board.repaint();
        header.updateStep(++stepNumber);
    }
}
