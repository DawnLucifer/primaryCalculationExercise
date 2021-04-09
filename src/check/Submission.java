package check;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Submission implements Iterable<Integer>{
    private final ArrayList<Integer> submission;
    Iterator<Integer> iterator;

    //为Submission的构造留下拓展控件
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
        System.out.println("请按照题目序号依次输入答案后回车");
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
            //append : true 附加写入，false：覆盖写入
            //方便测试期间，把append设为false，每次都重新创建文件
            for (Integer i : submission) {
                out.write(i + ",");
                //刷新缓冲区
                out.flush();
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return submission.iterator();
    }
}
