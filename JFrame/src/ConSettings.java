import javax.swing.event.*;
import ch1.Exercise;
import ch2.ExerciseFileOperation;
import ch4.CountDown;
import ch4.SettingProperty;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
/*
 * Created by JFormDesigner on Thu May 28 09:41:14 CST 2020
 */


/**
 * @author Brainrain
 */
public class ConSettings extends JPanel {
    private final SettingProperty settingProperty;
    private final Exercise exercise;
    private final CountDown countDown;
    private final String[] totalEquationSelections = {"50", "20", "40", "60", "80", "100", "120"};
    private final String[] exerciseModes = {"Mix", "Add", "Sub"};
    private final String[] enableSelections = {"Off", "On"};

    public ConSettings(Exercise exercise, CountDown countDown) {
        settingProperty = new SettingProperty();
        this.exercise = exercise;
        this.countDown = countDown;
        initComponents();
        initComboBox();
        initSpinner();
    }

    private void initSpinner() {
        spinnerMinutes.setValue(countDown.getMinutes());
        spinnerSeconds.setValue(countDown.getSeconds());
    }

    public Exercise getExercise() {
        return exercise;
    }

    private void initComboBox() {
        for (String s : enableSelections)
            comboBoxEnableCountdown.addItem(s);
        for (String s : totalEquationSelections)
            comboBoxTotal.addItem(s);
        for (String s : exerciseModes)
            comboBoxMode.addItem(s);
        initEquationSelections();
        comboBoxMode.setSelectedIndex(exercise.getMode());
        comboBoxEnableCountdown.setSelectedIndex(countDown.getEnable());

        comboBoxTotal.addItemListener(this::comboBoxTotalItemStateChanged);
        comboBoxMode.addItemListener(this::comboBoxModeItemStateChanged);
        comboBoxEnableCountdown.addItemListener(this::ComboBoxEnableCountdownItemStateChanged);
    }

    private void initEquationSelections() {
        for (int i = 0; i < totalEquationSelections.length; i++)
            if (totalEquationSelections[i].equals(Integer.toString(exercise.getTotalEquations()))) {
                comboBoxTotal.setSelectedIndex(i);
                break;
            }
    }

    private void ComboBoxEnableCountdownItemStateChanged(ItemEvent e) {
        int enable = comboBoxEnableCountdown.getSelectedIndex();
        countDown.setEnable(enable);
        settingProperty.writeSettings(countDown);
    }

    private void comboBoxTotalItemStateChanged(ItemEvent e) {
        int totalEquations = Integer.parseInt((String) Objects.requireNonNull(comboBoxTotal.getSelectedItem()));
        exercise.setTotalEquations(totalEquations);
        settingProperty.writeSettings(exercise);
    }

    private void comboBoxModeItemStateChanged(ItemEvent e) {
        String exerciseMode = (String) Objects.requireNonNull(comboBoxMode.getSelectedItem());
        int mode = 0;
        for (int i = 0; i < exerciseModes.length; i++)
            if (exerciseModes[i].equals(exerciseMode))
                mode = i;
        exercise.setMode(mode);
        settingProperty.writeSettings(exercise);
    }

    private void spinnerMinutesStateChanged(ChangeEvent e) {
        int minutes = (int) spinnerMinutes.getValue();
        countDown.setMinutes(minutes);
        settingProperty.writeSettings(countDown);
    }

