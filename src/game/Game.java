package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements ActionListener, KeyListener {
    // controls the delay between each tick in ms
    private static final int DELAY = 300;
    private final Header header;
    private final Board board;

    private int stepNumber = 0;
    private boolean isGamePaused = false;

    public Game() {
        setTitle("Life on swing");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        header = new Header();
        add(header, BorderLayout.NORTH);

        board = new Board();
        add(board, BorderLayout.CENTER);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        addKeyListener(this); // Register this frame as the KeyListener

        // this timer will call the actionPerformed() method every DELAY ms
        // keep a reference to the timer object that triggers actionPerformed() in
        // case we need access to it in another method
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGamePaused) {
            return;
        }

        board.doStep();
        board.repaint();
        header.updateStep(++stepNumber);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isGamePaused = !isGamePaused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
