package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class DatabaseConnector {
    private String ID;
    DatabaseConnector(String userID){
        this.ID=userID;
    }
    public boolean login(String userID){
        return true;
    }
    public Question getQuestion(int weight, String topic){
        String[] s=new String[4];
        return new Question("s","s",s,4,"s");
    }
}
