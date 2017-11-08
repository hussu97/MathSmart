package com.pythagorithm.mathsmartv2;

/**
 * Created by b00061342 on 11/4/2017.
 * This class is temporary for testing purposes
 */
public class Question {
    String questionStatment;
    String correctAnswer, wrongAnswer1, topic;

    long difficulty;

    public Question() {
    }

    public Question(String topic, String questionStatment, String correctAnswer, String wrongAnswer1, long difficulty) {

        this.wrongAnswer1 = wrongAnswer1;
        this.questionStatment = questionStatment;
        this.difficulty = difficulty;
        this.topic = topic;
        this.correctAnswer = correctAnswer;
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

    public void setQuestionStatment(String questionStatment) {
        this.questionStatment = questionStatment;
    }

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

    public long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
