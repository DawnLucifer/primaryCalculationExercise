package ch2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import ch1.Equation;
import ch1.Exercise;

public class CheckAnswer {
    private int totalEquations = 50;
    private int rightEquations = 0;
    private int wrongEquations = 0;
    private int completeEquations = 0;
    private final CheckResultEnum[] checkedResult;

    public CheckAnswer() {
        checkedResult = new CheckResultEnum[totalEquations];
    }

    public CheckAnswer(int totalEquations) {
        this.totalEquations = totalEquations;
        checkedResult = new CheckResultEnum[totalEquations];
    }

    public CheckResultEnum[] getCheckedResult() {
        return checkedResult;
    }

    public int getRightEquations() {
        return rightEquations;
    }

    public int getWrongEquations() {
        return wrongEquations;
    }

    public int getCompleteEquations() {
        return completeEquations;
    }

    public void check(Exercise ex, Submission s) {
        s.reset();
        int index = 0;
        for (Equation e : ex)
            if (s.hasNext()) {
                int answer = s.next();
                if (answer == -1) {
                    completeEquations--;
                    checkedResult[index] = CheckResultEnum.UNDO;
                }
                else if (e.getResult() == answer) {
                    this.rightEquations++;
                    checkedResult[index] = CheckResultEnum.RIGHT;
                }
                else {
                    this.wrongEquations++;
                    checkedResult[index] = CheckResultEnum.WRONG;
                }
                index++;
                completeEquations++;
            }
    }

    public void writeCheck(String fileName) {
        File exFile = new File(fileName);
        Writer out = null;
        try {
            out = new FileWriter(exFile, true);
            out.write("Total Equations:" + totalEquations + ";\r\n");
            out.write("Complete Equations:" + completeEquations + ";\r\n");
            out.write("Correct answers:" + rightEquations + ";\r\n");
            out.write("Wrong answers:" + wrongEquations + ";\r\n");
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

    public void printCheck() {
        System.out.println("CHECK ANSWER RESULT");
        System.out.println("Total Equations:" + totalEquations);
        System.out.println("Complete Equations:" + completeEquations);
        System.out.println("Correct answers:" + rightEquations);
        System.out.println("Wrong answers:" + wrongEquations);
    }

}
