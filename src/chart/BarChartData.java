package chart;

import java.util.Scanner;

public class BarChartData {
    private final int Complete;
    private final String date;
    private final int mode;

    public BarChartData(int Complete, String date, int mode) {
        this.Complete = Complete;
        this.date = date;
        this.mode = 0;
    }

    public int getComplete() {
        return Complete;
    }

    public String getDate() {
        return date;
    }

    public int getMode() {
        return mode;
    }

    public String getFormatDate() {

        Scanner scan = new Scanner(date);
        scan.next();
        String s = scan.next() + " " + scan.next() + "\n" + scan.next();
        scan.close();
        return s;
    }
}
