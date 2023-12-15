package game;

import javax.swing.*;
import java.awt.*;

import static game.Board.TILE_SIZE;

public class Header extends JPanel {
    public static final String STEP_LABEL_MESSAGE = "Step: ";
    public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static final int HEADER_HEIGH_IN_TILES = 2;
    private int step = 0;
    private final JLabel stepLabel;

    public Header() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setPreferredSize(new Dimension(screenSize.width, TILE_SIZE * HEADER_HEIGH_IN_TILES));
        setBackground(BACKGROUND_COLOR);

        stepLabel = createLabel();
        add(stepLabel);
    }

    private JLabel createLabel() {
        final JLabel stepLabel;
        stepLabel = new JLabel(STEP_LABEL_MESSAGE + step);
        stepLabel.setForeground(Color.BLUE);
        stepLabel.setBackground(BACKGROUND_COLOR);
        Font font = new Font(stepLabel.getFont().getName(), Font.BOLD, 20);
        stepLabel.setFont(font);
        stepLabel.setOpaque(true);
        return stepLabel;
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
