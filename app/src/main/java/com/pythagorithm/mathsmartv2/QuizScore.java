package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class QuizScore {
    private String questionID;
    private String assignmentID;
    private double weight;
    private String topic;
    private int time;
    private boolean correctAnswer;

    //constructor
    QuizScore(String qID,String aID,double w,String topic,int time,boolean answer){
        this.questionID=qID;
        this.assignmentID=aID;
        this.weight=w;
        this.time=time;
        this.topic=topic;
        this.correctAnswer=answer;
    }
}
