package chart;

import equation.Exercise;
import check.CheckAnswer;
import check.Submission;

import java.util.Date;

public class ExerciseLog {
    private String date;
    private Exercise exercise;
    private Submission submission;
    private CheckAnswer checkAnswer;

    public ExerciseLog(Exercise exercise, Submission submission) {
        Date dateTime = new Date();
        date = dateTime.toString();
        this.exercise = exercise;
        this.submission = submission;
        checkAnswer = new CheckAnswer(exercise.getTotalEquations());
    }

    public ExerciseLog(String date, Exercise exercise, Submission submission) {
        this.date = date;
        this.exercise = exercise;
        this.submission = submission;
        checkAnswer = new CheckAnswer();
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public CheckAnswer getCheckAnswer() {
        return checkAnswer;
    }

    public void setCheckAnswer(CheckAnswer checkAnswer) {
        this.checkAnswer = checkAnswer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
