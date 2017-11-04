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
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    public boolean isFilled() {
        return isFilled;
    }
    public void setFilled(boolean filled) {
        isFilled = filled;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

}
