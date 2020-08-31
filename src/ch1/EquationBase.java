package ch1;

import java.io.*;
import java.util.Scanner;

public abstract class EquationBase {

    protected int bound = 100;
    protected int[][] base;
    //base引用用来统一读写

    public EquationBase() {
    }

    public int[][] getBase() {
        return base;
    }

    public abstract void generateBase();

    public int getValue(int i, int j) {
        return base[i][j];
    }

    public void writeBase(String fileName) {
        File baseFile = new File(fileName);
        Writer out = null;
        try {
            out = new FileWriter(baseFile, false);
            for (int i = 0; i < bound; i++) {
                for (int j = 0; j < bound; j++)
                    out.write(base[i][j] + ",");
                out.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readBase(String fileName) {
        base = new int [bound][bound];
        File baseFile = new File(fileName);
        int index = 0;
        try (Scanner scan = new Scanner(baseFile)) {
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                base[index / bound][index % bound] = scan.nextInt();
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readBase(InputStream stream) {
        base = new int [bound][bound];
        int index = 0;
        try (Scanner scan = new Scanner(stream)) {
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                base[index / bound][index % bound] = scan.nextInt();
                index++;
            }
        }
    }
}
