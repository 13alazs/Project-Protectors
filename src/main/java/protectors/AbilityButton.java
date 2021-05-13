package protectors;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AbilityButton extends JButton {

    private AbilityButton btn = this;

    ImageIcon icon;

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
            public void mouseEntered(MouseEvent evt) {
                btn.setBorderPainted(true);
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                btn.setBorderPainted(false);
                btn.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2));
            }
        });
    }

    public void setImage(Image image, int width, int height) {
        Image newimg = image.getScaledInstance(width + 15, height + 15, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        btn.setIcon(icon);
    }
}
