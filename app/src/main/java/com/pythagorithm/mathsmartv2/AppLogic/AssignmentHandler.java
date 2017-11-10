package com.pythagorithm.mathsmartv2.AppLogic;

import java.util.ArrayList;

import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;

/**
 * Created by H_Abb on 11/3/2017.
 */

public class AssignmentHandler {
    /*
    CONSTANTS for calculating score and for defining number of difficulties and versions
     */
    private final double WEIGHT_VALUE=0.5;
    private final double TIME_VALUE=0.1;
    private final double CORRECT_ANSWER_VALUE=0.3;
    private final double OVERALL_SCORE_WEIGHT=0.8;
    private final double CURRENT_SCORE_WEIGHT=0.2;
    private final int MAX_WEIGHT=10;
    private final int MAX_VERSION=5;
    //other attributes
    private int min;
    private String studentID;
    private Assignment assignment;
    private double overallScore;
    private double currentScore;
    private double assignmentScore;
    private DatabaseConnector dc;
    private Question currentQuestion;
    private ArrayList<String> completedQuestions;

    /*
    Constructor: Initializes studentID to upload quizScore
     */
    //Starting new Assignment
    AssignmentHandler(Assignment assignment,String studentID,double overallScore){
        this.studentID=studentID;
        this.assignment=assignment;
        this.overallScore=overallScore;
        this.min=assignment.getMinCorrectAnswers();
        this.completedQuestions=new ArrayList<>();
        this.assignmentScore=0;
        dc=new DatabaseConnector();
        start();
    }
    /*
    Continuing an existing assignment
    Extra arguments required: List of completed questions (Only question ID), assignment score, and also the updated minimum number of correct answers
     */
    AssignmentHandler(Assignment assignment,String studentID,double overallScore,ArrayList<String> completedQuestions,double assignmentScore,int min){
        this.studentID=studentID;
        this.assignment=assignment;
        this.completedQuestions=completedQuestions;
        this.assignmentScore=assignmentScore;
        this.overallScore=overallScore;
        this.min=min;
        dc=new DatabaseConnector();
        start();
    }

    //setters and getters
    public Assignment getAssignment() {return assignment;}
    public void setAssignment(Assignment assignment) {this.assignment = assignment;}
    public double getOverallScore() {return overallScore;}
    public void setOverallScore(double overallScore) {this.overallScore = overallScore;}
    public double getCurrentScore() {return currentScore;}
    public void setCurrentScore(double currentScore) {this.currentScore = currentScore;}
    public Question getCurrentQuestion() {return currentQuestion;}
    public void setCurrentQuestion(Question currentQuestion) {this.currentQuestion = currentQuestion;}
    public ArrayList<String> getCompletedQuestions() {return completedQuestions;}
    public void setCompletedQuestions(ArrayList<String> completedQuestions) {this.completedQuestions = completedQuestions;}
    public double getAssignmentScore() {return assignmentScore;}
    public void setAssignmentScore(double assignmentScore) {this.assignmentScore = assignmentScore;}

    //Functions
    /*
    Gets available Questions from database for each difficulty in one topic
     */
    /*
    Private function to return the First question of the database
     */
    private void start(){
        int x=ceil(overallScore);
        currentQuestion=dc.getQuestion(completedQuestions,x,assignment.getAssignmentTopic());
    }
    /*
    Function used to generate current question score and send back new question
     */
    public Question solveQuestion(Question currQ,int time,boolean answer) {
        int i=ceil(overallScore);
        boolean alt=true;
        int scoreMinus=i,scorePlus=i;
        completedQuestions.add(currQ.getQuestionID());
        currentQuestion=null;

        //scores generation
        currentScore = masterFormula(currQ.getWeight(),answer,time);
        assignmentScore+=currentScore;
        overallScore = OVERALL_SCORE_WEIGHT * overallScore + CURRENT_SCORE_WEIGHT * currentScore;
        dc.updateScore(assignment.getAssignmentID(),currQ.getQuestionID(),currentScore,overallScore);

        //checking whether assignment is complete of not
        if(answer)
            --min;
        if(min==0)
            return null;

        //getting appropriate question
        while(currentQuestion==null&&(i>0||i<11)) {
            currentQuestion = dc.getQuestion(completedQuestions, i, assignment.getAssignmentTopic());
            if(alt)
                i=++scorePlus;
            else
                i=--scoreMinus;
            alt=!alt;
        }


        if(currentQuestion==null)
            return null;
        else {
            return currentQuestion;
        }
    }

    public boolean saveAssignment(){
        return dc.saveAssignment(studentID,assignment.getAssignmentID(),completedQuestions,overallScore,assignmentScore);
    }

    //420 BLAZE IT SHIZZZZZZ
    private double masterFormula(int weight, boolean correct,int time){
        if(correct)
            return CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*(1/time);
        else
            return (CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*(1/time))*-1;
    }
    private int ceil(double score){
        return (int)Math.ceil(score);
    }
}
