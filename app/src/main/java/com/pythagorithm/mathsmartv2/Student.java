package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Student extends User {
    private String sectionID;
    private String studentID;
    private ArrayList<Assignment> assignmentList;
    private DatabaseConnector dc;
    private AssignmentHandler aH;
    Student(){
        this.studentID=super.userID;
        this.dc=super.dc;
        assignmentList=dc.getAvailableAssignments(sectionID);
    }

    public Question startAssignment(int assignmentNum){
        if(assignmentNum<assignmentList.size()) {
            aH = new AssignmentHandler(assignmentList.get(assignmentNum),studentID);
            return aH.getCurrentQuestion();
        }
        else
            return null;
    }
    public Question getNextQuestion(Question q,String aID,int time,boolean answer){
        solveQuestion(q.getQuestionID(), aID, q.getWeight(), q.getTopic(), time, answer);
        return aH.getCurrentQuestion();
    }
    private void solveQuestion(String qID,String aID,int w,String topic,int time,boolean answer){
        aH.solveQuestion(qID,aID,w,topic,time,answer);
    }
}
