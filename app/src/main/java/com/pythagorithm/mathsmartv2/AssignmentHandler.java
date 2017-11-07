package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

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
    private int max;
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
    Progress: Filled with Question ID's and used to check whether a question has been solved or not
    Completed Questions: Array of questions solved by student
     */
    AssignmentHandler(Assignment assignment,String studentID){
        this.studentID=studentID;
        this.assignment=assignment;
        dc=new DatabaseConnector();
        init();
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
    private void init(){
        max=assignment.getMinCorrectAnswers();
        completedQuestions=new ArrayList<>();
        start();
    }
    /*
    Private function to return the First question of the database
     */
    private void start(){
        int x=ceil(overallScore);
        completedQuestions.clear();
        currentQuestion=dc.getQuestion(completedQuestions,x,assignment.getAssignmentTopic());
        completedQuestions.add(currentQuestion.getQuestionID());
    }
    /*
    Function used to generate current question score and send back new question
     */
    public Question solveQuestion(Question currQ,int time,boolean answer) {
        int i=ceil(overallScore);
        boolean alt=true;
        int scoreMinus=i,scorePlus=i;
        currentQuestion=null;

        //scores generation
        currentScore = masterFormula(currQ.getWeight(),answer,time);
        assignmentScore+=currentScore;
        overallScore = OVERALL_SCORE_WEIGHT * overallScore + CURRENT_SCORE_WEIGHT * currentScore;
        dc.updateScore(assignment.getAssignmentID(),currQ.getQuestionID(),currentScore,overallScore);

        if(--max==0)
            return null;

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
            completedQuestions.add(currentQuestion.getQuestionID());
            return currentQuestion;
        }
    }

    public boolean saveAssignment(){
        return dc.saveAssignment(assignment.getAssignmentID(),completedQuestions,overallScore,assignmentScore);
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
