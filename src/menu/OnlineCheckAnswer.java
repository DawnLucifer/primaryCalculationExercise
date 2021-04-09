package menu;

import equation.EquationBase;
import equation.Exercise;
import check.CheckAnswer;
import check.Submission;

import java.lang.reflect.Constructor;
import java.util.Scanner;

public class OnlineCheckAnswer {
    String baseName;
    EquationBase equationBase;
    Exercise ex;
    Submission s;
    CheckAnswer check;

    private String[] toBaseClass = {
            "MixBase",
            "AddBase",
            "SubBase",
    };

    public OnlineCheckAnswer(Exercise ex) {
        this.ex = ex;
        this.s = new Submission();
    }

    public void examine() {
        try {
            //reflect
            Class baseClass = Class.forName("ch1." + toBaseClass[ex.getMode()]);
            Constructor constructor = baseClass.getConstructor();
            this.equationBase = (EquationBase) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.baseName = ex.getMode() + ".txt";
        equationBase.readBase("database\\" + baseName);
        ex.generateEquations(equationBase);
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < ex.size(); i++) {
            System.out.println((i + 1) + ": " + ex.get(i).toString() + "=");
            int answer = scan.nextInt();
            if (answer == -1)
                break;
            s.add(answer);
        }
    }

    public void check() {
        check = new CheckAnswer(ex.size());
        check.check(ex, s);
        check.printCheck();
    }
}
