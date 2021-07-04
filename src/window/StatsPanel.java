package window;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    String title;
    String chances = "";

    public StatsPanel(String title) {

        JLabel labelTitle = new JLabel(title);
        labelTitle.setFont(labelTitle.getFont().deriveFont(18.0f));
        JLabel labelChances = new JLabel(chances);
        labelChances.setFont(labelChances.getFont().deriveFont(15.0f));

        labelTitle.setForeground(new Color(120,120,150));
        labelChances.setForeground(new Color(120,120,150));

        labelTitle.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelTitle.setAlignmentX(CENTER_ALIGNMENT);

        add(labelTitle);
        add(labelChances);

        setBackground(new Color(69,69,69));

    }

}
