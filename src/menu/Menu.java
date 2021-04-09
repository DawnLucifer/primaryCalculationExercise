package menu;

import equation.Exercise;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

public abstract class Menu {
    public String[] exerciseMode = {
            "混合模式",
            "加法模式",
            "减法模式",
    };

    public String[] exerciseModeEN = {
            "mix",
            "add",
            "sub",
    };

    public void printMenu(String[] allMenu) {
        for (int i = 0; i < allMenu.length; i++)
            System.out.println(i + ": " + allMenu[i]);
    }

    public void illegalInput() {
        System.err.println("Illegal Input!");
        waitInput();
    }

    public void notImplement() {
        System.out.println("Not Implemented Yet");
        waitInput();
    }

    public void waitInput() {
        Scanner scan = new Scanner(System.in);
        System.out.println("按任意键回车后继续...");
        scan.next();
    }

    public void printExerciseMode() {
        for (int i = 0; i < exerciseMode.length; i++)
            System.out.println(i + ": " + exerciseMode[i]);
    }

    public void loadSettings(Exercise ex, String lineMode) {
        Properties properties = new Properties();
        try {
            FileInputStream fileIn = new FileInputStream("settings.properties");
            properties.load(fileIn);
            ex.setMode(Integer.parseInt(properties.getProperty(lineMode + "Mode")));
            ex.setTotalEquations(Integer.parseInt(properties.getProperty(lineMode + "TotalEquations")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewSetting(Exercise ex) {
        System.out.println("当前设置为:");
        System.out.println("练习模式为: " + exerciseMode[ex.getMode()]);
        System.out.println("练习数量为: " + ex.getTotalEquations());
        System.out.println("修改设置请用记事本打开settings.properties");
        waitInput();
//        printExerciseMode();
    }
}
