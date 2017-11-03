package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */
public class User {
    private String username;
    protected String userID;
    protected DatabaseConnector dc;
    User(){
        this("null","null");
    }
    User(String username,String userID){
        this.username=username;
        this.userID=userID;
        dc=new DatabaseConnector(userID);
    }
    public boolean login(){
            return dc.login(userID);
    }

}
