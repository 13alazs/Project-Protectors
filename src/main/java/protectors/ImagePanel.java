package protectors;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private final Image image;

    public ImagePanel(Image image) {
        super();

        this.image = image;
        this.setLayout(null);
    }

    public ImagePanel(int x, int y, int width, int height, Image image) {
        super();

        this.setBounds(x, y, width, height);
        this.image = image;
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
