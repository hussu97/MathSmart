package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Student extends User {
    private String sectionID;
    private String studentID;
    private ArrayList<Assignment> assignmentList;
    private ArrayList<String>currAssignmentQuestions;
    private double currAssignmentScore;
    private double overallScore;
    private int min;
    private DatabaseConnector dc;
    private AssignmentHandler aH;
    Student(){
        this.studentID=super.userID;
        this.dc=super.dc;
        overallScore=dc.getOverallScore(studentID);
        assignmentList=dc.getAvailableAssignments(sectionID);
    }
    public Question startAssignment(int assignmentNum){
        if(assignmentNum<assignmentList.size()) {
            dc.getAssignmentProgress(assignmentList.get(assignmentNum).getAssignmentID(),studentID,currAssignmentQuestions,currAssignmentScore,min);
            if(currAssignmentQuestions==null) {
                aH = new AssignmentHandler(assignmentList.get(assignmentNum), studentID,overallScore);
            }
            else{
                aH=new AssignmentHandler(assignmentList.get(assignmentNum),studentID,overallScore,currAssignmentQuestions,currAssignmentScore,min);
            }
            return aH.getCurrentQuestion();
        }
        else
            return null;
    }
    public boolean saveAssignment(){
        return aH.saveAssignment();
    }
    public Question getNextQuestion(Question currQ,int time,boolean answer){
        return aH.solveQuestion(currQ,time, answer);
    }
    public double getAssignmentScore(int assignmentNum){
        return dc.getAssignmentScore(assignmentList.get(assignmentNum).getAssignmentID(),studentID);
    }
    public double getOverallScore(String studentID){
        return dc.getOverallScore(studentID);
    }
}
