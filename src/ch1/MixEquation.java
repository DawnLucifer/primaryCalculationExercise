package ch1;

import java.util.Random;

public class MixEquation extends Equation {
    public MixEquation(EquationBase equationBase) {
        super(equationBase);
        Random r = new Random();
        int i, j;
        do {
            i = r.nextInt(bound);
            j = r.nextInt(bound);
            if (i + j < bound) {
                setLeft(i);
                setRight(j);
                setOp('+');
                setResult(equationBase.getValue(getLeft(), getRight()));
            } else if (i + j > bound) {
                setLeft(i);
                setRight(bound - j);
                setOp('-');
                setResult(equationBase.getValue(getLeft(), bound - getRight()));
            }
            //ÏßÐÔ±ä»»
        } while (i + j == bound);
    }
}
