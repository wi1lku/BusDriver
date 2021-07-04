package window;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private String title;
    private JLabel labelChances;

    public StatsPanel(String title) {

        JLabel labelTitle = new JLabel(title);
        labelTitle.setFont(labelTitle.getFont().deriveFont(18.0f));
        labelChances = new JLabel();
        labelChances.setFont(labelChances.getFont().deriveFont(18.0f));

        labelTitle.setForeground(new Color(120,120,150));
        labelChances.setForeground(new Color(120,120,150));

        labelTitle.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelTitle.setAlignmentX(CENTER_ALIGNMENT);
        labelChances.setAlignmentX(CENTER_ALIGNMENT);

        add(labelTitle);
        add(labelChances);

        setBackground(new Color(69,69,69));

    }

    public void setChances(String chances) {
        labelChances.setText(chances);
    }
}
