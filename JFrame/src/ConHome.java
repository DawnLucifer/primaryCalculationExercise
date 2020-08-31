import java.awt.event.*;
import ch4.BarChartData;
import ch4.ChartGenerator;
import ch4.PieChartData;
import ch4.XMLFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
/*
 * Created by JFormDesigner on Thu May 14 12:29:21 CST 2020
 */


/**
 * @author Brainrain
 */
public class ConHome extends JPanel {
    private PieChartData pieChartData;
    private BarChartData[] barChartData;
    public ConHome() {
        initLineChart();
        initPieChart();
        initComponents();
        initPercentage();
        initLabelResult();
    }

    private void initLabelResult() {
        int right = pieChartData.getRight();
        int wrong = pieChartData.getWrong();
        labelRight.setText(Integer.toString(right));
        labelWrong.setText(Integer.toString(wrong));
        labelComplete.setText(Integer.toString(right + wrong));
    }

    private void initPercentage() {
        int right = pieChartData.getRight();
        int wrong = pieChartData.getWrong();
        int percent = right * 100 / (wrong + right);
        labelPercent.setText(percent + "%");
    }

    private void initPieChart() {
        pieChartData = XMLFactory.getPieChartData();
        ChartGenerator.getRingChart(pieChartData);
    }

    private void initLineChart() {
        barChartData = XMLFactory.getBarChartData();
        ChartGenerator.getLineChart(barChartData);
    }

