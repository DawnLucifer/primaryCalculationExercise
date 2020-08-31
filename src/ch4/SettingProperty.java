package ch4;

import ch1.Exercise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SettingProperty {

    Properties properties;
    Set<String> settingNames;
    Map<String, String> map;

    public SettingProperty() {
        properties = new Properties();
        map = new HashMap<>();
    }


    public void loadSettings(Exercise exercise) {
        loadSettings();
        exercise.setMode(Integer.parseInt(map.get("exerciseMode")));
        exercise.setTotalEquations(Integer.parseInt(map.get("totalEquations")));
    }

    public void loadSettings(CountDown countDownTime) {
        loadSettings();
        countDownTime.setEnable(Integer.parseInt(map.get("enableCountdown")));
        countDownTime.setTime(Integer.parseInt(map.get("countdown")));
    }

    public void writeSettings(Exercise exercise) {
        loadSettings();
        map.put("totalEquations", Integer.toString(exercise.getTotalEquations()));
        map.put("exerciseMode", Integer.toString(exercise.getMode()));
        writeSettings();
    }

    public void writeSettings(CountDown countDownTime) {
        loadSettings();
        map.put("countdown", Integer.toString(countDownTime.getTime()));
        map.put("enableCountdown", Integer.toString(countDownTime.getEnable()));
        writeSettings();
    }

    private void writeSettings() {
        try {
            FileOutputStream fileOut = new FileOutputStream("settings.properties");
            for (String settingName : settingNames) {      //TODO ????
                properties.setProperty(settingName, map.get(settingName));
            }
            properties.store(fileOut, "you can try change it");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadSettings() {
        try {
            properties.load(new FileInputStream("settings.properties"));
            settingNames = properties.stringPropertyNames();    //TODO ????
            for (String settingName : settingNames) {
                map.put(settingName, properties.getProperty(settingName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
