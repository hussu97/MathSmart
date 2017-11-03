package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Student extends User {
    private String studentID;
    private Assignment[] assignmentList;
    private DatabaseConnector dc;
    private AssignmentHandler aH;
    Student(){
        this.studentID=super.userID;
        this.dc=super.dc;
    }
    public void continueAssignment(int assignmentNum,int questionNumber){
        if(assignmentNum<assignmentList.length) {
            aH = new AssignmentHandler(assignmentList[assignmentNum]);
            aH.start();
        }
    }
}
