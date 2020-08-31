package ch3;

import ch1.Exercise;
import ch2.CheckAnswer;
import ch2.ExerciseFileOperation;
import ch2.Submission;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OfflineMenu extends Menu {
    Exercise ex;
    public String[] offlineMenuInfo = {
            "�������˵�",
            "������ϰ��",
            "������ϰ��",
            "����",
    };

    public void startOfflineMenu() {
        Exercise ex = new Exercise();
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu(offlineMenuInfo);
            loadOfflineSettings(ex);
            try {
                int choice = scan.nextInt();
                switch (choice) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        generateExerciseFile(ex);
                        break;
                    case 2:
                        judgeAnswer();
                        break;
                    case 3:
                        viewSetting(ex);
                        break;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                illegalInput();
            }

        }

        //TODO
    }

    public void loadOfflineSettings(Exercise ex) {
        super.loadSettings(ex, "offline");
    }

    public void judgeAnswer() {
        try {
            Scanner scan = new Scanner(System.in);
            ExerciseFileOperation operation = new ExerciseFileOperation();
            Submission submission = new Submission();

            System.out.println("��������Ŀ�ļ����ļ���:");
            String exFile = scan.next();
            System.out.println("�������ύ�ļ����ļ���:");
            String inFile = scan.next();

            Exercise ex = operation.readExercise(exFile);
            submission.readSubmission(inFile);
            CheckAnswer check = new CheckAnswer();
            check.check(ex, submission);
            check.printCheck();
            waitInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateExerciseFile(Exercise ex)
    {
        int index = 0;
        ExerciseFileOperation operation = new ExerciseFileOperation();
        Scanner scan = new Scanner(System.in);
        System.out.print("��������Ҫ���ɵ�����(1~10)��");
        int fileNum = scan.nextInt();
        if (fileNum >= 1 && fileNum <= 10)
        {
            while (fileNum-->0)
            {
                String fileName = "exercise_" + exerciseModeEN[ex.getMode()] + "_" + ex.getTotalEquations() +  "_" + index++;
                ex.clear();
                ex.generateEquations();
                try {
                    operation.writeExercise(ex, fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else
            illegalInput();

    }

}
