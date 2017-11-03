package com.pythagorithm.mathsmartv2;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class Assignment {
    private String name;
    private String topic;
    private int numQuestions;
    private String dueDate;
    private String studentID;
    private DatabaseConnector dc;

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getTopic() {return topic;}

    public void setTopic(String topic) {this.topic = topic;}

    public int getNumQuestions() {return numQuestions;}

    public void setNumQuestions(int numQuestions) {this.numQuestions = numQuestions;}

    public String getDueDate() {return dueDate;}

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}

    public String getStudentID() {return studentID;}

    public void setStudentID(String studentID) {this.studentID = studentID;}

    public DatabaseConnector getDc() {return dc;}

    public void setDc(DatabaseConnector dc) {this.dc = dc;}

    Assignment(String studentID){
        this.studentID=studentID;

    }

}
