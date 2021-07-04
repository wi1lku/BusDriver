package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JPanel implements ActionListener {

    JCheckBox[][] cards = new JCheckBox[4][13];
    boolean[][] cardsState = new boolean[4][13];
    boolean[][] cardsStateYours = new boolean[4][13];
    JPanel[] panelCardsArrayBig = new JPanel[4];
    JPanel[][] panelCardsArraySmall = new JPanel[4][13];
    String[] colors = { "Spades", "Hearts", "Clubs", "Diamonds" };
    String[] figures = { "2", "3", "4", "5", "6", "7", "8", "9", "X", "J", "Q", "K", "A"};
    boolean yours = false;
    JButton buttonSwitch;
    JButton buttonCalculate;
    JLabel labelCards;
    StatsPanel stat0;
    StatsPanel stat1;
    StatsPanel stat2;
    StatsPanel stat3;

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

        stat0 = new StatsPanel("Black : Red");
        stat1 = new StatsPanel("Higher : Even : Lower");
        stat2 = new StatsPanel("In : Not In");
        stat3 = new StatsPanel("Color : No Color");

        panelStatistics.add(stat0);
        panelStatistics.add(stat1);
        panelStatistics.add(stat2);
        panelStatistics.add(stat3);


        // Panel -> Player -> Hand
        JPanel panelHand = new JPanel();
        panelHand.setLayout(new BorderLayout());
        panelHand.setPreferredSize(new Dimension(400,100));
        panelHand.setBackground(new Color(69,69,69));

        JPanel panelLabels = new JPanel();
        panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
        panelLabels.setBackground(new Color(69,69,69));

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(1,2,10,10));
        panelButtons.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        buttonSwitch = new JButton("Yours");
        buttonSwitch.setPreferredSize(new Dimension(90, 50));
        buttonSwitch.setBackground(new Color(120,120,150));
        buttonSwitch.addActionListener(this);

        buttonCalculate = new JButton("Calculate");
        buttonCalculate.setPreferredSize(new Dimension(90, 50));
        buttonCalculate.setBackground(new Color(120,120,150));
        buttonCalculate.addActionListener(this);

        panelButtons.setBackground(new Color(69,69,69));
        panelButtons.add(buttonSwitch);
        panelButtons.add(buttonCalculate);

        panelHand.add(panelLabels, BorderLayout.WEST);
        panelHand.add(panelButtons, BorderLayout.EAST);


        // Panel -> Cards
        JPanel panelCards = new JPanel();
        panelCards.setLayout(new BoxLayout(panelCards, BoxLayout.Y_AXIS));
        panelCards.setPreferredSize(new Dimension(400, 500));
        panelCards.setBackground(new Color(69,69,69));

        labelCards = new JLabel("All Cards:");
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
                cards[i][j].addActionListener(this);
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


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonSwitch) {
            if (!yours){

                for(int i=0;i<4;++i){
                    for(int j=0;j<13;++j){
                        cards[i][j].setSelected(cardsStateYours[i][j]);
                    }
                }

                yours = true;
                buttonSwitch.setText("All");
                labelCards.setText("Your Cards");

            } else{

                for(int i=0;i<4;++i){
                    for(int j=0;j<13;++j){
                        cards[i][j].setSelected(cardsState[i][j]);
                    }
                }

                yours = false;
                buttonSwitch.setText("Yours");
                labelCards.setText("All Cards");

            }

        } else if (e.getSource() == buttonCalculate){

            int cardsNumber = 0;
            for(int i=0;i<4;++i){
                for(int j=0;j<13;++j){
                    if(cardsStateYours[i][j]) cardsNumber++;
                }
            }

            String s;
            switch(cardsNumber){
                case 0:
                    int black = 26;
                    int red = 26;
                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if (i%2 == 1) if(cardsState[i][j]) red--;
                            if (i%2 == 0) if(cardsState[i][j]) black--;
                        }
                    }
                    s = black + " : " + red;
                    stat0.setChances(s);
                    break;
                case 1:
                    int cntHigher = 0;
                    int cntEven = 0;
                    int cntLower = 0;
                    int index_j = -1;

                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if(cardsStateYours[i][j]){
                                index_j = j;
                            }
                        }
                    }

                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if(!cardsState[i][j]){
                                if (j > index_j) cntHigher++;
                                else if (j == index_j) cntEven++;
                                else cntLower++;
                            }
                        }
                    }

                    s = cntHigher + " : " + cntEven + " : " + cntLower;
                    stat1.setChances(s);
                    break;
                case 2:
                    int index_j1 = -1;
                    int index_j2 = -1;
                    int cntYes = 0;
                    int cntNo = 0;

                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if(cardsStateYours[i][j]){
                                if (index_j1 == -1) index_j1 = j;
                                else index_j2 = j;
                            }
                        }
                    }

                    if(index_j2 < index_j1){
                        int t = index_j1;
                        index_j1 = index_j2;
                        index_j2 = t;
                    }

                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if(!cardsState[i][j]){
                                if (j >= index_j1 && j <= index_j2) cntYes++;
                                else cntNo++;
                            }
                        }
                    }

                    s = cntYes + " : " + cntNo;
                    stat2.setChances(s);
                    break;
                case 3:
                    int cntGot = 0;
                    int cntNot = 0;
                    boolean[] gotColors = new boolean[4];

                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if (cardsStateYours[i][j]) {
                                gotColors[i] = true;
                                break;
                            }
                        }
                    }

                    for(int i=0;i<4;++i){
                        for(int j=0;j<13;++j){
                            if(!cardsState[i][j] && gotColors[i]) cntGot++;
                            else if(!cardsState[i][j] && !gotColors[i]) cntNot++;
                        }
                    }

                    s = cntGot + " : " + cntNot;
                    stat3.setChances(s);
                    break;
            }

        }

        if (e.getSource() instanceof JCheckBox){

            for(int i=0;i<4;++i){
                for(int j=0;j<13;++j){
                    if(yours){
                        if (cardsStateYours[i][j] && !cards[i][j].isSelected()) cardsState[i][j] = false;
                        cardsStateYours[i][j] = cards[i][j].isSelected();
                        if (cardsStateYours[i][j] && !cardsState[i][j]) cardsState[i][j] = true;
                    } else{
                        cardsState[i][j] = cards[i][j].isSelected();
                        if (cardsStateYours[i][j] && !cardsState[i][j]) cardsStateYours[i][j] = false;
                    }
                }
            }

        }

    }

}
