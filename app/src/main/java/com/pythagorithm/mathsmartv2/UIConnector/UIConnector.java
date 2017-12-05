package com.pythagorithm.mathsmartv2.UIConnector;

import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentHandler;
import com.pythagorithm.mathsmartv2.AppLogic.Question;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.UILayer.LoginActivity;

/**
 * Created by H_Abb on 11/6/2017.
 */

public  class  UIConnector  {
    private Teacher teacher;
    private Student student;
    AssignmentHandler aH;
    private LoginActivity la;
    public UIConnector(LoginActivity la, String uID, boolean teacher){
        this.la = la;
        if (teacher){
            loginTeacher(uID);
        }
        else {
            loginStudent(uID);
        }
    }
    public static void addedQuestion(Question q){}
    public static void addedAssignment(Assignment a){}

    private void loginStudent(String uID){
        DatabaseConnector dc = new DatabaseConnector();
        dc.loginStudent(this, uID);
    }

    private void loginTeacher(String uID){
        DatabaseConnector dc = new DatabaseConnector();
        dc.loginTeacher(this, uID);
    }

    public void loginSuccessful(Teacher t){
        la.startSectionsActivity(t);
    }

    public void loginSuccessful( Student s){
        la.startAssignmentsActivity(s);
    }

    public void loginUnsuccessful(){
        la.displayError("Login failed");
    }
}

