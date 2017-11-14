package com.pythagorithm.mathsmartv2.AppLogic;

import java.util.ArrayList;

/**
 * Created by b00061342 on 11/13/2017.
 */
public class AssignmentProgress {
    String studentID;
    String assignmentID;
    ArrayList<String> completedQuestions;
    double assignmentScore;
    int min;
    double overallScore; // weight the student reached

    public AssignmentProgress() {
    }

    public AssignmentProgress(String studentID, String assignmentID, ArrayList<String> completedQuestions, double assignmentScore, double overallScore, int min) {
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.completedQuestions = completedQuestions;
        this.assignmentScore = assignmentScore;
        this.overallScore = overallScore;
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public ArrayList<String> getCompletedQuestions() {
        return completedQuestions;
    }

    public void setCompletedQuestions(ArrayList<String> completedQuestions) {
        this.completedQuestions = completedQuestions;
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }
}
