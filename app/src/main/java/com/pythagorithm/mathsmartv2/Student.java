package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Student extends User {
    private String sectionID;
    private ArrayList<Assignment> assignmentList;
    private DatabaseConnector dc;
    private AssignmentHandler aH;
    Student(){
        this.sectionID=super.userID;
        this.dc=super.dc;
        assignmentList=dc.getAvailableAssignments(sectionID);
    }

    public void continueAssignment(int assignmentNum,int questionNumber){
        if(assignmentNum<assignmentList.size()) {
            aH = new AssignmentHandler(assignmentList.get(assignmentNum));
            aH.init();
        }
    }
}
