package com.pythagorithm.mathsmartv2.UIConnector;

import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.Question;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.AppLogic.User;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.UILayer.LoginActivity;

/**
 * Created by H_Abb on 11/6/2017.
 */

public class UIConnector {
    private Teacher teacher;
    private Student student;
    private LoginActivity la;
    private User user;
    public UIConnector(){
        teacher=new Teacher("hi");
    }
    public UIConnector(LoginActivity la, String uID, boolean teacher){
        this.la = la;
        if (teacher){
            loginTeacher(uID);
        }
    }
    public static void addedQuestion(Question q){}
    public static void addedAssignment(Assignment a){}
    public void loginTeacher(String uID){
        DatabaseConnector dc = new DatabaseConnector();
        dc.loginTeacher(this, uID);
    }

    public void loginSuccessful(Teacher t){
        la.startSectionsActivity(t);
    }
    public void loginUnsuccessful(){
        la.displayError("Login failed");
    }
}

