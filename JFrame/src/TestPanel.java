import equation.Exercise;
import chart.CountDown;
import chart.SettingProperty;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Wed May 13 21:53:41 CST 2020
 */


/**
 * @author Brainrain
 */
public class TestPanel extends JFrame {
    private final SettingProperty settingProperty;
    private CardLayout cardLayout;
    private Exercise exercise;
    private CountDown countDown;

    public TestPanel() {
        initComponents();
        settingProperty = new SettingProperty();
        initExercise();
        initCountDown();
        TimeNow();
        setVisible(true);
        initContainer();
    }

    private void initExercise() {
        exercise = new Exercise();
        settingProperty.loadSettings(exercise);
    }

    private void initCountDown() {
        countDown = new CountDown(0, 0, 0);
        settingProperty.loadSettings(countDown);
    }

    private void initContainer() {
        cardLayout = (CardLayout) panelContainer.getLayout();

        ConHome conHome = new ConHome();
        panelContainer.add(conHome, "conHome");
        ConOnline conOnline = new ConOnline(exercise);
        panelContainer.add(conOnline, "conOnline");
        ConSettings conSettings = new ConSettings(exercise, countDown);
        panelContainer.add(conSettings, "conSettings");
        ConOffline conOffline = new ConOffline(countDown);
        panelContainer.add(conOffline, "conOffline");
        ConReview conReview = new ConReview();
        panelContainer.add(conReview, "conReview");
    }

    private void ButtonHomeMouseClicked(ActionEvent e) {
        cardLayout.show(panelContainer, "conHome");
    }

    private void ButtonOnlineActionPerformed(ActionEvent e) {
        cardLayout.show(panelContainer, "conOnline");
    }

    private void ButtonSettingsActionPerformed(ActionEvent e) {
        cardLayout.show(panelContainer, "conSettings");
    }

    private void ButtonOfflineActionPerformed(ActionEvent e) {
        cardLayout.show(panelContainer, "conOffline");
    }

