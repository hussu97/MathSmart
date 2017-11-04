package com.pythagorithm.mathsmartv2;

import java.util.ArrayList;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class DatabaseConnector {
    private String ID;
    DatabaseConnector(String userID){
        this.ID=userID;
    }
    DatabaseConnector(){}
    public boolean login(String userID){
        return true;
    }

    public Question getQuestion(int weight, String topic){
        String[] s=new String[4];
        return new Question("s","s",s,4,"s");
    }

    public Question getQuestion(ArrayList<String> completedQuestion,int weight, String topic){
        String[] s=new String[4];
        return new Question("s","s",s,4,"s");
    }

    public Question getQuestion(String qID){
        String[] s=new String[4];
        return new Question("s","s",s,4,"s");
    }

    public void setQuestion(String qID,Question q){

    }
    public ArrayList<Assignment> getAvailableAssignments(String sectionID){
        return new ArrayList<>();
    }

    public void setAvailableAssignments(String sectionID,ArrayList<Assignment> assignmentList){

    }
}
