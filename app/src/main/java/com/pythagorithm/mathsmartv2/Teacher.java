package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/4/2017.
 */

public class Teacher extends User{
    private String teacherID;
    private String[] sectionList;
    private DatabaseConnector dc;
    private ArrayList<Assignment> availableAssignments;
    private String sectionID;
    private Question question;

    Teacher(){
        this.teacherID=super.userID;
        this.dc=new DatabaseConnector(teacherID);
    }

    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================
    public Question getQuestion(String questionID){
        return dc.getQuestion(questionID);
    }
    public Question createQuestion(String qID, String qStatement, String[] a, int w, String topic){
        return new Question(qID, qStatement, a, w, topic);
    }
    public Question editQuestion(String questionID){
        return getQuestion(questionID);
    }
    public void updateQuestion(String questionID,Question question){
        dc.setQuestion(questionID,question);
    }


    //=========================================================================================================================
    //ASSIGNMENTS
    //=========================================================================================================================
    public ArrayList<Assignment> getAssignments(String sectionID){
        return dc.getAvailableAssignments(sectionID);
    }
    public Assignment createAssignment(String assignmentID,String name, String topic, int numQuestions, String dueDate, String submissionPeriod,String[] sectionID){
        return new Assignment(assignmentID,name, topic, numQuestions, dueDate,submissionPeriod, sectionID);
    }
    public Assignment editAssignment(String sectionID){
        for(Assignment a:availableAssignments)
            for(String secID:a.getSectionList())
                if(secID==sectionID)
                    return a;
        return null;
    }
    public void updateAssignment(Assignment assignment){
        for(Assignment a:availableAssignments) {
            if(a.getAssignmentID()==assignment.getAssignmentID()){
                a=assignment;
            }
        }
    }
    public void uploadAssignment(Assignment assigment){
        dc.setAvailableAssignments(sectionID,availableAssignments);
    }
}
