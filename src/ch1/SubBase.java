package ch1;

public class SubBase extends EquationBase {
    @Override
    public void generateBase() {
        base = new int[bound][bound];
        for (int i = 0; i < bound; i++)
            for (int j = 0; j < bound; j++)
                if (i - j > 0)
                    base[i][j] = i - j;
    }
}