    private void ButtonReviewActionPerformed(ActionEvent e) {
        cardLayout.show(panelContainer, "conReview");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panelTimer = new JPanel();
        JLabelTime = new JLabel();
        labelIcon = new JLabel();
        panelMenu = new JPanel();
        ButtonHome = new JButton();
        ButtonOnline = new JButton();
        ButtonOffline = new JButton();
        ButtonSettings = new JButton();
        ButtonReview = new JButton();
        panelContainer = new JPanel();
        panelTitle = new JPanel();
        labelCountDown = new JLabel();

        //======== this ========
        setTitle("Exercise");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panelTimer ========
        {
            panelTimer.setBackground(new Color(0, 71, 160));
            panelTimer.setFont(new Font("Century Gothic", Font.PLAIN, 12));
            panelTimer.setLayout(null);

            //---- JLabelTime ----
            JLabelTime.setText("TIme Now");
            JLabelTime.setFont(new Font("Century Gothic", Font.BOLD, 22));
            JLabelTime.setForeground(SystemColor.window);
            panelTimer.add(JLabelTime);
            JLabelTime.setBounds(new Rectangle(new Point(40, 110), JLabelTime.getPreferredSize()));

            //---- labelIcon ----
            labelIcon.setIcon(new ImageIcon(getClass().getResource("/iconMain.png")));
            panelTimer.add(labelIcon);
            labelIcon.setBounds(55, 25, 65, 80);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelTimer.getComponentCount(); i++) {
                    Rectangle bounds = panelTimer.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelTimer.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelTimer.setMinimumSize(preferredSize);
                panelTimer.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelTimer);
        panelTimer.setBounds(0, 0, 174, 164);

        //======== panelMenu ========
        {
            panelMenu.setBackground(new Color(0, 71, 160));
            panelMenu.setLayout(null);

            //---- ButtonHome ----
            ButtonHome.setText("Home");
            ButtonHome.setBackground(Color.white);
            ButtonHome.setForeground(new Color(0, 71, 160));
            ButtonHome.setFont(new Font("Century Gothic", Font.BOLD, 26));
            ButtonHome.addActionListener(e -> ButtonHomeMouseClicked(e));
            panelMenu.add(ButtonHome);
            ButtonHome.setBounds(5, 5, 165, 70);

            //---- ButtonOnline ----
            ButtonOnline.setText("Online");
            ButtonOnline.setBackground(Color.white);
            ButtonOnline.setForeground(new Color(0, 71, 160));
            ButtonOnline.setFont(new Font("Century Gothic", Font.BOLD, 26));
            ButtonOnline.addActionListener(e -> ButtonOnlineActionPerformed(e));
            panelMenu.add(ButtonOnline);
            ButtonOnline.setBounds(5, 80, 165, 70);

            //---- ButtonOffline ----
            ButtonOffline.setText("Offline");
            ButtonOffline.setBackground(Color.white);
            ButtonOffline.setForeground(new Color(0, 71, 160));
            ButtonOffline.setFont(new Font("Century Gothic", Font.BOLD, 26));
            ButtonOffline.addActionListener(e -> ButtonOfflineActionPerformed(e));
            panelMenu.add(ButtonOffline);
            ButtonOffline.setBounds(5, 155, 165, 70);

            //---- ButtonSettings ----
            ButtonSettings.setText("Settings");
            ButtonSettings.setBackground(Color.white);
            ButtonSettings.setForeground(new Color(0, 71, 160));
            ButtonSettings.setFont(new Font("Century Gothic", Font.BOLD, 26));
            ButtonSettings.addActionListener(e -> ButtonSettingsActionPerformed(e));
            panelMenu.add(ButtonSettings);
            ButtonSettings.setBounds(5, 305, 165, 70);

            //---- ButtonReview ----
            ButtonReview.setText("Review");
            ButtonReview.setBackground(Color.white);
            ButtonReview.setForeground(new Color(0, 71, 160));
            ButtonReview.setFont(new Font("Century Gothic", Font.BOLD, 26));
            ButtonReview.addActionListener(e -> ButtonReviewActionPerformed(e));
            panelMenu.add(ButtonReview);
            ButtonReview.setBounds(5, 230, 165, 70);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelMenu.getComponentCount(); i++) {
                    Rectangle bounds = panelMenu.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelMenu.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelMenu.setMinimumSize(preferredSize);
                panelMenu.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelMenu);
        panelMenu.setBounds(0, 170, 174, 518);

        //======== panelContainer ========
        {
            panelContainer.setBackground(Color.white);
            panelContainer.setLayout(new CardLayout());
        }
        contentPane.add(panelContainer);
        panelContainer.setBounds(180, 60, 1100, 630);

        //======== panelTitle ========
        {
            panelTitle.setBackground(new Color(0, 71, 160));

            //---- labelCountDown ----
            labelCountDown.setText("Count");
            labelCountDown.setFont(new Font("Century Gothic", Font.BOLD, 30));
            labelCountDown.setVisible(false);

            GroupLayout panelTitleLayout = new GroupLayout(panelTitle);
            panelTitle.setLayout(panelTitleLayout);
            panelTitleLayout.setHorizontalGroup(
                panelTitleLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                        .addContainerGap(1083, Short.MAX_VALUE)
                        .addComponent(labelCountDown, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
            );
            panelTitleLayout.setVerticalGroup(
                panelTitleLayout.createParallelGroup()
                    .addComponent(labelCountDown, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );
        }
        contentPane.add(panelTitle);
        panelTitle.setBounds(180, 0, 1100, 55);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    public void TimeNow() {
        Timer timer = new Timer(100, e -> {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            JLabelTime.setText(dateFormat.format(date));
        });
        timer.start();
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panelTimer;
    private JLabel JLabelTime;
    private JLabel labelIcon;
    private JPanel panelMenu;
    private JButton ButtonHome;
    private JButton ButtonOnline;
    private JButton ButtonOffline;
    private JButton ButtonSettings;
    private JButton ButtonReview;
    private JPanel panelContainer;
    private JPanel panelTitle;
    private JLabel labelCountDown;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new TestPanel();
    }
}
