package game;

import javax.swing.*;

import java.awt.*;

import static game.Board.COLUMNS;
import static game.Board.TILE_SIZE;

public class Header extends JPanel {
    public static final String STEP_LABEL_MESSAGE = "Step: ";
    private int step = 0;
    private final JLabel stepLabel;

    public Header() {
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * 2));

        stepLabel = new JLabel(STEP_LABEL_MESSAGE + step);
        Font newFont = new Font(stepLabel.getFont().getName(), Font.PLAIN, 20);
        stepLabel.setFont(newFont);
        stepLabel.setOpaque(true);
        add(stepLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        stepLabel.setText(STEP_LABEL_MESSAGE + step);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    protected void updateStep(int stepNumber) {
        step = stepNumber;
        repaint();
    }
}
