package equation;

public class UserEquation {
    private final EquationBase equationBase;

    //����ģʽ
    public UserEquation(EquationBase equationBase) {
        this.equationBase = equationBase;
    }

    public Equation executeEquation() {
        if (equationBase instanceof AddBase)
            return new AddEquation(equationBase);
        else if (equationBase instanceof SubBase)
            return new SubEquation(equationBase);
        else
            return new MixEquation(equationBase);
    }
}
