package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/3/2017.
 */

public class AssignmentHandler {
    private final double WEIGHT_VALUE=0.5;
    private final double TIME_VALUE=0.1;
    private final double CORRECT_ANSWER_VALUE=0.3;
    private final int MAX_WEIGHT=10;
    private final int VERSION_WEIGHT=5;
    private Assignment assignment;
    private Progress progress[][];
    private double overallScore;
    private double currentScore;
    private DatabaseConnector dc;
    private Question currentQuestion;
    private ArrayList<String> completedQuestions;

    //constructor
    AssignmentHandler(Assignment assignment){
        this.assignment=assignment;
        progress=new Progress[MAX_WEIGHT][VERSION_WEIGHT];
        dc=new DatabaseConnector();
    }

    //setters and getters
    public Assignment getAssignment() {return assignment;}
    public void setAssignment(Assignment assignment) {this.assignment = assignment;}
    public Progress[][] getProgress() {return progress;}
    public void setProgress(Progress[][] progress) {this.progress = progress;}
    public double getOverallScore() {return overallScore;}
    public void setOverallScore(double overallScore) {this.overallScore = overallScore;}
    public double getCurrentScore() {return currentScore;}
    public void setCurrentScore(double currentScore) {this.currentScore = currentScore;}

    public void init(){
        String topic = assignment.getAssignmentTopic();
        Question q;
        for(int i=0;i<MAX_WEIGHT;i++){
            for(int j=0;j<VERSION_WEIGHT;j++){
                q=dc.getQuestion(completedQuestions,i,topic);
                completedQuestions.add(q.getQuestionID());
                progress[i][j].setQuestionID(q.getQuestionID());
                progress[i][j].setWeight(q.getWeight());
            }
        }
        start();
    }
    private void start(){
        int score =(int)Math.ceil(overallScore);
        int max=assignment.getMinCorrectAnswers();
        while (max>0){

            max--;
        }
    }
    private boolean solveQuestion(){
        return true;
    }
    private double masterFormula(int weight, boolean correct,int time){
        if(correct)
            return CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*time;
        else
            return (CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*time)*-1;
    }
}
