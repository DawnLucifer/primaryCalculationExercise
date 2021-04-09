package equation;

public class AddBase extends EquationBase {
    @Override
    public void generateBase() {
        base = new int[bound][bound];
        for (int i = 0; i < bound; i++)
            for (int j = 0; j < bound; j++)
                if (i + j < bound)
                    base[i][j] = i + j;
    }
}
