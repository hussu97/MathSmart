package com.pythagorithm.mathsmartv2.AppLogic;

import java.util.HashMap;

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
    private HashMap<String, Boolean> sectionList;

    public Assignment() {
    }

    //constructor
    public Assignment(String assignmentName, String assignmentTopic, int minCorrectAnswers, String dueDate, String submissionPeriod, HashMap<String, Boolean> sectionList) {

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
    public HashMap<String, Boolean> getSectionList() {return sectionList;}
    public void setSectionList(HashMap<String, Boolean> sectionList) {this.sectionList = sectionList;}
}
