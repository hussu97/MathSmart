package com.pythagorithm.mathsmartv2.UIConnector;

import com.pythagorithm.mathsmartv2.AppLogic.AssignmentHandler;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.UILayer.LoginActivity;
import com.pythagorithm.mathsmartv2.UILayer.addAssignment;
import com.pythagorithm.mathsmartv2.UILayer.addQuestion;
import com.pythagorithm.mathsmartv2.UILayer.reportStudent;
import com.pythagorithm.mathsmartv2.UILayer.reportTeacher;

import java.util.HashMap;

/**
 * Created by H_Abb on 11/6/2017.
 */

public  class  UIConnector  {
    AssignmentHandler aH;
    private LoginActivity la;
    private static addAssignment aa;
    private static addQuestion aq;
    private static reportTeacher rt;
    private static reportStudent rs;
    public UIConnector(LoginActivity la, String uID, boolean teacher){
        this.la = la;
        if (teacher)
            loginTeacher(uID);
        else
            loginStudent(uID);
    }
    public UIConnector(addAssignment aa){this.aa=aa;}
    public UIConnector(reportTeacher rt){this.rt=rt;}
    public UIConnector(reportStudent rs){this.rs=rs;}
    public UIConnector(addQuestion aq){this.aq=aq;}
    public static void addedQuestion(){aq.addedQuestion();}
    public static void addedAssignment(){aa.addedAssignment();}
    public static void showPieChartStudent(HashMap<String,Integer> values){rs.showPieChart(values);}
    public static void showBarChartStudent(HashMap<String,Float> values){rs.showBarChart(values);}
    public static void showPieChartTeacher(HashMap<String,Integer> values){rt.showPieChart(values);}
    public static void showBarChartTeacher(HashMap<Integer,String> values,HashMap<String,Float> values2){rt.showBarChart(values,values2);}

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
    public void loginUnsuccessful(){la.displayError("Login failed");}
}

