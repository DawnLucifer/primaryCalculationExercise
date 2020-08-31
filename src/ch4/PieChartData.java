package ch4;

public class PieChartData {
    private final int right;
    private final int wrong;
    private final int complete;

    public PieChartData(int right, int wrong) {
        this.right = right;
        this.wrong = wrong;
        complete = right + wrong;
    }

    public int getRight() {
        return right;
    }

    public int getWrong() {
        return wrong;
    }

    public int getComplete() {
        return complete;
    }

}
