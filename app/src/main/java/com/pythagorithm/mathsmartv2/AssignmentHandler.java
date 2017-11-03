package com.pythagorithm.mathsmartv2;

import android.provider.ContactsContract;

/**
 * Created by H_Abb on 11/3/2017.
 */

public class AssignmentHandler {
    private Assignment assignment;
    private boolean progress[][];
    private double overallScore;
    private double currentScore;
    private DatabaseConnector dc;

    AssignmentHandler(Assignment assignment){
        this.assignment=assignment;
        progress=new boolean[10][5];
        dc=new DatabaseConnector(assignment.getStudentID());
    }
    public Assignment getAssignment() {return assignment;}

    public void setAssignment(Assignment assignment) {this.assignment = assignment;}

    public boolean[][] getProgress() {return progress;}

    public void setProgress(boolean[][] progress) {this.progress = progress;}

    public double getOverallScore() {return overallScore;}

    public void setOverallScore(double overallScore) {this.overallScore = overallScore;}

    public double getCurrentScore() {return currentScore;}

    public void setCurrentScore(double currentScore) {this.currentScore = currentScore;}

    public void start(){
        int score =(int)Math.ceil(overallScore);
        int scorePlus=score;
        int scoreMinus=score;
        boolean alt=true;
        while(true){
            while(dc.getQuestion(score,assignment.getTopic())!=null){
                if(alt)
                    score=++scorePlus;
                else
                    score=--scoreMinus;
                alt=!alt;
            }
        }

    }
}
