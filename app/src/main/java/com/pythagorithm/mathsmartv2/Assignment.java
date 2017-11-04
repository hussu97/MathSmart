package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Assignment {
    private String assignmentID;
    private String assignmentName;
    private String assignmentTopic;
    private int minCorrectAnswers;
    private String dueDate;
    private String submissionPeriod;
    private String[] sectionList;

    //constructor
    public Assignment(String assignmentID, String assignmentName, String assignmentTopic, int minCorrectAnswers, String dueDate, String submissionPeriod, String[] sectionList) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.assignmentTopic = assignmentTopic;
        this.minCorrectAnswers = minCorrectAnswers;
        this.dueDate = dueDate;
        this.submissionPeriod = submissionPeriod;
        this.sectionList = sectionList;
    }

    //setters and getters
    public String getAssignmentID() {return assignmentID;}
    public void setAssignmentID(String assignmentID) {this.assignmentID = assignmentID;}
    public String getAssignmentName() {return assignmentName;}
    public void setAssignmentName(String assignmentName) {this.assignmentName = assignmentName;}
    public String getAssignmentTopic() {return assignmentTopic;}
    public void setAssignmentTopic(String assignmentTopic) {this.assignmentTopic = assignmentTopic;}
    public int getMinCorrectAnswers() {return minCorrectAnswers;}
    public void setMinCorrectAnswers(int minCorrectAnswers) {this.minCorrectAnswers = minCorrectAnswers;}
    public String getDueDate() {return dueDate;}
    public void setDueDate(String dueDate) {this.dueDate = dueDate;}
    public String getSubmissionPeriod() {return submissionPeriod;}
    public void setSubmissionPeriod(String submissionPeriod) {this.submissionPeriod = submissionPeriod;}
    public String[] getSectionList() {return sectionList;}
    public void setSectionList(String[] sectionList) {this.sectionList = sectionList;}
}
