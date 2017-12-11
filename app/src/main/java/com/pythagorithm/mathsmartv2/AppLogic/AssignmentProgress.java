package com.pythagorithm.mathsmartv2.AppLogic;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by b00061342 on 11/13/2017.
 */
public class AssignmentProgress implements Parcelable {
    String studentID;
    String assignmentID;
    HashMap<String,Boolean> completedQuestions;
    double assignmentScore;
    int questionsLeft; // minimum questions to complete assignment
    double overallScore; // weight the student reached
    //TODO: add questionsAttempted to logic
    int questionsAttempted;

    public AssignmentProgress() {
    }

    public AssignmentProgress(String studentID, String assignmentID, HashMap<String,Boolean> completedQuestions, double assignmentScore, int questionsLeft, int questionsAttempted) {
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.completedQuestions = completedQuestions;
//        if (completedQuestions !=null) {
//            HashMap<String, Boolean> compQsValue = new HashMap<>();
//            for (int i = 0; i < completedQuestions.size(); i++) {
//                compQsValue.put(completedQuestions.get(i), true);
//            }
//            this.completedQuestions.put("completedQuestions", compQsValue);
//        }
//        else this.completedQuestions=null;
        this.assignmentScore = assignmentScore;
        this.questionsLeft = questionsLeft;
        this.questionsAttempted = questionsAttempted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentID);
        parcel.writeString(assignmentID);
        Bundle comp = new Bundle();
        comp.putSerializable("comp",completedQuestions);
        parcel.writeBundle(comp);
        parcel.writeDouble(assignmentScore);
        parcel.writeInt(questionsLeft);
        parcel.writeDouble(overallScore);
        parcel.writeInt(questionsAttempted);
    }

    public AssignmentProgress(Parcel in){
        studentID = in.readString();
        assignmentID = in.readString();
        Bundle comp = in.readBundle();
        completedQuestions = (HashMap<String, Boolean>)comp.getSerializable("comp");
        assignmentScore = in.readDouble();
        questionsLeft = in.readInt();
        overallScore = in.readDouble();
        questionsAttempted = in.readInt();
    }

    // This is to de-serialize the object
    public static final Parcelable.Creator<AssignmentProgress> CREATOR = new Parcelable.Creator<AssignmentProgress>(){
        public AssignmentProgress createFromParcel(Parcel in) {
            return new AssignmentProgress(in);
        }
        public AssignmentProgress[] newArray(int size) {
            return new AssignmentProgress[size];
        }
    };

    public int getQuestionsAttempted() {
        return questionsAttempted;
    }

    public void setQuestionsAttempted(int questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
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

    public HashMap<String, Boolean> getCompletedQuestions() {
        return completedQuestions;
    }

    public void setCompletedQuestions(HashMap<String, Boolean> completedQuestions) {
        this.completedQuestions = completedQuestions;
    }

    //    public ArrayList<String> getCompletedQuestions() {
//        if (completedQuestions!=null) {
//            ArrayList<String> result = new ArrayList<String>(completedQuestions.keySet());
//            return result;
//        }
//        else return new ArrayList<String>();
//    }
//    public void setCompletedQuestions(ArrayList<String> completedQuestions) {
//        HashMap<String, Boolean> compQsValue = new HashMap<>();
//        for (int i =0; i< completedQuestions.size();i++){
//            compQsValue.put(completedQuestions.get(i),true);
//        }
//    }
    public double getAssignmentScore() {
        return assignmentScore;
    }
    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }
}
