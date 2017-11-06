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
    private int max;
    private String studentID;
    private Assignment assignment;
    private Progress progress[][];
    private double overallScore;
    private double currentScore;
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
        max=this.assignment.getMinCorrectAnswers();
        progress=new Progress[MAX_WEIGHT][VERSION_WEIGHT];
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

    //Functions
    /*
    Gets available Questions from database for each difficulty in one topic
     */
    private void init(){
        String topic = assignment.getAssignmentTopic();
        Question q;
        for(int i=0;i<MAX_WEIGHT;i++){
            completedQuestions.clear();
            for(int j=0;j<VERSION_WEIGHT;j++){
                q=dc.getQuestion(completedQuestions,i,topic);
                if(q!=null) {
                    completedQuestions.add(q.getQuestionID());
                    progress[i][j].setQuestionID(q.getQuestionID()).setWeight(q.getWeight()).setFilled(true);
                }
                else{
                    j=VERSION_WEIGHT;
                }
            }
        }
        start();
    }
    /*
    Private function to return the First question of the database
     */
    private void start(){
        int score =(int)Math.ceil(overallScore);
        currentQuestion=dc.getQuestion(progress[score][0].getQuestionID());
    }
    public void solveQuestion(String qID,String aID,int w,String topic,int time,boolean answer){
        currentScore+=generateScore(qID,aID,w,topic,time,answer);
    }
    private double generateScore(String qID,String aID,int w,String topic,int time,boolean answer){
        dc.updateScore(new QuizScore(qID, aID, w, topic, time, answer),studentID);
        return masterFormula(w,answer,time);
    }

    //420 BLAZE IT SHIZZZZZZ
    private double masterFormula(int weight, boolean correct,int time){
        if(correct)
            return CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*time;
        else
            return (CORRECT_ANSWER_VALUE+WEIGHT_VALUE*(MAX_WEIGHT-weight)+TIME_VALUE*time)*-1;
    }
}
