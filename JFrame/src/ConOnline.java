import equation.Exercise;
import check.CheckAnswer;
import check.CheckResultEnum;
import check.Submission;
import chart.ExerciseLog;
import chart.XMLFactory;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri May 15 23:38:42 CST 2020
 */


/**
 * @author Brainrain
 */
public class ConOnline extends JPanel {
    private final int exercisesPerPage = 16;
    private int totalEquations;
    private int pages;
    private int pageIndex = 0;
    Submission submission;
    CheckResultEnum[] checkedResult;
    Panel[] pagePanels;
    Exercise ex;

    public ConOnline(Exercise ex) {
        initComponents();
        this.ex = ex;
        initExercise();
        initExerciseGrid();
        updataPageIndex();
    }

    private void initExercise() {
        ex.clear();
        ex.generateEquations();
    }

    public void initExerciseGrid() {
        panelcheckResult.setVisible(false);
        panelCard.removeAll();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);
        flowLayout.setVgap(5);
        flowLayout.setAlignment(FlowLayout.LEFT);
        totalEquations = ex.getTotalEquations();

        int index = 0;
        pages = new Double(Math.ceil((double) totalEquations / exercisesPerPage)).intValue();
        pagePanels = new Panel[pages];
        for (int i = 0; i < pages; i++) {
            pagePanels[i] = new Panel(flowLayout);
            int exerciseThisPage = (totalEquations - index) % exercisesPerPage;
            if (i != pages - 1)     //is the last page ?
                exerciseThisPage = exercisesPerPage;
            ExerciseUnit[] exerciseUnits = new ExerciseUnit[exerciseThisPage];
            for (int j = 0; j < exerciseThisPage; j++) {
                exerciseUnits[j] = new ExerciseUnit(ex.get(index++));
                pagePanels[i].add(exerciseUnits[j]);
            }
            panelCard.add(pagePanels[i], "" + i);
        }
    }

    private void checkEquationAnswer() {
        CheckAnswer checkAnswer = new CheckAnswer(totalEquations);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < totalEquations; i++) {
            ExerciseUnit unit = (ExerciseUnit) pagePanels[i / exercisesPerPage].getComponent(i % exercisesPerPage);
            if (isNumeric(unit.getAnswer()))
                arrayList.add(Integer.parseInt(unit.getAnswer()));
            else
                arrayList.add(-1);
        }
        submission = new Submission(arrayList);
        checkAnswer.check(ex, submission);
        checkedResult = checkAnswer.getCheckedResult();
        showStatistics(checkAnswer);
    }

    private void showStatistics(CheckAnswer checkAnswer) {
        labelRight.setText(Integer.toString(checkAnswer.getRightEquations()));
        labelWrong.setText(Integer.toString(checkAnswer.getWrongEquations()));
        labelComplete.setText(Integer.toString(checkAnswer.getCompleteEquations()));
        panelcheckResult.setVisible(true);
    }

    private void showCheckedEquationResult() {
        for (int i = 0; i < totalEquations; i++) {
            ExerciseUnit unit = (ExerciseUnit) pagePanels[i / exercisesPerPage].getComponent(i % exercisesPerPage);
            unit.showAnswer();
            switch (checkedResult[i]) {
                case UNDO:
                    unit.setColor(new Color(184, 184, 184));
                    break;
                case RIGHT:
                    unit.setColor(new Color(26, 191, 81));
                    break;
                case WRONG:
                    unit.setColor(new Color(204, 75, 23));
                    break;
            }
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("\\d{1,10}");
        return pattern.matcher(str).matches();
    }

    private void ButtonPreActionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) panelCard.getLayout();
        cardLayout.previous(panelCard);
        pageIndex = (pageIndex - 1 + pages) % pages;
        updataPageIndex();
    }

    private void buttonNewActionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) panelCard.getLayout();
        initExercise();
        initExerciseGrid();
        cardLayout.first(panelCard);
        pageIndex = 0;
        updataPageIndex();
    }

    private void buttonSubmitActionPerformed(ActionEvent e) {
        checkEquationAnswer();
        //paint
        showCheckedEquationResult();

        submitToLog();
    }

    private void submitToLog() {
        XMLFactory.addLog(new ExerciseLog(ex, submission));
    }

    private void ButtonNextActionPerformed(ActionEvent e) {
        CardLayout cardLayout = (CardLayout) panelCard.getLayout();
        cardLayout.next(panelCard);
        pageIndex = (pageIndex + 1) % pages;
        updataPageIndex();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panelCard = new JPanel();
        ButtonPre = new JButton();
        ButtonNext = new JButton();
        labelPage = new JLabel();
        buttonSubmit = new JButton();
        buttonNew = new JButton();
        panelcheckResult = new JPanel();
        panelRight = new JPanel();
        labelR = new JLabel();
        labelRight = new JLabel();
        panelWrong = new JPanel();
        labelW = new JLabel();
        labelWrong = new JLabel();
        panelComplete = new JPanel();
        labelC = new JLabel();
        labelComplete = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setFont(new Font("Century Gothic", Font.BOLD, 36));
        setLayout(null);

        //======== panelCard ========
        {
            panelCard.setBackground(Color.white);
            panelCard.setLayout(new CardLayout());
        }
        add(panelCard);
        panelCard.setBounds(65, 40, 1010, 365);

        //---- ButtonPre ----
        ButtonPre.setBackground(new Color(0, 71, 160));
        ButtonPre.setForeground(new Color(0, 71, 160));
        ButtonPre.setFont(new Font("Century Gothic", Font.BOLD, 26));
        ButtonPre.setIcon(new ImageIcon(getClass().getResource("/pre.png")));
        ButtonPre.addActionListener(e -> ButtonPreActionPerformed(e));
        add(ButtonPre);
        ButtonPre.setBounds(15, 545, 80, 70);

        //---- ButtonNext ----
        ButtonNext.setBackground(new Color(0, 71, 160));
        ButtonNext.setForeground(new Color(0, 71, 160));
        ButtonNext.setFont(new Font("Century Gothic", Font.BOLD, 26));
        ButtonNext.setIcon(new ImageIcon(getClass().getResource("/next.png")));
        ButtonNext.addActionListener(e -> ButtonNextActionPerformed(e));
        add(ButtonNext);
        ButtonNext.setBounds(1005, 550, 80, 70);

        //---- labelPage ----
        labelPage.setText("page");
        labelPage.setFont(new Font("Century Gothic", Font.BOLD, 26));
        labelPage.setForeground(new Color(0, 71, 160));
        add(labelPage);
        labelPage.setBounds(500, 560, 85, 45);

        //---- buttonSubmit ----
        buttonSubmit.setText("Submit");
        buttonSubmit.setFont(new Font("Century Gothic", Font.BOLD, 24));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBackground(new Color(0, 71, 160));
        buttonSubmit.addActionListener(e -> buttonSubmitActionPerformed(e));
        add(buttonSubmit);
        buttonSubmit.setBounds(580, 555, 135, 55);

        //---- buttonNew ----
        buttonNew.setText("New");
        buttonNew.setBackground(new Color(0, 71, 160));
        buttonNew.setForeground(Color.white);
        buttonNew.setFont(new Font("Century Gothic", Font.BOLD, 24));
        buttonNew.addActionListener(e -> buttonNewActionPerformed(e));
        add(buttonNew);
        buttonNew.setBounds(340, 555, 135, 55);

        //======== panelcheckResult ========
        {
            panelcheckResult.setBackground(Color.white);
            panelcheckResult.setLayout(null);

            //======== panelRight ========
            {
                panelRight.setBackground(new Color(0, 153, 0));
                panelRight.setLayout(null);

                //---- labelR ----
                labelR.setText("\u6b63\u786e");
                labelR.setBackground(new Color(204, 51, 0));
                labelR.setIcon(null);
                labelR.setIconTextGap(10);
                labelR.setForeground(Color.white);
                labelR.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                panelRight.add(labelR);
                labelR.setBounds(15, 10, 45, 35);

                //---- labelRight ----
                labelRight.setText("00");
                labelRight.setBackground(new Color(204, 51, 0));
                labelRight.setIcon(null);
                labelRight.setIconTextGap(10);
                labelRight.setForeground(Color.white);
                labelRight.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
                panelRight.add(labelRight);
                labelRight.setBounds(70, 15, 40, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panelRight.getComponentCount(); i++) {
                        Rectangle bounds = panelRight.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panelRight.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panelRight.setMinimumSize(preferredSize);
                    panelRight.setPreferredSize(preferredSize);
                }
            }
            panelcheckResult.add(panelRight);
            panelRight.setBounds(10, 10, 125, 55);

            //======== panelWrong ========
            {
                panelWrong.setBackground(new Color(202, 47, 21));
                panelWrong.setLayout(null);

                //---- labelW ----
                labelW.setText("\u9519\u8bef");
                labelW.setBackground(new Color(204, 51, 0));
                labelW.setIcon(null);
                labelW.setIconTextGap(10);
                labelW.setForeground(Color.white);
                labelW.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                panelWrong.add(labelW);
                labelW.setBounds(15, 10, 45, 35);

                //---- labelWrong ----
                labelWrong.setText("00");
                labelWrong.setBackground(new Color(204, 51, 0));
                labelWrong.setIcon(null);
                labelWrong.setIconTextGap(10);
                labelWrong.setForeground(Color.white);
                labelWrong.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
                panelWrong.add(labelWrong);
                labelWrong.setBounds(70, 15, 40, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panelWrong.getComponentCount(); i++) {
                        Rectangle bounds = panelWrong.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panelWrong.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panelWrong.setMinimumSize(preferredSize);
                    panelWrong.setPreferredSize(preferredSize);
                }
            }
            panelcheckResult.add(panelWrong);
            panelWrong.setBounds(155, 10, 125, 55);

            //======== panelComplete ========
            {
                panelComplete.setBackground(new Color(0, 102, 204));
                panelComplete.setLayout(null);

                //---- labelC ----
                labelC.setText("\u5b8c\u6210");
                labelC.setBackground(new Color(204, 51, 0));
                labelC.setIcon(null);
                labelC.setIconTextGap(10);
                labelC.setForeground(Color.white);
                labelC.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
                panelComplete.add(labelC);
                labelC.setBounds(15, 10, 45, 35);

                //---- labelComplete ----
                labelComplete.setText("00");
                labelComplete.setBackground(new Color(204, 51, 0));
                labelComplete.setIcon(null);
                labelComplete.setIconTextGap(10);
                labelComplete.setForeground(Color.white);
                labelComplete.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
                panelComplete.add(labelComplete);
                labelComplete.setBounds(70, 15, 40, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panelComplete.getComponentCount(); i++) {
                        Rectangle bounds = panelComplete.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panelComplete.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panelComplete.setMinimumSize(preferredSize);
                    panelComplete.setPreferredSize(preferredSize);
                }
            }
            panelcheckResult.add(panelComplete);
            panelComplete.setBounds(300, 10, 125, 55);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelcheckResult.getComponentCount(); i++) {
                    Rectangle bounds = panelcheckResult.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelcheckResult.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelcheckResult.setMinimumSize(preferredSize);
                panelcheckResult.setPreferredSize(preferredSize);
            }
        }
        add(panelcheckResult);
        panelcheckResult.setBounds(310, 435, 435, 75);

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


    private void updataPageIndex() {
        labelPage.setText((pageIndex + 1) + "/" + pages);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panelCard;
    private JButton ButtonPre;
    private JButton ButtonNext;
    private JLabel labelPage;
    private JButton buttonSubmit;
    private JButton buttonNew;
    private JPanel panelcheckResult;
    private JPanel panelRight;
    private JLabel labelR;
    private JLabel labelRight;
    private JPanel panelWrong;
    private JLabel labelW;
    private JLabel labelWrong;
    private JPanel panelComplete;
    private JLabel labelC;
    private JLabel labelComplete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
