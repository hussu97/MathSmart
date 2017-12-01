package com.pythagorithm.mathsmartv2.AppLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by b00061342 on 11/13/2017.
 */
public class AssignmentProgress {
    String studentID;
    String assignmentID;
    Map<String,Object> completedQuestions;
    double assignmentScore;
    int questionsLeft; // minimum questions to complete assignment
    double overallScore; // weight the student reached
    //TODO: add questionsAttempted to logic
    int questionsAttempted;

    public AssignmentProgress() {
    }

    public AssignmentProgress(String studentID, String assignmentID, ArrayList<String> completedQuestions, double assignmentScore, int questionsLeft) {
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.completedQuestions = new HashMap<>();
        if (completedQuestions !=null) {
            HashMap<String, Boolean> compQsValue = new HashMap<>();
            for (int i = 0; i < completedQuestions.size(); i++) {
                compQsValue.put(completedQuestions.get(i), true);
            }
            this.completedQuestions.put("completedQuestions", compQsValue);
        }
        else this.completedQuestions=null;
        this.assignmentScore = assignmentScore;
        this.questionsLeft = questionsLeft;
    }

    public int getQuestionsLeft() {
        return questionsLeft;
    }

    public void setQuestionsLeft(int min) {
        this.questionsLeft = min;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public ArrayList<String> getCompletedQuestions() {
        if (completedQuestions!=null) {
            HashMap<String, Boolean> temp = (HashMap<String, Boolean>) this.completedQuestions.get("completedQuestions");
            ArrayList<String> result = new ArrayList<String>(temp.keySet());
            return result;
        }
        else return new ArrayList<String>();
    }

    public void setCompletedQuestions(ArrayList<String> completedQuestions) {
        HashMap<String, Boolean> compQsValue = new HashMap<>();
        for (int i =0; i< completedQuestions.size();i++){
            compQsValue.put(completedQuestions.get(i),true);
        }
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }
}
