package ch3;

import ch1.Exercise;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineMenu extends Menu {

    public String[] onlineMenuInfo = {
            "�������˵�",
            "��ʼ",
            "����",
    };

    public void startOnlineMenu() {
        Exercise ex = new Exercise();
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu(onlineMenuInfo);
            loadOnlineSettings(ex);
            try {
                int choice = scan.nextInt();
                switch (choice) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        onlineStart(ex);
                        break;
                    case 2:
                        super.viewSetting(ex);
                        break;
                    default:
                        throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                illegalInput();
//                e.printStackTrace();
            }
        }
        //TODO
    }

    public void onlineStart(Exercise ex) {
        System.out.println("���ڿ�ʼ������ϰ�����-1����");
        OnlineCheckAnswer onlineCheckAnswer = new OnlineCheckAnswer(ex);
        onlineCheckAnswer.examine();
        onlineCheckAnswer.check();
        waitInput();
    }

    public void loadOnlineSettings(Exercise ex) {
        super.loadSettings(ex, "online");
    }
}
