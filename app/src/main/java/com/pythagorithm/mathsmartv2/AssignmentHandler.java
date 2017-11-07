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
    private Progress progress[][];
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
        this.assignmentScore=0;
        max=this.assignment.getMinCorrectAnswers();
        progress=new Progress[MAX_WEIGHT][MAX_VERSION];
        completedQuestions=new ArrayList<>();
        dc=new DatabaseConnector();
        init();
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
        String topic = assignment.getAssignmentTopic();
        Question q;

        for(int i=0;i<MAX_WEIGHT;i++){
            completedQuestions.clear();
            for(int j=0;j<MAX_VERSION;j++){
                q=dc.getQuestion(completedQuestions,i,topic);
                if(q!=null) {
                    completedQuestions.add(q.getQuestionID());
                    progress[i][j].setQuestionID(q.getQuestionID()).setWeight(q.getWeight()).setFilled(true);
                }
                else{
                    j=MAX_VERSION;
                }
            }
        }
        start();
    }
    /*
    Private function to return the First question of the database
     */
    private void start(){
        progress[(int)Math.ceil(overallScore)][0].setComplete(true);
        currentQuestion=dc.getQuestion(progress[(int)Math.ceil(overallScore)][0].getQuestionID());
    }
    /*
    Function used to generate current question score and send back new question
     */
    public void solveQuestion(String qID,String aID,int w,String topic,int time,boolean answer) {
        //scores generation
        currentScore = generateScore(qID, aID, w, topic, time, answer);
        assignmentScore+=currentScore;
        overallScore = OVERALL_SCORE_WEIGHT * overallScore + CURRENT_SCORE_WEIGHT * currentScore;

        int i = (int) Math.ceil(overallScore), j=0;
        //Change difficulty to +-1, +-2, so on if question from same difficulty is not available
        int weightPlus=i; int weightMinus=i;
        boolean foundQuestion=false,alt=true;
        //If question is answered correctly, reduce minnumber of correct questions
        if(answer)max--;
        //If assignment is complete, return null question
        if(max==0) {
            currentQuestion=null;
            return;
        }
        while(!foundQuestion){
            j=0;
            if(i>-1&&i<MAX_WEIGHT) {
                if (progress[i][j].isFilled() && progress[i][j].isComplete() && j < MAX_VERSION) {
                    j++;
                }
            }
            //Appropriate question has been found
            else if(!progress[i][j].isComplete()){
                foundQuestion=true;
            }
            //Changing weight of question to be found
            else if (weightPlus<MAX_WEIGHT&&weightMinus>-1){
                if(alt)
                    i=++weightPlus;
                else
                    i=--weightMinus;
                alt=!alt;
            }
            //Assignment has run out of questions to offer
            else{
                currentQuestion=null;
                return;
            }
        }
        progress[i][j].setComplete(true);
        currentQuestion=dc.getQuestion(progress[i][j].getQuestionID());
    }
    private double generateScore(String qID,String aID,int w,String topic,int time,boolean answer){
        dc.updateScore(new QuizScore(qID, aID, w, topic, time, answer),studentID);
        return masterFormula(w,answer,time);
    }
    //420 BLAZE IT SHIZZZZZZ
    private double masterFormula(int weight, boolean correct,int time){
        if(correct)
            return CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*(1/time);
        else
            return (CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*(1/time))*-1;
    }
}
