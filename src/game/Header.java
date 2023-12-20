package game;

import javax.swing.*;
import java.awt.*;

import static game.Board.TILE_SIZE;

public class Header extends JPanel {
    public static final String STEP_LABEL_MESSAGE = "Step: ";
    public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static final int HEADER_HEIGH_IN_TILES = 2;
    public static final Color LABEL_COLOR_PLAY = Color.BLUE;
    public static final Color LABEL_COLOR_PAUSE = Color.GRAY;
    private int step = 0;
    private final JLabel stepLabel;
    private Color currentLabelColor = LABEL_COLOR_PLAY;

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
        stepLabel.setForeground(currentLabelColor);
        stepLabel.setBackground(BACKGROUND_COLOR);
        Font font = new Font(stepLabel.getFont().getName(), Font.BOLD, 20);
        stepLabel.setFont(font);
        stepLabel.setOpaque(true);
        return stepLabel;
    }

    public void togglePause(boolean isPaused) {
        currentLabelColor = isPaused ? LABEL_COLOR_PAUSE : LABEL_COLOR_PLAY;
        stepLabel.setForeground(currentLabelColor);
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
