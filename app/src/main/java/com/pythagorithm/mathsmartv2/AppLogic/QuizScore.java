package com.pythagorithm.mathsmartv2.AppLogic;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class QuizScore {
    private String questionID;
    private String assignmentID;

    private int weight;
    private String topic;
    private int time;
    private boolean correctAnswer;

    //constructor
    QuizScore(String qID,String aID,int w,String topic,int time,boolean answer){
        this.questionID=qID;
        this.assignmentID=aID;
        this.weight=w;
        this.time=time;
        this.topic=topic;
        this.correctAnswer=answer;
    }

    public String getQuestionID() {return questionID;}
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    public String getAssignmentID() {
        return assignmentID;
    }
    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public boolean isCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
