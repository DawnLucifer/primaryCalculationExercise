package ch1;

import java.util.Random;

public class AddEquation extends Equation {
    public AddEquation(EquationBase equationBase) {
        super(equationBase);
        this.setOp('+');
        Random r = new Random();
        do {
            setLeft(r.nextInt(bound));
            setRight(r.nextInt(bound));
            this.setResult(equationBase.getValue(getLeft(), getRight()));
        } while (getResult() == 0);
    }

    public AddEquation(String s) {
        this.setOp('+');
        int index = s.indexOf(this.getOp());
        int length = s.length();
        this.setLeft(Integer.parseInt(s.substring(0, index)));
        this.setRight(Integer.parseInt(s.substring(index + 1, length)));
        this.setResult(getLeft() + getRight());
    }
}
