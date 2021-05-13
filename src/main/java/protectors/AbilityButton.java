package protectors;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import javax.swing.BorderFactory;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AbilityButton extends JButton {
    private AbilityButton btn = this;
    private ImageIcon icon;

    private boolean current = false;

    public AbilityButton(String path, int x, int y, int width, int height) {
        super();

        btn.setBounds(x, y, width, height);
        icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(width + 15, height + 15, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btn.setIcon(icon);

        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setBorder(null);

        btn.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                /*
                 * if(!current){ setBordered(true); current = true; }else{ setBordered(false); current = false; }
                 */
            }

            /*
             * @Override public void mouseExited(MouseEvent evt) { btn.setBorderPainted(false);
             * btn.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3)); }
             */
        });
    }

    public void setBordered(boolean border, int currResource, int cost) {
        if (border && currResource >= cost) {
            btn.setBorderPainted(true);
            btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        } else if (!border || currResource < cost) {
            btn.setBorderPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 3));
        }
    }

    public void setImage(Image image, int width, int height, int currResource, int cost) {
        if (currResource >= cost) {
            Image newimg = image.getScaledInstance(width + 15, height + 15, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            btn.setIcon(icon);
        } else {
            ImageFilter filter = new GrayFilter(true, 25);
            ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
            Image img = Toolkit.getDefaultToolkit().createImage(producer);
            icon = new ImageIcon(img);
            btn.setIcon(icon);
        }
    }
}
