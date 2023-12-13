package game;

import javax.swing.*;

import java.awt.*;

import static game.Board.COLUMNS;
import static game.Board.TILE_SIZE;

public class Header extends JPanel {
    private int step = 0;

    public Header() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * 2));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var stepLable = new JLabel("Step: " + step);
        Font newFont = new Font(stepLable.getFont().getName(), Font.PLAIN, 20);
        stepLable.setFont(newFont);
        stepLable.setOpaque(true);
        add(stepLable);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    protected void updateStep(int stepNumber) {
        step = stepNumber;
        repaint();
    }
}
