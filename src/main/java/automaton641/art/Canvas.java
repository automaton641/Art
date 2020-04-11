package automaton641.art;

import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Canvas extends JComponent {
    public BufferedImage bufferedImage;
    public Canvas() {
        bufferedImage = new BufferedImage(App.width*App.cellSize, App.height*App.cellSize, BufferedImage.TYPE_INT_ARGB);
        for (int row = 0; row < bufferedImage.getHeight(); row++) {
            for (int column = 0; column < bufferedImage.getWidth(); column++) {
                bufferedImage.setRGB(column, row, new Color(0.5f, 0.5f, 0.5f).getRGB());
            }
        }
        setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this);
    }
}
