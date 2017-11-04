package com.pythagorithm.mathsmartv2;

/**
 * Created by b00061342 on 11/4/2017.
 * This class is temporary for testing purposes
 */
public class Question {
    String id;

    String questionStatment;
    String correctAnswer, wrongAnswer1, difficulty, topic;

    public Question(String topic, String id, String questionStatment, String correctAnswer, String wrongAnswer1, String difficulty) {
        this.topic = topic;
        this.questionStatment = questionStatment;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
