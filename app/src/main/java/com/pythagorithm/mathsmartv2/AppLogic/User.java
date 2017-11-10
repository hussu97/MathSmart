package com.pythagorithm.mathsmartv2.AppLogic;

import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;

/**
 * Created by H_Abb on 11/2/2017.
 */
public abstract class User {
    protected String username;
    protected String userID;
    protected DatabaseConnector dc;
    User(){
        this("null");
    }
    User(String username){
        dc=new DatabaseConnector();
        userID=dc.login(username);
    }

}
