package com.pythagorithm.mathsmartv2.AppLogic;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Question {
    private String questionID;
    private String questionStatement;
    private String[] wrongAnswer;
    private String correctAnswer;
    private int weight;
    private String topic;

    //constructor
    public Question(String qStatement, String[] a,String c, int w, String topic){
        this.questionStatement=qStatement;
        wrongAnswer=new String[a.length];
        for(int i=0;i<a.length;i++)
            wrongAnswer[i]=a[i];
        this.weight=w;
        this.correctAnswer=c;
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
    public String[] getWrongAnswer() {return wrongAnswer;}
    public void setWrongAnswer(String[] wrongAnswer) {this.wrongAnswer = wrongAnswer;}
    public String getCorrectAnswer() {return correctAnswer;}
    public void setCorrectAnswer(String correctAnswer) {this.correctAnswer = correctAnswer;}
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
