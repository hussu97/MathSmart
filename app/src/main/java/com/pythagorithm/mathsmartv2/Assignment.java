package com.pythagorithm.mathsmartv2;

import java.util.Date;

/**
 * Created by b00061342 on 11/4/2017.
 */
public class Assignment {
    String assignmentID, assignmentName, assignmentTopic;
    int minCorrectAnswers;
    Date dueDate;
    int submissionPeriod;
    Section [] sections;

    public Assignment(String assignmentID, String assignmentName, String assignmentTopic, int minCorrectAnswers, Date dueDate, int submissionPeriod, Section[] sections) {
        this.assignmentID = assignmentID;
        this.assignmentName = assignmentName;
        this.assignmentTopic = assignmentTopic;
        this.minCorrectAnswers = minCorrectAnswers;
        this.dueDate = dueDate;
        this.submissionPeriod = submissionPeriod;
        this.sections = sections;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentTopic() {
        return assignmentTopic;
    }

    public void setAssignmentTopic(String assignmentTopic) {
        this.assignmentTopic = assignmentTopic;
    }

    public int getMinCorrectAnswers() {
        return minCorrectAnswers;
    }

    public void setMinCorrectAnswers(int minCorrectAnswers) {
        this.minCorrectAnswers = minCorrectAnswers;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getSubmissionPeriod() {
        return submissionPeriod;
    }

    public void setSubmissionPeriod(int submissionPeriod) {
        this.submissionPeriod = submissionPeriod;
    }

    public Section[] getSections() {
        return sections;
    }

    public void setSections(Section[] sections) {
        this.sections = sections;
    }
}
