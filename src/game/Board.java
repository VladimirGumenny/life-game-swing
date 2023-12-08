package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements ActionListener, KeyListener {
    public static final int TILE_SIZE = 30;
    public static final int ROWS = 20;
    public static final int COLUMNS = 20;

    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;
    // controls the delay between each tick in ms
    private static final int DELAY = 1_000;

    private Timer timer;

    private boolean[][] cells = new boolean[COLUMNS][ROWS];

    public Board() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        setBackground((new Color(232, 232, 232)));

        populateCells();

        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void populateCells() {
        cells[2][2] = true;
        cells[2][3] = true;
        cells[2][4] = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);

        drawCells(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                // only color every other tile
                if ((row + col) % 2 == 1) {
                    // draw a square tile at the current row/column position
                    g.drawRect(
                            col * TILE_SIZE,
                            row * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE
                    );
                }
            }
        }
    }

    private void drawCells(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (cells[col][row]) {
                    drawCell(g, col, row);
                }
            }
        }
    }

    public void drawCell(Graphics g, int col, int row) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(
                col * TILE_SIZE,
                row * TILE_SIZE,
                TILE_SIZE,
                TILE_SIZE
        );
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        doStep();
    }

    private void doStep() {
        boolean[][] newCells = new boolean[COLUMNS][ROWS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                int neighbours = countNeighbours(row, col);
                if (cells[col][row]) {
                    newCells[col][row] = neighbours == 2
                            || neighbours == 3;
                } else {
                    newCells[col][row] = neighbours == 3;
                }
            }
        }

        for (int row = 0; row < ROWS; row++)
            for (int col = 0; col < COLUMNS; col++)
                cells[col][row] = newCells[col][row];
    }

    private int countNeighbours(int row, int col) {
        int num = 0;
        int startRow = (row == 0) ? row : row - 1;
        int startCol = (col == 0) ? col : col - 1;
        int endRow = (row == ROWS - 1) ? row : row + 1;
        int endCol = (col == COLUMNS - 1) ? col : col + 1;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (!(r == row && c == col)) {
                    if (cells[c][r])
                        num++;
                }
            }
        }
        return num;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