    private void buttonFreshActionPerformed(ActionEvent e) {

        panelLineChart.removeAll();
        barChartData = XMLFactory.getBarChartData();
        JFreeChart chart = ChartGenerator.getLineChart(barChartData);
        panelLineChart.setLayout(new BorderLayout());
        panelLineChart.add(new ChartPanel(chart), BorderLayout.CENTER);


        panelPieChart.removeAll();
        pieChartData = XMLFactory.getPieChartData();
        JFreeChart ringChart = ChartGenerator.getRingChart(pieChartData);
        panelPieChart.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(ringChart);
        panelPieChart.add(chartPanel, BorderLayout.CENTER);
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(labelPercent, BorderLayout.CENTER);

        initLabelResult();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelTitle = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        labelRight = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        labelWrong = new JLabel();
        panel3 = new JPanel();
        label3 = new JLabel();
        labelComplete = new JLabel();
        panelLineChart = new JPanel();
        labelLineChart = new JLabel();
        panelPieChart = new JPanel();
        labelPercent = new JLabel();
        label4 = new JLabel();
        buttonFresh = new JButton();

        //======== this ========
        setBackground(Color.white);
        setFont(new Font("Century Gothic", Font.BOLD, 22));
        setLayout(null);

        //---- labelTitle ----
        labelTitle.setText("Primary calculation exercise");
        labelTitle.setFont(new Font("Century Gothic", Font.BOLD, 36));
        labelTitle.setForeground(new Color(0, 71, 160));
        add(labelTitle);
        labelTitle.setBounds(295, 25, 655, 95);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0, 153, 0));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setBackground(new Color(204, 51, 0));
            label1.setIcon(new ImageIcon(getClass().getResource("/right.png")));
            label1.setIconTextGap(10);
            label1.setForeground(Color.white);
            label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
            panel1.add(label1);
            label1.setBounds(20, -5, 80, 105);

            //---- labelRight ----
            labelRight.setText("0");
            labelRight.setBackground(new Color(204, 51, 0));
            labelRight.setIcon(null);
            labelRight.setIconTextGap(10);
            labelRight.setForeground(Color.white);
            labelRight.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 48));
            labelRight.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(labelRight);
            labelRight.setBounds(85, 25, 90, 45);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        add(panel1);
        panel1.setBounds(175, 135, 185, 90);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(202, 47, 21));
            panel2.setLayout(null);

            //---- label2 ----
            label2.setBackground(new Color(204, 51, 0));
            label2.setIcon(new ImageIcon(getClass().getResource("/wrong.png")));
            label2.setIconTextGap(10);
            label2.setForeground(Color.white);
            label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
            panel2.add(label2);
            label2.setBounds(15, -5, 75, 105);

            //---- labelWrong ----
            labelWrong.setText("0");
            labelWrong.setBackground(new Color(204, 51, 0));
            labelWrong.setIcon(null);
            labelWrong.setIconTextGap(10);
            labelWrong.setForeground(Color.white);
            labelWrong.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 48));
            labelWrong.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(labelWrong);
            labelWrong.setBounds(85, 25, 95, 45);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2);
        panel2.setBounds(440, 135, 185, 90);

        //======== panel3 ========
        {
            panel3.setBackground(new Color(0, 102, 204));
            panel3.setLayout(null);

            //---- label3 ----
            label3.setBackground(new Color(204, 51, 0));
            label3.setIcon(new ImageIcon(getClass().getResource("/iconMain.png")));
            label3.setIconTextGap(10);
            label3.setForeground(Color.white);
            label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
            panel3.add(label3);
            label3.setBounds(15, -5, 70, 105);

            //---- labelComplete ----
            labelComplete.setText("0");
            labelComplete.setBackground(new Color(204, 51, 0));
            labelComplete.setIcon(null);
            labelComplete.setIconTextGap(10);
            labelComplete.setForeground(Color.white);
            labelComplete.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 48));
            labelComplete.setHorizontalAlignment(SwingConstants.CENTER);
            panel3.add(labelComplete);
            labelComplete.setBounds(80, 25, 90, 45);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        add(panel3);
        panel3.setBounds(700, 135, 180, 90);

        //======== panelLineChart ========
        {
            panelLineChart.setBackground(Color.white);
            panelLineChart.setLayout(null);

            //---- labelLineChart ----
            labelLineChart.setBackground(Color.white);
            labelLineChart.setForeground(Color.white);
            labelLineChart.setIcon(new ImageIcon(getClass().getResource("/recent.png")));
            panelLineChart.add(labelLineChart);
            labelLineChart.setBounds(0, 0, 500, 300);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelLineChart.getComponentCount(); i++) {
                    Rectangle bounds = panelLineChart.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelLineChart.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelLineChart.setMinimumSize(preferredSize);
                panelLineChart.setPreferredSize(preferredSize);
            }
        }
        add(panelLineChart);
        panelLineChart.setBounds(80, 290, 500, 300);

        //======== panelPieChart ========
        {
            panelPieChart.setBackground(Color.white);
            panelPieChart.setLayout(null);

            //---- labelPercent ----
            labelPercent.setText("0%");
            labelPercent.setBackground(Color.white);
            labelPercent.setForeground(new Color(0, 71, 160));
            labelPercent.setFont(new Font("Century Gothic", Font.BOLD, 28));
            labelPercent.setHorizontalAlignment(SwingConstants.CENTER);
            panelPieChart.add(labelPercent);
            labelPercent.setBounds(130, 130, 90, 45);

            //---- label4 ----
            label4.setBackground(Color.white);
            label4.setForeground(Color.white);
            label4.setIcon(new ImageIcon(getClass().getResource("/statistics.png")));
            panelPieChart.add(label4);
            label4.setBounds(0, 0, 350, 300);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelPieChart.getComponentCount(); i++) {
                    Rectangle bounds = panelPieChart.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelPieChart.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelPieChart.setMinimumSize(preferredSize);
                panelPieChart.setPreferredSize(preferredSize);
            }
        }
        add(panelPieChart);
        panelPieChart.setBounds(675, 280, 350, 300);

        //---- buttonFresh ----
        buttonFresh.setText("Fresh");
        buttonFresh.setFont(new Font("Century Gothic", Font.BOLD, 24));
        buttonFresh.setForeground(Color.white);
        buttonFresh.setBackground(new Color(0, 71, 160));
        buttonFresh.addActionListener(e -> buttonFreshActionPerformed(e));
        add(buttonFresh);
        buttonFresh.setBounds(980, 20, 105, 45);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelTitle;
    private JPanel panel1;
    private JLabel label1;
    private JLabel labelRight;
    private JPanel panel2;
    private JLabel label2;
    private JLabel labelWrong;
    private JPanel panel3;
    private JLabel label3;
    private JLabel labelComplete;
    private JPanel panelLineChart;
    private JLabel labelLineChart;
    private JPanel panelPieChart;
    private JLabel labelPercent;
    private JLabel label4;
    private JButton buttonFresh;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
