import ch1.Equation;

import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat May 16 16:03:20 CST 2020
 */



/**
 * @author Brainrain
 */
public class ExerciseUnit extends JPanel {
    public ExerciseUnit(Equation equation) {
        initComponents();
        initEquation(equation);
    }

    private void initEquation(Equation equation) {
        labelExercise.setText(equation.toString());
        labelEqual.setText("=");
        textFieldBox.setHorizontalAlignment(JTextField.CENTER);
        labelRightAnwer.setText(Integer.toString(equation.getResult()));
        labelRightAnwer.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        labelExercise = new JLabel();
        textFieldBox = new JTextField();
        labelEqual = new JLabel();
        labelRightAnwer = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setFont(new Font("Century Gothic", Font.BOLD, 22));
        setLayout(null);

        //---- labelExercise ----
        labelExercise.setFont(new Font("Century Gothic", Font.BOLD, 30));
        labelExercise.setForeground(new Color(0, 71, 160));
        add(labelExercise);
        labelExercise.setBounds(10, 30, 105, 55);

        //---- textFieldBox ----
        textFieldBox.setForeground(new Color(0, 71, 160));
        textFieldBox.setFont(new Font("Century Gothic", Font.BOLD, 26));
        textFieldBox.setBackground(Color.white);
        add(textFieldBox);
        textFieldBox.setBounds(125, 30, 65, 55);

        //---- labelEqual ----
        labelEqual.setFont(new Font("Century Gothic", Font.BOLD, 30));
        labelEqual.setForeground(new Color(0, 71, 160));
        add(labelEqual);
        labelEqual.setBounds(100, 30, 30, 55);

        //---- labelRightAnwer ----
        labelRightAnwer.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        labelRightAnwer.setForeground(new Color(153, 153, 153));
        add(labelRightAnwer);
        labelRightAnwer.setBounds(190, 40, 40, 37);

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

    public String getEquation() {
        return labelExercise.getText();
    }

    public String getAnswer() {
        return textFieldBox.getText();
    }

    public void showAnswer() {
        labelRightAnwer.setVisible(true);
    }

    public void setColor(Color color) {
        textFieldBox.setBackground(color);
        textFieldBox.setForeground(Color.white);
    }

    public void setAnswer(int i) {
        textFieldBox.setText(Integer.toString(i));
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel labelExercise;
    private JTextField textFieldBox;
    private JLabel labelEqual;
    private JLabel labelRightAnwer;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
