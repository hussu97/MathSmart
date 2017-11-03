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
    Question(String qID,String qStatement, String[] a,int w,String topic){
        this.questionID=qID;
        this.questionStatement=qStatement;
        answers=new String[a.length];
        for(int i=0;i<a.length;i++)
            answers[i]=a[i];
        this.weight=w;
        this.topic=topic;
    }
}
