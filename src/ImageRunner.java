import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageRunner extends JFrame {
    public ImageRunner(String filename){
        ImageIcon icon = new ImageIcon(filename);

        JLabel label = new JLabel(icon);

        createLayout(label);

    }

    private void createLayout(JComponent label){
        Container pane = getContentPane();
        GroupLayout layout = new GroupLayout(pane);
        pane.setLayout(layout);

        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(label));

        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(label));

        pack();
    }
}
