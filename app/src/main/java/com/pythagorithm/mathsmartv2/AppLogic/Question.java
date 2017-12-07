//package com.pythagorithm.mathsmartv2.AppLogic;
//
///**
// * Created by H_Abb on 11/2/2017.
// */
//
//public class Question {
//    private String questionID;
//    private String questionStatement;
//    private String[] wrongAnswer;
//    private String correctAnswer;
//    private int weight;
//    private String topic;
//
//    //constructor
//    public Question(String qStatement, String[] a,String c, int w, String topic){
//        this.questionStatement=qStatement;
//        wrongAnswer=new String[a.length];
//        for(int i=0;i<a.length;i++)
//            wrongAnswer[i]=a[i];
//        this.weight=w;
//        this.correctAnswer=c;
//        this.topic=topic;
//    }
//
//    //getters and setters
//    public String getQuestionID() {return questionID;}
//    public void setQuestionID(String questionID) {
//        this.questionID = questionID;
//    }
//    public String getQuestionStatement() {
//        return questionStatement;
//    }
//    public void setQuestionStatement(String questionStatement) {this.questionStatement = questionStatement;}
//    public String[] getWrongAnswer() {return wrongAnswer;}
//    public void setWrongAnswer(String[] wrongAnswer) {this.wrongAnswer = wrongAnswer;}
//    public String getCorrectAnswer() {return correctAnswer;}
//    public void setCorrectAnswer(String correctAnswer) {this.correctAnswer = correctAnswer;}
//    public int getWeight() {
//        return weight;
//    }
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
//    public String getTopic() {
//        return topic;
//    }
//    public void setTopic(String topic) {
//        this.topic = topic;
//    }
//
//}

package com.pythagorithm.mathsmartv2.AppLogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Question {

    String questionID;
    String questionStatment;
    String correctAnswer, wrongAnswer1,wrongAnswer2,wrongAnswer3, topic;

    int weight;

    public Question() {
    }

    public Question(String topic, String questionStatment, String correctAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, int weight) {
        this.questionID =genRandom();
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2=wrongAnswer2;
        this.wrongAnswer3=wrongAnswer3;
        this.questionStatment = questionStatment;
        this.weight = weight;
        this.topic = topic;
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }
    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }
    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }
    public String getQuestionID() {
        return questionID;
    }
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getQuestionStatment() {
        return questionStatment;
    }
    public void setQuestionStatment(String questionStatment) {this.questionStatment = questionStatment;}
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }
    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int difficulty) {
        this.weight = difficulty;
    }
    public String genRandom(){
        MessageDigest instance = null;
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] messageDigest = instance.digest(String.valueOf(System.nanoTime()).getBytes());
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single
                // byte
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}