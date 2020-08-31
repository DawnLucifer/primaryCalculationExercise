package ch2;

import java.io.*;
import java.util.Scanner;

import ch1.AddEquation;
import ch1.Equation;
import ch1.Exercise;
import ch1.SubEquation;

public class ExerciseFileOperation {

    public ExerciseFileOperation() {

    }

    public void writeExercise(Exercise ex, String fileName) throws IOException {
        File exFile = new File(fileName);
        Writer out = null;
//public FileWriter(String fileName,boolean append)
//throws IOException
//append : true ����д�룬false������д��
        out = new FileWriter(exFile, true);
        for (Equation e : ex)
            out.write(e.toString() + ",");
        out.close();
    }

    public Exercise readExercise(String fileName) throws IOException {
        File exFile = new File(fileName);
        Exercise ex = new Exercise();
        String equation;

        try (Scanner scan = new Scanner(exFile)) {
            //��","��Ϊ�ָ���
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                //������ʽ�滻������+-�ŵ������ַ�
                equation = scan.next().replaceAll("[^\\d\\+\\-]", "");
                if (equation.contains("+"))
                    ex.add(new AddEquation(equation));
                else
                    ex.add(new SubEquation(equation));
            }
        }
        return ex;
    }

    public void writeExerciseStream(Exercise ex, String fileName) throws IOException {
        File exFile = new File(fileName);
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(exFile)));
        for (Equation e : ex)
            out.writeBytes(e.toString()+ ",");
        out.close();
    }

    public Exercise readExerciseStream(String fileName) throws IOException {
        Exercise ex = new Exercise();
        String equation;
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        Scanner scan = new Scanner(in);
        scan.useDelimiter(",");
        while (scan.hasNext()) {
            //������ʽ�滻������+-�ŵ������ַ�
            equation = scan.next().replaceAll("[^\\d\\+\\-]", "");
            if (equation.contains("+"))
                ex.add(new AddEquation(equation));
            else
                ex.add(new SubEquation(equation));
        }
        return ex;
    }

}
