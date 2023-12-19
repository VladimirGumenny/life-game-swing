package game;

import javax.swing.*;
import java.awt.*;

import static game.Header.HEADER_HEIGH_IN_TILES;

public class Board extends JPanel {
    public static final int TILE_SIZE = 30;

    private final boolean[][] cells;
    private final int rowsNum;
    private int columnsNum;

    public Board() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        int panelHeight = height;

        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            GraphicsConfiguration defaultConfiguration = GraphicsEnvironment
                    .getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

            Insets screenInsets = Toolkit.getDefaultToolkit()
                    .getScreenInsets(defaultConfiguration);
            int dockHeight = screenInsets.bottom;
            int titleHeight = screenInsets.top;

            columnsNum = width / TILE_SIZE;
            panelHeight -= dockHeight + titleHeight;
        }
        rowsNum = panelHeight / TILE_SIZE - HEADER_HEIGH_IN_TILES;

        setPreferredSize(new Dimension(TILE_SIZE * columnsNum, TILE_SIZE * rowsNum));
        setBackground((new Color(232, 232, 232)));

        cells = new boolean[columnsNum][rowsNum];
        populateCells();
    }

    private void populateCells() {
        // blinker();
        glider();
    }

    private void glider() {
        cells[3][1] = true;
        cells[4][2] = true;
        cells[4][3] = true;
        cells[3][3] = true;
        cells[2][3] = true;
    }

    private void blinker() {
        cells[2][2] = true;
        cells[2][3] = true;
        cells[2][4] = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);

        drawCells(g);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < rowsNum; row++) {
            for (int col = 0; col < columnsNum; col++) {
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
        for (int row = 0; row < rowsNum; row++) {
            for (int col = 0; col < columnsNum; col++) {
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

    protected void doStep() {
        boolean[][] newCells = new boolean[columnsNum][rowsNum];

        for (int row = 0; row < rowsNum; row++) {
            for (int col = 0; col < columnsNum; col++) {
                int neighbours = countNeighbours(row, col);
                if (cells[col][row]) {
                    newCells[col][row] = neighbours == 2
                            || neighbours == 3;
                } else {
                    newCells[col][row] = neighbours == 3;
                }
            }
        }

        for (int row = 0; row < rowsNum; row++)
            for (int col = 0; col < columnsNum; col++)
                cells[col][row] = newCells[col][row];

    }

    private int countNeighbours(int row, int col) {
        int num = 0;
        int startRow = (row == 0) ? row : row - 1;
        int startCol = (col == 0) ? col : col - 1;
        int endRow = (row == rowsNum - 1) ? row : row + 1;
        int endCol = (col == columnsNum - 1) ? col : col + 1;

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

}
