package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Question {
    private String questionID;
    private String questionStatement;
    private String[] answers;
    private int weight;
    private String topic;

    //constructor
    Question(String qID, String qStatement, String[] a, int w, String topic){
        this.questionID=qID;
        this.questionStatement=qStatement;
        answers=new String[a.length];
        for(int i=0;i<a.length;i++)
            answers[i]=a[i];
        this.weight=w;
        this.topic=topic;
    }

    //getters and setters
    public String getQuestionID() {return questionID;}
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    public String getQuestionStatement() {
        return questionStatement;
    }
    public void setQuestionStatement(String questionStatement) {this.questionStatement = questionStatement;}
    public String[] getAnswers() {
        return answers;
    }
    public void setAnswers(String[] answers) {
        this.answers = answers;
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

}
