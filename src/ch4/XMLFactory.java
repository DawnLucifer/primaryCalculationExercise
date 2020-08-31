package ch4;

import ch1.AddEquation;
import ch1.Equation;
import ch1.Exercise;
import ch1.SubEquation;
import ch2.CheckAnswer;
import ch2.ExerciseFileOperation;
import ch2.Submission;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class XMLFactory {
    public static OutputFormat format;
    private static final String fileName = XMLFactory.class.getResource("/ExerciseLog.xml").getPath();

    static {
        format = OutputFormat.createPrettyPrint();
        format.setIndentSize(4);    //行缩进
        format.setNewlines(true); // 一个结点为一行
        format.setNewLineAfterDeclaration(false);//解决声明下空行问题
        format.setEncoding("GBK");
    }

    public static void createXML() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("ExerciseLog");

        Element elementProperty = root.addElement("Property");
        Element elementAllComplete = elementProperty.addElement("AllComplete");
        Element elementAllRight = elementProperty.addElement("AllRight");
        Element elementAllWrong = elementProperty.addElement("AllWrong");
        elementAllComplete.setText("0");
        elementAllRight.setText("0");
        elementAllWrong.setText("0");
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(fileName), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addLog(ExerciseLog log) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(XMLFactory.class.getResourceAsStream("/ExerciseLog.xml"));

            Exercise exercise = log.getExercise();
            Submission submission = log.getSubmission();
            CheckAnswer checkAnswer = log.getCheckAnswer();
            checkAnswer.check(exercise, submission);

            Element root = document.getRootElement();
            Element elementLog = root.addElement("Log");

            Element elementDate = elementLog.addElement("Date");
            elementDate.setText(log.getDate());

            Element elementMode = elementLog.addElement("Mode");
            elementMode.setText(Integer.toString(exercise.getMode()));

            Element elementResult = elementLog.addElement("Result");
            Element elementTotalEquations = elementResult.addElement("TotalEquations");
            elementTotalEquations.setText(Integer.toString(exercise.getTotalEquations()));
            Element elementComplete = elementResult.addElement("Complete");
            elementComplete.setText(Integer.toString(checkAnswer.getCompleteEquations()));
            Element elementRight = elementResult.addElement("Right");
            elementRight.setText(Integer.toString(checkAnswer.getRightEquations()));
            Element elementWrong = elementResult.addElement("Wrong");
            elementWrong.setText(Integer.toString(checkAnswer.getWrongEquations()));


            Element elementExercise = elementLog.addElement("Exercise");
            for (Equation equation : exercise) {
                Element elementEquation = elementExercise.addElement("Equation");
                elementEquation.setText(equation.toString());
            }

            Element elementSubmission = elementLog.addElement("Submission");
            for (Integer integer : submission) {
                Element elementAnswer = elementSubmission.addElement("Answer");
                elementAnswer.setText(integer.toString());
            }

            TotalResult(checkAnswer, root);

            XMLWriter writer = new XMLWriter(new FileOutputStream(fileName), format);
            writer.write(document);
            writer.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public static ExerciseLog[] AllLogs() {
        ArrayList<ExerciseLog> arrayList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(XMLFactory.class.getResourceAsStream("/ExerciseLog.xml"));
            Element root = document.getRootElement();
            Iterator<Element> iteratorLogs = root.elementIterator("Log");
//            iteratorLogs.next();    //第一个结点记录总体信息
            while (iteratorLogs.hasNext()) {
                Element elementLog = iteratorLogs.next();
                Exercise exercise = generateExercise(elementLog);
                exercise.setMode(Integer.parseInt(elementLog.element("Mode").getText()));
                exercise.setTotalEquations(Integer.parseInt(elementLog.element("Result").element("TotalEquations").getText()));
                Submission submission = generateSubmission(elementLog);
                String date = elementLog.elementText("Date");
                arrayList.add(new ExerciseLog(date, exercise, submission));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return arrayList.toArray(new ExerciseLog[0]);
    }

    private static Submission generateSubmission(Element elementLog) {
        Element elementSubmission = elementLog.element("Submission");
        ArrayList<Integer> arrayList = new ArrayList<>();
        Iterator<Element> iteratorSubmission = elementSubmission.elementIterator();
        while (iteratorSubmission.hasNext()) {
            Element elementAnswer = iteratorSubmission.next();
            String answer = elementAnswer.getText();
            arrayList.add(Integer.parseInt(answer));
        }
        return new Submission(arrayList);
    }

    private static Exercise generateExercise(Element elementLog) {
        Element elementExercise = elementLog.element("Exercise");
        Exercise exercise = new Exercise();
        Iterator<Element> iteratorExercise = elementExercise.elementIterator();
        while (iteratorExercise.hasNext()) {
            Element elementEquation = iteratorExercise.next();
            String equation = elementEquation.getText();
            if (equation.contains("+"))
                exercise.add(new AddEquation(equation));
            else
                exercise.add(new SubEquation(equation));
        }
        return exercise;
    }

    private static void TotalResult(CheckAnswer checkAnswer, Element root) {
        Element elementProperty = root.element("Property");
        Element elementAllComplete = elementProperty.element("AllComplete");
        Element elementAllRight = elementProperty.element("AllRight");
        Element elementAllWrong = elementProperty.element("AllWrong");
        int Complete = Integer.parseInt(elementAllComplete.getText());
        int Right = Integer.parseInt(elementAllRight.getText());
        int Wrong = Integer.parseInt(elementAllWrong.getText());
        Complete += checkAnswer.getCompleteEquations();
        Right += checkAnswer.getRightEquations();
        Wrong += checkAnswer.getWrongEquations();
        elementAllComplete.setText(Integer.toString(Complete));
        elementAllRight.setText(Integer.toString(Right));
        elementAllWrong.setText(Integer.toString(Wrong));
    }

    public static BarChartData[] getBarChartData() {
        ArrayList<BarChartData> arrayList = new ArrayList<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(XMLFactory.class.getResourceAsStream("/ExerciseLog.xml"));
            Element root = document.getRootElement();
            Iterator<Element> iteratorLogs = root.elementIterator("Log");
            while (iteratorLogs.hasNext()) {
                Element elementLog = iteratorLogs.next();
                int totalEquations = Integer.parseInt(elementLog.element("Result").element("Complete").getText());
                int mode = Integer.parseInt(elementLog.element("Mode").getText());
                String date = elementLog.element("Date").getText();
                arrayList.add(new BarChartData(totalEquations, date, mode));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        int length = arrayList.size();
        int maxColumns = 5;
        int startIndex = Math.max(length - maxColumns, 0);
        List<BarChartData> list = arrayList.subList(startIndex, length);
        return list.toArray(new BarChartData[0]);
    }

    public static PieChartData getPieChartData() {
        SAXReader reader = new SAXReader();
        int right = 0, wrong = 0;
        try {
            Document document = reader.read(XMLFactory.class.getResourceAsStream("/ExerciseLog.xml"));
            Element root = document.getRootElement();
            Element property = root.element("Property");
            right = Integer.parseInt(property.element("AllRight").getText());
            wrong = Integer.parseInt(property.element("AllWrong").getText());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new PieChartData(right, wrong);
    }

    public static void main(String[] args) throws IOException {
//        createXML();

//        ExerciseFileOperation operation = new ExerciseFileOperation();
//        Exercise exercise = operation.readExerciseStream("exercise.txt");
//        Submission submission = new Submission();
//        submission.readSubmission("Answers.txt");
//        addLog(new ExerciseLog(exercise, submission));

//        ExerciseLog[] exerciseLogs = AllLogs();
//        ExerciseLog exerciseLog = exerciseLogs[0];
//        System.out.println(exerciseLog.get());

//        BarChartData[] barChartData = getBarChartData();
//        for (BarChartData data : barChartData) {
//            System.out.println(data.getDate());
//            System.out.println(data.getMode());
//            System.out.println(data.getTotalEquations());
//        }

        BarChartData[] barChartData = getBarChartData();
        System.out.println(barChartData[0].getFormatDate());
    }
}
