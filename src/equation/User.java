package equation;

import check.ExerciseFileOperation;

import java.io.IOException;

public class User {
    final public int ADDMODE = 1;
    final public int SUBMODE = 2;
    final public int MIXMODE = 0;
    final public int EXERCISEMODE = 0;
    final public int ANSWERMODE = 2;
    final public int DEBUGMODE = 1;
    private int totalEquations = 50;
    private int exerciseMode = 0;
    private int columns = 5;

    public int getTotalEquations() {
        return totalEquations;
    }

    public void setTotalEquations(int totalEquations) {
        this.totalEquations = totalEquations;
    }

    public int getExerciseMode() {
        return exerciseMode;
    }

    public void setExerciseMode(int exerciseMode) {
        this.exerciseMode = exerciseMode;
    }

    private int index = 0;
    private Exercise ex;

    //这三个构造方法是独立的，不能彼此间调用
    //因为此处要求在User赋值后进行传参构造Exercise
    public User() {
        ex = new Exercise(totalEquations, exerciseMode);
        ex.generateEquations();
    }

    public User(int totalEquations, int exerciseMode) {
        this.totalEquations = totalEquations;
        this.exerciseMode = exerciseMode;
        ex = new Exercise(totalEquations, exerciseMode);
        ex.generateEquations();
    }

    public User(int totalEquations, int exerciseMode, int columns) {
        this(totalEquations, exerciseMode);
        this.columns = columns;
    }

    public User(Exercise ex) {
        this.ex = ex;
    }

    public User(Exercise ex, int columns) {
        this(ex);
        this.columns = columns;
    }

    public void formatPrintIndex() {
        System.out.printf("(%02d)", index);
    }

    //用来控制打印的间隔
    public void formatPrintSpace(int printMode) {
        if (index % columns == 0)
            System.out.println();
        else {
            if (printMode == DEBUGMODE)
                System.out.print("   \t");
            else
                System.out.print("\t");
        }
    }

    public void formatPrint(int printMode) {
        index = 0;
        for (Equation e : ex) {
            index++;
            formatPrintIndex();
            switch (printMode) {
                case EXERCISEMODE:
                    System.out.print(e + "=");
                    break;
                case DEBUGMODE:
                    System.out.print(e + "=" + e.getResult());
                    break;
                case ANSWERMODE:
                    System.out.print(e.getResult());
                    break;
            }
            formatPrintSpace(printMode);
        }
    }

    public static void main(String[] args) {
//		User student = new User(40,2);
//		ExerciseFileOperation exFile = new ExerciseFileOperation();
//		exFile.writeExercise(student.ex, "sub.txt");
//		student.formatPrint(0);

//		ExerciseFileOperation exFile = new ExerciseFileOperation();
//		Exercise exercise = exFile.readExercise("exercise_add_70_2.txt");
//		User student = new User(exercise, 5);
//		student.formatPrint(student.DEBUGMODE);

//		ExerciseFileOperation exFile = new ExerciseFileOperation();
//		Exercise exercise = exFile.readExercise("exercise_mix_70_3.txt");
//		User student = new User(exercise, 5);
//		student.formatPrint(student.DEBUGMODE);
//		Submission s = new Submission();
//		s.readSubmission("answers_mix_70_3.txt");
//		CheckAnswer check = new CheckAnswer(70);
//		check.check(exercise, s);
//		check.printCheck();

//		EquationBase equationBase = new AddBase();
//		equationBase.generateBase();
//		Exercise exercise = new Exercise(35);
//		exercise.generateEquations(equationBase);
//		User student = new User(exercise);
//		student.formatPrint(student.DEBUGMODE);

//        EquationBase equationBase = new MixBase();
//        equationBase.generateBase();
//        equationBase.writeBase("database\\0.txt");

        ExerciseFileOperation operation = new ExerciseFileOperation();
        Exercise ex = new Exercise();
        ex.generateEquations();
        try {
            operation.writeExerciseStream(ex, "testout.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Exercise exercise;
        try {
            exercise = operation.readExerciseStream("testout.txt");
            for (Equation equation : exercise)
                System.out.println(equation);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.startMainMenu();
    }

}
