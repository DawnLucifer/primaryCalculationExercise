package ch1;

public abstract class Equation {
    final protected int bound = 100;
    private int left;
    private int right;
    private int result;
    private char op;
    protected EquationBase equationBase;

    public Equation() {

    }

    public Equation(EquationBase equationBase) {
        this.equationBase = equationBase;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equation other = (Equation) obj;
        if (left != other.left)
            return false;
        if (op != other.op)
            return false;
        if (right != other.right)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "" + left + op + right;
    }
}
