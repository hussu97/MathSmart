package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */
public abstract class User {
    protected String userID;
    protected DatabaseConnector dc;
    User(){
        this("null");
    }
    User(String userID){
        this.userID=userID;
        dc=new DatabaseConnector(userID);
    }
    public boolean login(){
            return dc.login(userID);
    }

}