    private void spinnerSecondsStateChanged(ChangeEvent e) {
        int seconds = (int) spinnerSeconds.getValue();
        countDown.setSeconds(seconds);
        settingProperty.writeSettings(countDown);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        comboBoxMode = new JComboBox();
        comboBoxEnableCountdown = new JComboBox();
        spinnerMinutes = new JSpinner();
        spinnerSeconds = new JSpinner();
        separator1 = new JSeparator();
        labelExercise = new JLabel();
        labelExercise2 = new JLabel();
        labelExercise3 = new JLabel();
        comboBoxTotal = new JComboBox();
        labelCountdown = new JLabel();
        labelCountdown2 = new JLabel();
        labelCountdown3 = new JLabel();
        labelMinutes = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setFont(new Font("Century Gothic", Font.BOLD, 28));
        setLayout(null);

        //---- comboBoxMode ----
        comboBoxMode.setFont(new Font("Century Gothic", Font.BOLD, 28));
        comboBoxMode.setForeground(new Color(0, 71, 160));
        comboBoxMode.setBackground(Color.white);
        add(comboBoxMode);
        comboBoxMode.setBounds(385, 110, 150, 50);

        //---- comboBoxEnableCountdown ----
        comboBoxEnableCountdown.setFont(new Font("Century Gothic", Font.BOLD, 28));
        comboBoxEnableCountdown.setForeground(new Color(0, 71, 160));
        comboBoxEnableCountdown.setBackground(Color.white);
        add(comboBoxEnableCountdown);
        comboBoxEnableCountdown.setBounds(435, 390, 120, 50);

        //---- spinnerMinutes ----
        spinnerMinutes.setBackground(Color.white);
        spinnerMinutes.setForeground(new Color(0, 71, 160));
        spinnerMinutes.setFont(new Font("Century Gothic", Font.BOLD, 28));
        spinnerMinutes.setModel(new SpinnerNumberModel(10, 0, 60, 1));
        spinnerMinutes.addChangeListener(e -> spinnerMinutesStateChanged(e));
        add(spinnerMinutes);
        spinnerMinutes.setBounds(435, 455, 60, 40);

        //---- spinnerSeconds ----
        spinnerSeconds.setBackground(Color.white);
        spinnerSeconds.setForeground(new Color(0, 71, 160));
        spinnerSeconds.setFont(new Font("Century Gothic", Font.BOLD, 28));
        spinnerSeconds.setModel(new SpinnerNumberModel(0, 0, 60, 1));
        spinnerSeconds.addChangeListener(e -> spinnerSecondsStateChanged(e));
        add(spinnerSeconds);
        spinnerSeconds.setBounds(505, 455, 60, 40);
        add(separator1);
        separator1.setBounds(70, 275, 1025, 20);

        //---- labelExercise ----
        labelExercise.setText("Exercise Settings");
        labelExercise.setFont(new Font("Century Gothic", Font.BOLD, 28));
        labelExercise.setForeground(new Color(0, 71, 160));
        labelExercise.setBackground(Color.white);
        add(labelExercise);
        labelExercise.setBounds(60, 35, 240, 65);

        //---- labelExercise2 ----
        labelExercise2.setText("Exercise Mode");
        labelExercise2.setFont(new Font("Century Gothic", Font.BOLD, 22));
        labelExercise2.setForeground(new Color(0, 71, 160));
        labelExercise2.setBackground(Color.white);
        add(labelExercise2);
        labelExercise2.setBounds(180, 110, 160, 50);

        //---- labelExercise3 ----
        labelExercise3.setText("Total Equations");
        labelExercise3.setFont(new Font("Century Gothic", Font.BOLD, 22));
        labelExercise3.setForeground(new Color(0, 71, 160));
        labelExercise3.setBackground(Color.white);
        add(labelExercise3);
        labelExercise3.setBounds(180, 175, 160, 50);

        //---- comboBoxTotal ----
        comboBoxTotal.setFont(new Font("Century Gothic", Font.BOLD, 28));
        comboBoxTotal.setForeground(new Color(0, 71, 160));
        comboBoxTotal.setBackground(Color.white);
        add(comboBoxTotal);
        comboBoxTotal.setBounds(385, 175, 150, 50);

        //---- labelCountdown ----
        labelCountdown.setText("Countdown Settings");
        labelCountdown.setFont(new Font("Century Gothic", Font.BOLD, 28));
        labelCountdown.setForeground(new Color(0, 71, 160));
        labelCountdown.setBackground(Color.white);
        add(labelCountdown);
        labelCountdown.setBounds(60, 305, 290, 65);

        //---- labelCountdown2 ----
        labelCountdown2.setText("Countdown Enable");
        labelCountdown2.setFont(new Font("Century Gothic", Font.BOLD, 22));
        labelCountdown2.setForeground(new Color(0, 71, 160));
        labelCountdown2.setBackground(Color.white);
        add(labelCountdown2);
        labelCountdown2.setBounds(175, 385, 215, 50);

        //---- labelCountdown3 ----
        labelCountdown3.setText("Countdown Settings");
        labelCountdown3.setFont(new Font("Century Gothic", Font.BOLD, 22));
        labelCountdown3.setForeground(new Color(0, 71, 160));
        labelCountdown3.setBackground(Color.white);
        add(labelCountdown3);
        labelCountdown3.setBounds(175, 445, 215, 50);

        //---- labelMinutes ----
        labelMinutes.setText(":");
        labelMinutes.setFont(new Font("Century Gothic", Font.BOLD, 30));
        labelMinutes.setForeground(new Color(0, 71, 160));
        labelMinutes.setBackground(Color.white);
        add(labelMinutes);
        labelMinutes.setBounds(495, 460, 15, 30);

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
    private JComboBox comboBoxMode;
    private JComboBox comboBoxEnableCountdown;
    private JSpinner spinnerMinutes;
    private JSpinner spinnerSeconds;
    private JSeparator separator1;
    private JLabel labelExercise;
    private JLabel labelExercise2;
    private JLabel labelExercise3;
    private JComboBox comboBoxTotal;
    private JLabel labelCountdown;
    private JLabel labelCountdown2;
    private JLabel labelCountdown3;
    private JLabel labelMinutes;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
