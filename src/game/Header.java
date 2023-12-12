package game;

import javax.swing.*;

import java.awt.*;

import static game.Board.COLUMNS;
import static game.Board.TILE_SIZE;

public class Header extends JPanel {
    public Header() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * 2));
        setBackground((new Color(232, 232, 0)));

    }
}
