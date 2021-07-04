package window;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JPanel {

    JCheckBox[][] cards = new JCheckBox[4][13];
    JPanel[] panelCardsArrayBig = new JPanel[4];
    JPanel[][] panelCardsArraySmall = new JPanel[4][13];
    String[] colors = { "Spades", "Hearts", "Clubs", "Diamonds" };
    String[] figures = { "2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K", "A"};

    public MainWindow() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Panel -> Player
        JPanel panelPlayer = new JPanel();
        panelPlayer.setLayout(new BoxLayout(panelPlayer, BoxLayout.Y_AXIS));
        panelPlayer.setPreferredSize(new Dimension(400, 500));
        panelPlayer.setBackground(new Color(69,69,69));


        // Panel -> Player -> Statistics
        JPanel panelStatistics = new JPanel();
        panelStatistics.setLayout(new GridLayout(0,2));
        panelStatistics.setPreferredSize(new Dimension(400,400));
        panelStatistics.setBackground(new Color(69,69,69));

        StatsPanel stat1 = new StatsPanel("Black : Red");
        StatsPanel stat2 = new StatsPanel("Higher : Even : Lower");
        StatsPanel stat3 = new StatsPanel("In : Not In");
        StatsPanel stat4 = new StatsPanel("Color : No Color");

        panelStatistics.add(stat1);
        panelStatistics.add(stat2);
        panelStatistics.add(stat3);
        panelStatistics.add(stat4);


        // Panel -> Player -> Hand
        JPanel panelHand = new JPanel();
        panelHand.setLayout(new BorderLayout());
        panelHand.setPreferredSize(new Dimension(400,100));
        panelHand.setBackground(new Color(69,69,69));

        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
        panelLabels.setBackground(new Color(69,69,69));

        JLabel labelYourCards = new JLabel("Your cards");
        labelYourCards.setFont(labelYourCards.getFont().deriveFont(25.0f));
        labelYourCards.setForeground(new Color(120,120,150));
        labelYourCards.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));
        JLabel labelHand = new JLabel("whatever");
        labelHand.setFont(labelHand.getFont().deriveFont(25.0f));
        labelHand.setForeground(new Color(120,120,150));
        labelHand.setBorder(BorderFactory.createEmptyBorder(0,30,0,0));

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1,2,10,10));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JButton buttonSwitch = new JButton("Yours");
        buttonSwitch.setPreferredSize(new Dimension(90, 50));
        buttonSwitch.setBackground(new Color(120,120,150));

        JButton buttonCalculate = new JButton("Calculate");
        buttonCalculate.setPreferredSize(new Dimension(90, 50));
        buttonCalculate.setBackground(new Color(120,120,150));

        panelButtons.setBackground(new Color(69,69,69));
        panelButtons.add(buttonSwitch);
        panelButtons.add(buttonCalculate);

        panelLabels.add(labelYourCards);
        panelLabels.add(labelHand);
        panelHand.add(panelLabels, BorderLayout.WEST);
        panelHand.add(panelButtons, BorderLayout.EAST);


        // Panel -> Cards
        JPanel panelCards = new JPanel();
        panelCards.setLayout(new BoxLayout(panelCards, BoxLayout.Y_AXIS));
        panelCards.setPreferredSize(new Dimension(400, 500));
        panelCards.setBackground(new Color(69,69,69));

        JLabel labelCards = new JLabel("Cards:");
        labelCards.setFont(labelCards.getFont().deriveFont(20.0f));
        labelCards.setForeground(new Color(120,120,150));
        labelCards.setBorder(BorderFactory.createEmptyBorder(10,0,10,130));
        panelCards.add(labelCards);

        JPanel panelCheck = new JPanel();
        panelCheck.setBackground(new Color(100,100,100));

        for(int i=0;i<4;++i){
            panelCardsArrayBig[i] = new JPanel();
            panelCardsArrayBig[i].setLayout(new BoxLayout(panelCardsArrayBig[i], BoxLayout.Y_AXIS));
            panelCardsArrayBig[i].add(new JLabel(colors[i]));
            for(int j=0;j<13;++j){
                cards[i][j] = new JCheckBox();
                cards[i][j].setBackground(new Color(100,100,100));
                panelCardsArraySmall[i][j] = new JPanel();
                panelCardsArraySmall[i][j].add(new JLabel(figures[j]));
                panelCardsArraySmall[i][j].add(cards[i][j]);
                panelCardsArraySmall[i][j].setBackground(new Color(100,100,100));
                panelCardsArrayBig[i].add(panelCardsArraySmall[i][j]);
            }
            panelCheck.add(panelCardsArrayBig[i]);
            panelCardsArrayBig[i].setBackground(new Color(100,100,100));
        }
        panelCards.add(panelCheck);


        // Adding Panels
        panelPlayer.add(panelStatistics);
        panelPlayer.add(panelHand);
        add(panelPlayer);
        add(panelCards);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Bus Driver");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new MainWindow());
        frame.pack();
        frame.setVisible(true);

    }

}
