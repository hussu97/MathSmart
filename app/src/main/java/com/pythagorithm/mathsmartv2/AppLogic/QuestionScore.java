package com.pythagorithm.mathsmartv2.AppLogic;

import java.util.Date;

/**
 * Created by b00061342 on 11/14/2017.
 */
public class QuestionScore {
    String questionScoreID;
    String studentID;
    String questionID;
    String assignmentID;
    boolean correct;
    int time;
    Date date;
    String topic;
    int difficulty;

    public QuestionScore() {
    }

    public QuestionScore(String studentID, String questionID, String assignmentID, boolean correct, int time,  String topic, int difficulty) {
        this.questionScoreID = null;
        this.studentID = studentID;
        this.questionID = questionID;
        this.assignmentID = assignmentID;
        this.correct = correct;
        this.time = time;
        this.date = getDate();
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public String getQuestionScoreID() {
        return questionScoreID;
    }

    public void setQuestionScoreID(String questionScoreID) {
        this.questionScoreID = questionScoreID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
