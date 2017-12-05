package com.pythagorithm.mathsmartv2.AppLogic;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Assignment implements Parcelable{
    private String assignmentID;

    private String assignmentName;
    private String assignmentTopic;
    private int minCorrectAnswers;
    private String dueDate;
    private String submissionPeriod;
    private HashMap<String, Boolean> sectionList;


    public Assignment() {
    }

    //constructor
    public Assignment(String assignmentName, String assignmentTopic, int minCorrectAnswers, String dueDate, String submissionPeriod, HashMap<String, Boolean> sectionList) {
        this.assignmentID = genRandom();
        this.assignmentName = assignmentName;
        this.assignmentTopic = assignmentTopic;
        this.minCorrectAnswers = minCorrectAnswers;
        this.dueDate = dueDate;
        this.submissionPeriod = submissionPeriod;
        this.sectionList = sectionList;
    }

    public Assignment(Parcel in){
        assignmentID = in.readString();
        assignmentName = in.readString();
        assignmentTopic = in.readString();
        minCorrectAnswers = in.readInt();
        dueDate = in.readString();
        submissionPeriod = in.readString();
        Bundle sectionsBundle = in.readBundle();
        sectionList = (HashMap<String, Boolean>)sectionsBundle.getSerializable("sections");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(assignmentID);
        parcel.writeString(assignmentName);
        parcel.writeString(assignmentTopic);
        parcel.writeInt(minCorrectAnswers);
        parcel.writeString(dueDate);
        parcel.writeString(submissionPeriod);
        Bundle sectionsBundle = new Bundle();
        sectionsBundle.putSerializable("sections",sectionList);
        parcel.writeBundle(sectionsBundle);

    }


    // This is to de-serialize the object
    public static final Parcelable.Creator<Assignment> CREATOR = new Parcelable.Creator<Assignment>(){
        public Assignment createFromParcel(Parcel in) {
            return new Assignment(in);
        }
        public Assignment[] newArray(int size) {
            return new Assignment[size];
        }
    };

    public String genRandom(){
        MessageDigest instance = null;
        try {
            instance = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] messageDigest = instance.digest(String.valueOf(System.nanoTime()).getBytes());
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single
                // byte
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    //setters and getters
    public String getAssignmentID() {return assignmentID;}
    public void setAssignmentID(String assignmentID) {this.assignmentID = assignmentID;}
    public String getAssignmentName() {return assignmentName;}
    public void setAssignmentName(String assignmentName) {this.assignmentName = assignmentName;}
    public String getAssignmentTopic() {return assignmentTopic;}
    public void setAssignmentTopic(String assignmentTopic) {this.assignmentTopic = assignmentTopic;}
    public int getMinCorrectAnswers() {return minCorrectAnswers;}
    public void setMinCorrectAnswers(int minCorrectAnswers) {this.minCorrectAnswers = minCorrectAnswers;}
    public String getDueDate() {return dueDate;}
    public void setDueDate(String dueDate) {this.dueDate = dueDate;}
    public String getSubmissionPeriod() {return submissionPeriod;}
    public void setSubmissionPeriod(String submissionPeriod) {this.submissionPeriod = submissionPeriod;}
    public HashMap<String, Boolean> getSectionList() {return sectionList;}
    public void setSectionList(HashMap<String, Boolean> sectionList) {this.sectionList = sectionList;}
}
