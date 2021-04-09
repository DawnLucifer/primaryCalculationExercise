package menu;

import addones.ExecuteCommand;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu extends Menu {

    public String[] mainMuneInfo = {
            "�˳�",
            "������ϰ",
            "������ϰ",
            "��Windows������",
    };

    public void printMenu(String[] allMenu) {
        for (int i = 0; i < allMenu.length; i++)
            System.out.println(i + ": " + allMenu[i]);
    }

    public void startMainMenu() {
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        while (running) {
            try {
                printMenu(mainMuneInfo);
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        OnlineMenu onlineMenu = new OnlineMenu();
                        onlineMenu.startOnlineMenu();
                        break;
                    case 2:
                        OfflineMenu offlineMenu = new OfflineMenu();
                        offlineMenu.startOfflineMenu();
                        break;
                    case 3:
                        startWindowsCMD();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        throw new InputMismatchException();
                        //TODO what it is after throw an exception
                }
            } catch (InputMismatchException e) {
                illegalInput();
            }
        }
    }

    public void startWindowsCMD() {
        System.out.println("������CMDָ�");
        Scanner scan = new Scanner(System.in);
        String cmd = scan.nextLine();
        ExecuteCommand.execCMD(cmd);
        waitInput();
        //TODO
    }

}
