package ch1;

public class MixBase extends EquationBase {
    @Override
    public void generateBase() {
        base = new int[bound][bound];
        for (int i = 0; i < bound; i++)
            for (int j = 0; j < bound; j++) {
                if (i + j < 100)
                    base[i][j] = i + j;
                else if (i - (100 - j) > 0)
                    base[i][j] = i - (100 - j);
                //ÏßÐÔ±ä»»
            }
    }
}
