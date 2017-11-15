package com.pythagorithm.mathsmartv2.AppLogic;

import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Student extends User {
    private String sectionID;
    private String studentID;
    private ArrayList<Assignment> assignmentList;
    private ArrayList<String>currAssignmentQuestions;
    private int totalQuestionsSolved;
    private double currAssignmentScore;
    private double overallScore;
    private int min;
    private DatabaseConnector dc;
    private AssignmentHandler aH;

    //Constructor
    Student(String username){
        super(username);
        this.studentID=super.userID;
        this.dc=super.dc;
        this.sectionID=dc.getSectionID(studentID);
        this.overallScore=dc.getOverallScore(studentID);
        this.assignmentList=dc.getAvailableAssignments(sectionID);
        this.totalQuestionsSolved=dc.getTotalQuestionsSolved(studentID);
    }

    //getters and setters
    public String getSectionID() {return sectionID;}
    public void setSectionID(String sectionID) {this.sectionID = sectionID;}
    public String getStudentID() {return studentID;}
    public void setStudentID(String studentID) {this.studentID = studentID;}
    public ArrayList<Assignment> getAssignmentList() {return assignmentList;}
    public void setAssignmentList(ArrayList<Assignment> assignmentList) {this.assignmentList = assignmentList;}
    public ArrayList<String> getCurrAssignmentQuestions() {return currAssignmentQuestions;}
    public void setCurrAssignmentQuestions(ArrayList<String> currAssignmentQuestions) {this.currAssignmentQuestions = currAssignmentQuestions;}
    public double getCurrAssignmentScore() {return currAssignmentScore;}
    public void setCurrAssignmentScore(double currAssignmentScore) {this.currAssignmentScore = currAssignmentScore;}
    public double getOverallScore() {return overallScore;}
    public void setOverallScore(double overallScore) {this.overallScore = overallScore;}
    public int getMin() {return min;}
    public void setMin(int min) {this.min = min;}
    public AssignmentHandler getaH() {return aH;}
    public void setaH(AssignmentHandler aH) {this.aH = aH;}


    public Question startAssignment(int assignmentNum){
        if(assignmentNum<assignmentList.size()) {
            dc.getAssignmentProgress(assignmentList.get(assignmentNum).getAssignmentID(),studentID,currAssignmentQuestions,currAssignmentScore,min);
            if(currAssignmentQuestions==null) {
                aH = new AssignmentHandler(assignmentList.get(assignmentNum), studentID,overallScore,totalQuestionsSolved);
            }
            else{
                aH=new AssignmentHandler(assignmentList.get(assignmentNum),studentID,overallScore,currAssignmentQuestions,currAssignmentScore,min,totalQuestionsSolved);
            }
            return aH.getCurrentQuestion();
        }
        else
            return null;
    }
//    public boolean saveAssignment(){
//        return aH.saveAssignment();
//    }
//    public Question getNextQuestion(Question currQ,int time,boolean answer){
//        return aH.solveQuestion(currQ,time, answer);
//    }
    public double getAssignmentScore(int assignmentNum){
        return dc.getAssignmentScore(assignmentList.get(assignmentNum).getAssignmentID(),studentID);
    }
    public double getOverallScore(String studentID){
        return dc.getOverallScore(studentID);
    }
}
