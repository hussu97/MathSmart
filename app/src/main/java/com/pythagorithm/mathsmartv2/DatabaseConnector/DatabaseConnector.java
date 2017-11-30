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
import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentHandler;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentProgress;
import com.pythagorithm.mathsmartv2.AppLogic.Question;
import com.pythagorithm.mathsmartv2.AppLogic.QuestionScore;

import java.util.ArrayList;
import java.util.Arrays;

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
    public int getTotalQuestionsSolved(String studentID){return 1;}
    //=========================================================================================================================
    //QUESTIONS
    //=========================================================================================================================
    public ArrayList<Question> getAvailableQuestions(String topic, String sectionID){

        return new ArrayList<>();
    }
    public void getQuestion(final ArrayList<String> completedQuestion, final int weight, String topic){

            Log.d("Firestore", "Initialized getQuestion...");
            FirebaseFirestore.getInstance().collection("questions")
                    .whereEqualTo("weight", weight)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            Log.d("Firestore","Entered onComplete in getQuestion");
                            if (task.getResult().getDocuments().size()==0){
                                Log.d("Firestore", "Did not find a question with weight"+weight+". Getting a new questions...");
                                assignmentHandler.getNextQuestion();
                            }
                            else if (task.isSuccessful()){
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

//    public Question getQuestion(String qID){
//        String[] s=new String[4];
//        return new Question("s",s,"s",4,"s");
//    }

    public void addQuestion(final Question q){
        FirebaseFirestore.getInstance().collection("questions")
                .add(q)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "added question successfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore", "Question was not added successfully");
                    }
        });
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
//    public String addAssignment(String sectionList[],ArrayList<Assignment> assignmentList){
//        return "JI";
//    }

    public void addAssignment(final Assignment a){
        FirebaseFirestore.getInstance().collection("assignments")
                .add(a)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore", "added assignment successfully");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Firestore", "assignment was not added successfully");
            }
        });
    }

    /*
        Description: Save progress of an assignment to the database. Can be retrieved later.
        Precondition: a student is logged in and an assignment has been started.
        Postcondition: an assignment progress object is added to the database.
     */
    public void saveAssignmentProgress(final String studentID, final String aID, final ArrayList<String> completedQuestions, final double assignmentScore, int questionsLeft){
        final AssignmentProgress ap = new AssignmentProgress(studentID, aID,completedQuestions, assignmentScore,questionsLeft);

//        FirebaseFirestore.getInstance().collection("assignment-progress")
//                .whereEqualTo("assignmentID", aID)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot documentSnapshots) {
//                        updateAssignmentProgress(ap,documentSnapshots.getDocuments().get(0).getId());
//                        Log.d("Firestore","Attempt to save assignment " + aID + " progress: assignment exists and progress will update");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        createAssignmentProgress(ap);
//                        Log.d("Firestore","Attempt to save assignment " + aID + " progress: creating assignment progress");
//                    }
//        });

        FirebaseFirestore.getInstance().collection("assignment-progress")
                .document(aID)
                .set(ap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Firestore","updated assignment progress of assignment: "+aID);
                    }
                });
    }

    public void createAssignmentProgress(final String studentID, final String aID, final ArrayList<String> completedQuestions , int questionsLeft){
        final AssignmentProgress ap = new AssignmentProgress(studentID, aID,null, 0,questionsLeft);
        FirebaseFirestore.getInstance().collection("assignment-progress")
                .document(aID)
                .set(ap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Firestore","created assignment progress for assignment: "+aID);
                    }
                });
    }

    //=========================================================================================================================
    //SCORES
    //=========================================================================================================================

    /*
        Description: Create a  new score report for a solved question and updates the assignement score of that solved question
        Precondition: question has been solved.
        Postcondition: a new score for a question is added to the database. The assignment report of the assignment with assignment ID assignmentID
        is either created or updated with the new score
     */
    public void updateScore(String studentID, final String questionID, String assignmentID,ArrayList<String> completedQuestions, double questionScore, double assignementScore, int questionsLeft ,boolean correct, int time, String topic, int difficulty){
        QuestionScore qs = new QuestionScore(studentID, questionID, assignmentID, correct, time, topic, difficulty, questionScore);
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
        saveAssignmentProgress(studentID,assignmentID,completedQuestions,assignementScore,questionsLeft);

    }
    public double getAssignmentScore(String aID,String studentID){
        return 0;
    }
    public double getOverallScore(String studentID){
        return 0;
    }
}
