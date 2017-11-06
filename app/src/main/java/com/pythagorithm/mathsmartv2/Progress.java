package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/3/2017.
 */

public class Progress {
    private String questionID;
    private boolean isComplete;
    private boolean isFilled;
    private int weight;

    //Constructor
    Progress(){
        isComplete=false;
        isFilled=false;
    }

    //Setters and getters
    public String getQuestionID() {return questionID;}
    public Progress setQuestionID(String questionID) {
        this.questionID = questionID;
        return this;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public Progress setComplete(boolean complete) {
        isComplete = complete;
        return this;
    }
    public boolean isFilled() {
        return isFilled;
    }
    public Progress setFilled(boolean filled) {
        isFilled = filled;
        return this;
    }
    public int getWeight() {
        return weight;
    }
    public Progress setWeight(int weight) {
        this.weight = weight;
        return this;
    }


}
