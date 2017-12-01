package com.pythagorithm.mathsmartv2.UIConnector;

import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.Question;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.AppLogic.User;

/**
 * Created by H_Abb on 11/6/2017.
 */

public class UIConnector {
    private Teacher teacher;
    private Student student;
    private User user;
    UIConnector(){
        teacher=new Teacher("hi");
    }
    public static void addedQuestion(Question q){}
    public static void addedAssignment(Assignment a){}
}

