package com.pythagorithm.mathsmartv2.UIConnector;

import com.pythagorithm.mathsmartv2.AppLogic.AssignmentHandler;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.UILayer.LoginActivity;
import com.pythagorithm.mathsmartv2.UILayer.addAssignment;
import com.pythagorithm.mathsmartv2.UILayer.addQuestion;

/**
 * Created by H_Abb on 11/6/2017.
 */

public  class  UIConnector  {
    private Teacher teacher;
    private Student student;
    AssignmentHandler aH;
    private LoginActivity la;
    private static addAssignment aa;
    private static addQuestion aq;
    public UIConnector(LoginActivity la, String uID, boolean teacher){
        this.la = la;
        if (teacher){
            loginTeacher(uID);
        }
        else {
            loginStudent(uID);
        }
    }
    public UIConnector(addAssignment aa){this.aa=aa;}
    public UIConnector(addQuestion aq){this.aq=aq;}
    public static void addedQuestion(){aq.addedQuestion();}
    public static void addedAssignment(){aa.addedAssignment();}

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

