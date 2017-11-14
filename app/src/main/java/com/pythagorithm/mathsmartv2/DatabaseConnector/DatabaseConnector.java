package com.pythagorithm.mathsmartv2.DatabaseConnector;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.pythagorithm.mathsmartv2.AppLogic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by H_Abb on 11/2/2017.
 */

public class DatabaseConnector {
    private String ID;

    private AssignmentHandler assignmentHandler;


    public DatabaseConnector(String userID){
        this.ID=userID;
    }
    public DatabaseConnector(){}


    public DatabaseConnector(AssignmentHandler assignmentHandler){
        this.assignmentHandler = assignmentHandler;
    }

    //=========================================================================================================================
    //OTHER
    //=========================================================================================================================
    public String login(String username){
        return "";
    }
    public String getSectionID(String studentID){
        return "";
    }
    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================
    public ArrayList<Question> getAvailableQuestions(String topic, String sectionID){

        return new ArrayList<>();
    }
    public void getQuestion(final ArrayList<String> completedQuestion,int weight, String topic){


            FirebaseFirestore.getInstance().collection("question")
                    .whereEqualTo("difficulty", weight)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult()){
                                    if (Arrays.asList(completedQuestion).contains(doc.getId())){
                                        Log.d("Firestore", "Question with ID: "+ doc.getId() +" found. Not needed.");
                                        assignmentHandler.getNextQuestion();
                                    }
                                    else {
                                        Log.d("Firestore", "Question with ID: "+ doc.getId() +" found. Success.");
                                        Question q = doc.toObject(Question.class);
                                        Log.d("Firestore", "onComplete: "+ doc.getData());
                                        //====================================================================
                                        // Function that gets called in the AssignmentHandler class
                                        //====================================================================
                                        assignmentHandler.setCurrentQuestion(q);
                                    }
                                }
                            }
                        }

                    });

    }

    public Question getQuestion(String qID){
        String[] s=new String[4];
        return new Question("s",s,"s",4,"s");
    }

    public String addQuestion(Question q){
        return "null";
    }
    public void updateQuestion(Question q){

    }
    //=========================================================================================================================
    //ASSIGNMENTS
    //=========================================================================================================================
    public ArrayList<Assignment> getAvailableAssignments(String sectionID){
        return new ArrayList<>();
    }
    public void getAssignmentProgress(String studentID,String aID,ArrayList<String> completedQuestions,double assignmentScore,int min){
        //Change values of completedQuestions, assignmentScore, and min
        //If not available, change value of completedQuestions to 'null'
    }
    public String addAssignment(String sectionList[],ArrayList<Assignment> assignmentList){
        return "JI";
    }
    public void saveAssignment(final String studentID, final String aID, final ArrayList<String> completedQuestions, final double assignmentScore, final double overallScore){
        AssignmentProgress ap = new AssignmentProgress(studentID,aID, completedQuestions,assignmentScore,overallScore);
        FirebaseFirestore.getInstance().collection("assignment-progress")
                .add(ap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "Wrote assignment progress for assignment " + aID);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error writing document", e);
                    }
                });
    }
    //=========================================================================================================================
    //SCORES
    //=========================================================================================================================
    public void updateScore(String studentID, final String questionID, String assignmentID, boolean correct, int time, String topic, int difficulty){
        QuestionScore qs = new QuestionScore(studentID, questionID, assignmentID, correct, time, topic, difficulty);
        FirebaseFirestore.getInstance().collection("question-scores")
                .add(qs)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "Score of question" +questionID+ " saved ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error writing score", e);
                    }
        });

    }
    public double getAssignmentScore(String aID,String studentID){
        return 0;
    }
    public double getOverallScore(String studentID){
        return 0;
    }
}
