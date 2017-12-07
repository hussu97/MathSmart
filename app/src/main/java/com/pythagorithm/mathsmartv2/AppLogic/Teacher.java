package com.pythagorithm.mathsmartv2.AppLogic;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.UILayer.Sections;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by H_Abb on 11/4/2017.
 */

public class Teacher  implements Parcelable{
    private String teacherID;
    private HashMap<String, Boolean> sectionList;
    private HashMap<String, Assignment> availableAssignments;
    private DatabaseConnector dc;
    private String sectionID;
    private Sections s;

    //Constructor

    public Teacher(){}

    public Teacher(String teacherID, HashMap<String, Boolean> sectionList) {
        this.teacherID = teacherID;
        this.sectionList = sectionList;

    }

    public Teacher(Parcel in){
        Log.d("Hussu","Teacher in parcel");
        teacherID = in.readString();
        Bundle sectionsBundle = in.readBundle();
        sectionList = (HashMap<String, Boolean>)sectionsBundle.getSerializable("map");
        dc = new DatabaseConnector();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d("Hussu","Teacher writeToParcel");
        parcel.writeString(teacherID);
        Bundle sectionsBundle = new Bundle();
        sectionsBundle.putSerializable("map", sectionList);
        parcel.writeBundle(sectionsBundle);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    // This is to de-serialize the object
    public static final Parcelable.Creator<Teacher> CREATOR = new Parcelable.Creator<Teacher>(){
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    //Getters and setters
    public String getTeacherID() {return teacherID;}
    public void setTeacherID(String teacherID) {this.teacherID = teacherID;}
    public HashMap<String, Boolean> getSectionList() {return sectionList;}
    public void setSectionList(HashMap<String, Boolean> sectionList) {this.sectionList = sectionList;}
    public String getSectionID() {return sectionID;}
    public void setSectionID(String sectionID) {this.sectionID = sectionID;}
    public void setSections(Sections s){this.s = s;}
    public ArrayList<Assignment> getAvailableAssignments() {
        if (availableAssignments!=null)
        return new ArrayList<>(availableAssignments.values());
        else return null;
    }

    public void setAvailableAssignmentsFrom(ArrayList<Assignment> availableAssignments) {
        HashMap<String, Assignment> aa = new HashMap<>();
        for (int i =0; i< availableAssignments.size(); i++){
            aa.put(availableAssignments.get(i).getAssignmentID(),availableAssignments.get(i));
        }
        this.availableAssignments = aa;
         s.displaySectionAssingments(availableAssignments);
    }

    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================

    public void createQuestion(String qStatement, String correctAnswer,String wrongAnswer1,String wrongAnswer2,String wrongAnswer3, String topic, int weight){
        Log.d("Hussu","Calling create Question");
        Question q=new Question(topic,qStatement,correctAnswer, wrongAnswer1,wrongAnswer2,wrongAnswer3, weight);
        dc.addQuestion(q);
    }

    //=========================================================================================================================
    //ASSIGNMENTS
    //=========================================================================================================================
    public void getAssignments(String sectionID){
        this.sectionID=sectionID;
        dc.getAvailableAssignments(this,sectionID);
    }
    public void createAssignment(String name, String topic, int numQuestions, String dueDate, String submissionPeriod, HashMap<String, Boolean> sectionList){
        Log.d("Hussu", "Entering create assignment");
        Assignment a= new Assignment(name, topic, numQuestions, dueDate,submissionPeriod, sectionList);
        dc.addAssignment(a);
    }
    public void getTopicScores(){
        dc.getTopicScores(sectionList);
    }
    public void getAssignmentCreated(){
        dc.getAssignmentsCreated(teacherID);
    }
}


