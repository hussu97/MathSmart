package com.pythagorithm.mathsmartv2.AppLogic;

import java.util.ArrayList;
import java.util.HashMap;

import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.UILayer.Sections;

/**
 * Created by H_Abb on 11/4/2017.
 */

public class Teacher extends User{
    private String teacherID;
    private HashMap<String, Boolean> sectionList;
    private DatabaseConnector dc;
    private HashMap<String, Assignment> availableAssignments;
    private String sectionID;
    private Question question;
    private Sections s;

    //Constructor
    public Teacher(String username ){
        super(username);
        teacherID=super.userID;
        this.dc=new DatabaseConnector(teacherID);
    }

    public Teacher(){}

    public Teacher(String teacherID, HashMap<String, Boolean> sectionList, DatabaseConnector dc, HashMap<String, Assignment> availableAssignments) {
        this.teacherID = teacherID;
        this.sectionList = sectionList;
        this.dc = dc;
        this.availableAssignments = availableAssignments;
        this.sectionID = "";
        this.question = new Question();
        this.s = new Sections();
    }

    public void setSections(Sections s){this.s = s;}

    //Getters and setters
    public String getTeacherID() {return teacherID;}
    public void setTeacherID(String teacherID) {this.teacherID = teacherID;}
    public HashMap<String, Boolean> getSectionList() {return sectionList;}
    public void setSectionList(HashMap<String, Boolean> sectionList) {this.sectionList = sectionList;}
    public String getSectionID() {return sectionID;}
    public void setSectionID(String sectionID) {this.sectionID = sectionID;}
    public Question getQuestion() {return question;}
    public void setQuestion(Question question) {this.question = question;}

    public ArrayList<Assignment> getAvailableAssignments() {
        return new ArrayList<>(availableAssignments.values());
    }

    public void setAvailableAssignmentsFrom(ArrayList<Assignment> availableAssignments) {
        HashMap<String, Assignment> aa = new HashMap<>();
        for (int i =0; i< availableAssignments.size(); i++){
            aa.put(availableAssignments.get(i).getAssignmentID(),availableAssignments.get(i));
        }
        this.availableAssignments = aa;
         s.displaySectionAssingments(availableAssignments);
    }

    public void setAvailableAssignments(ArrayList<Assignment> availableAssignments) {
        HashMap<String, Assignment> aa = new HashMap<>();
        for (int i =0; i< availableAssignments.size(); i++){
            aa.put(availableAssignments.get(i).getAssignmentID(),availableAssignments.get(i));
        }
        this.availableAssignments = aa;
        // s.displaySectionAssingments(availableAssignments);
    }

    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================
    public ArrayList<Question> getAvailableQuestions(String topic, String sectionID){
        return dc.getAvailableQuestions(topic,sectionID);
    }
    public void createQuestion(String qStatement, String correctAnswer,String wrongAnswer, String topic, int weight){
        Question q=new Question(topic,qStatement,correctAnswer, wrongAnswer, weight);
        dc.addQuestion(q);
    }
//    public Question editQuestion(String questionID){
//        return getQuestion(questionID);
//    }
    public void updateQuestion(Question question){
        dc.updateQuestion(question);
    }
//    private String addQuestion(Question q){
//        return dc.addQuestion(q);
//    }
//    private Question getQuestion(String questionID) {
//        return dc.getQuestion(questionID);
//    }
    //=========================================================================================================================
    //ASSIGNMENTS
    //=========================================================================================================================
    public void getAssignments(String sectionID){
        dc.getAvailableAssignments(this,sectionID);
        //return dc.getAvailableAssignments(sectionID);
    }
    public void createAssignment(String name, String topic, int numQuestions, String dueDate, String submissionPeriod, HashMap<String, Boolean> sectionList){
        Assignment a= new Assignment(name, topic, numQuestions, dueDate,submissionPeriod, sectionList);
        dc.addAssignment(a);
    }
//    public Assignment editAssignment(String sectionID){
//        for(Assignment a:availableAssignments)
//            for(String secID:a.getSectionList())
//                if(secID==sectionID)
//                    return a;
//        return null;
//    }
//    public void updateAssignment(Assignment assignment){
//        for(Assignment a:availableAssignments) {
//            if(a.getAssignmentID()==assignment.getAssignmentID()){
//                a=assignment;
//            }
//        }
//    }

}
