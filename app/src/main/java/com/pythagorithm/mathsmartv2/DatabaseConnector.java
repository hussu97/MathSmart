package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class DatabaseConnector {
    private String ID;
    DatabaseConnector(String userID){
        this.ID=userID;
    }
    DatabaseConnector(){}
    public boolean login(String userID){
        return true;
    }
    public ArrayList<Question> getAvailableQuestions(String topic,String sectionID){
        return new ArrayList<>();
    }
    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================
    public Question getQuestion(int weight, String topic){
        String[] s=new String[4];
        return new Question("s",s,"s",4,"s");
    }

    public Question getQuestion(ArrayList<String> completedQuestion,int weight, String topic){
        String[] s=new String[4];
        return new Question("s",s,"s",4,"s");
    }

    public Question getQuestion(String qID){
        String[] s=new String[4];
        return new Question("s",s,"s",4,"s");
    }

    public String addQuestion(Question q){
        return "null";
    }
    public void updateQuestion(String qID,Question q){

    }
    //=========================================================================================================================
    //ASSIGNMENTS
    //=========================================================================================================================
    public ArrayList<Assignment> getAvailableAssignments(String sectionID){
        return new ArrayList<>();
    }

    public String addAssignment(String sectionList[],ArrayList<Assignment> assignmentList){
        return "JI";
    }
    public void updateScore(String aID,String qID,double questionScore,double overallScore){
    }
    public boolean saveAssignment(String aID,ArrayList<String> completedQuestions,double assignmentScore,double overallScore){
        return true;
    }
    //=========================================================================================================================
    //SCORES
    //=========================================================================================================================
    public double getAssignmentScore(String aID,String studentID){
        return 0;
    }
    public double getOverallScore(String studentID){
        return 0;
    }
}
