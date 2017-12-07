package com.pythagorithm.mathsmartv2.AppLogic;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by akeir on 12/7/2017.
 */

public class AssignmentReport implements Parcelable {
    String studentID;
    HashMap<String, Boolean> completedQuestions;
    String assignmentID;

    String topic;
    double assignmentScore;
    int totalQuestionsAttempted;

    public AssignmentReport() {
    }

    @Override
    public int describeContents() {
        return 0;
    }



    // This is to de-serialize the object
    public static final Parcelable.Creator<AssignmentReport> CREATOR = new Parcelable.Creator<AssignmentReport>(){
        public AssignmentReport createFromParcel(Parcel in) {
            return new AssignmentReport(in);
        }
        public AssignmentReport[] newArray(int size) {
            return new AssignmentReport[size];
        }
    };


    public AssignmentReport(Parcel in){
        studentID= in.readString();
        Bundle completed = in.readBundle();
        completedQuestions = (HashMap<String, Boolean>)completed.getSerializable("completedQuestions");
        topic = in.readString();
        assignmentScore = in.readDouble();
        totalQuestionsAttempted = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentID);
        Bundle completed = new Bundle();
        completed.putSerializable("completedQuestions", completedQuestions);
        parcel.writeBundle(completed);
        parcel.writeString(topic);
        parcel.writeDouble(assignmentScore);
        parcel.writeInt(totalQuestionsAttempted);
    }

    public AssignmentReport(String studentID, HashMap<String, Boolean> completedQuestions, String assignmentID, String topic, double assignmentScore, int totalQuestionsAttempted) {

        this.studentID = studentID;
        this.completedQuestions = completedQuestions;
        this.assignmentID = assignmentID;
        this.topic = topic;
        this.assignmentScore = assignmentScore;
        this.totalQuestionsAttempted = totalQuestionsAttempted;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public HashMap<String, Boolean> getCompletedQuestions() {
        return completedQuestions;
    }

    public void setCompletedQuestions(HashMap<String, Boolean> completedQuestions) {
        this.completedQuestions = completedQuestions;
    }

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public int getTotalQuestionsAttempted() {
        return totalQuestionsAttempted;
    }

    public void setTotalQuestionsAttempted(int totalQuestionsAttempted) {
        this.totalQuestionsAttempted = totalQuestionsAttempted;
    }
}
