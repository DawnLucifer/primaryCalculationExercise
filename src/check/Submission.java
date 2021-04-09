package check;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Submission implements Iterable<Integer>{
    private final ArrayList<Integer> submission;
    Iterator<Integer> iterator;

    //ΪSubmission�Ĺ���������չ�ؼ�
    public Submission() {
        submission = new ArrayList<>();
        iterator = submission.iterator();
    }

    public Submission(ArrayList<Integer> submission) {
        this.submission = submission;
        iterator = submission.iterator();
    }

    public int next() {
        return iterator.next();
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void clear() {
        submission.clear();
    }

    public void reset() {
        iterator = submission.iterator();
    }

    public boolean add(int a) {
        return submission.add(a);
    }

    public void scanSubmission(int count) {
        Scanner scan = new Scanner(System.in);
        submission.clear();
        System.out.println("�밴����Ŀ�����������𰸺�س�");
        for (int i = 0; i < count; i++) {
            System.out.println("(" + (i + 1) + ")");
            submission.add(scan.nextInt());
        }
        scan.close();
    }

    public void readSubmission(String fileName) throws IOException {
        File ex = new File(fileName);
        String a;
        this.clear();
        try (Scanner scan = new Scanner(ex)) {
            scan.useDelimiter(",");
            while (scan.hasNext()) {
                a = scan.next().replaceAll("[^\\d\\+\\-]", "");
                this.add(Integer.parseInt(a));
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public void writeSubmission(String fileName) throws IOException{
        File ex = new File(fileName);
        try (Writer out = new FileWriter(ex, false)) {
            //public FileWriter(String fileName,boolean append)
            //throws IOException
            //append : true ����д�룬false������д��
            //��������ڼ䣬��append��Ϊfalse��ÿ�ζ����´����ļ�
            for (Integer i : submission) {
                out.write(i + ",");
                //ˢ�»�����
                out.flush();
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return submission.iterator();
    }
}
