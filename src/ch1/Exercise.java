package ch1;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;


public class Exercise implements Iterable<Equation> {
    //默认情况下五十道混合运算题
    private int totalEquations = 50;
    public ArrayList<Equation> equations;
    private int mode = 0;
    protected EquationBase equationBase;
    private String baseName;
    private String[] toBaseClass = {
            "MixBase",
            "AddBase",
            "SubBase",
    };

    public int getTotalEquations() {
        return totalEquations;
    }

    public void setTotalEquations(int totalEquations) {
        this.totalEquations = totalEquations;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public Exercise() {
        equations = new ArrayList<>();
    }

    public Exercise(int totalEquations) {
        this();
        if (totalEquations > 0 && totalEquations <= 500)
            this.totalEquations = totalEquations;
    }

    public Exercise(int totalEquations, int mode) {
        this(totalEquations);
        if (mode >= 0 && mode <= 2)
            this.mode = mode;
    }

    public void add(Equation e) {
        if (!contains(e))
            equations.add(e);
    }

    public Equation get(int i) {
        return equations.get(i);
    }

    public boolean contains(Equation e) {
        return equations.contains(e);
    }

    public int size() {
        return equations.size();
    }

    public void clear() {
        equations.clear();
    }

    //用Equation生成Exercise
    //一般情况下不使用
    public void generateEquations() {
        try {
            //reflect
            Class baseClass = Class.forName("ch1." + toBaseClass[mode]);
            Constructor constructor = baseClass.getConstructor();
            equationBase = (EquationBase) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.baseName = mode + ".txt";
        equationBase.readBase(this.getClass().getResourceAsStream("/" + baseName));
        generateEquations(equationBase);
//        if (mode == 0) {
//            equationBase = new MixBase();
//            equationBase.generateBase();
//            while (size() < totalEquations)
//                equations.add(new MixEquation(equationBase));
//        } else if (mode == 1) {
//            equationBase = new AddBase();
//            equationBase.generateBase();
//            while (size() < totalEquations)
//                equations.add(new AddEquation(equationBase));
//        } else if (mode == 2)
//            equationBase = new SubBase();
//        equationBase.generateBase();
//        while (size() < totalEquations)
//            equations.add(new SubEquation(equationBase));
    }



    //接收外来的EquationBase
    public void generateEquations(EquationBase equationBase) {
        //借鉴策略模式的思想
        this.equationBase = equationBase;
        UserEquation user = new UserEquation(equationBase);
        while (size() < totalEquations)
            equations.add(user.executeEquation());
    }

    //匿名类
    public Iterator<Equation> iterator() {
        return new Iterator<Equation>() {

            Iterator<Equation> iterator = equations.iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Equation next() {
                return iterator.next();
            }
        };
    }

}