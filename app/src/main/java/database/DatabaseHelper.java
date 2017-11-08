package database;

import android.support.annotation.NonNull;
import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.pythagorithm.mathsmartv2.*;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amro Aljundi on 11/4/2017.
 * This class interfaces the Firestore realtime database with the application
 */
public class DatabaseHelper {
    // This is the name of the collection for questions
    final static String QUESTION_COLLECTION = "question";
    // This tag is used for logging
    final static String DATABASE_TAG = "Firestore";
    // This flag is used to determine whether update functions run correctly
    boolean success;
    String aID="";
    String qID="";
    static FirebaseFirestore db;

    // at the initiation of the database, the connection is created
    public DatabaseHelper(){
        try {
            db = FirebaseFirestore.getInstance();
        } catch(Exception e){
            Log.d(DATABASE_TAG, e.toString());
        }
    }

    /*  Arguments: Question object
        Returns: ID of question in DB as string
        Preconditions: Connected to database and there is a Question object to be added to database.
        Postconditions: A new Question object has been added to the database and its ID is returned.
   */
    public String addQuestion(Question q){
        db.collection(QUESTION_COLLECTION)
                .add(q)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        qID = documentReference.getId();
                        Log.d(DATABASE_TAG, "addQuestion() succeeded with ID: "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(DATABASE_TAG, "addQuestion() failed ", e);
                        qID = "Failed";
                    }
                });
        return qID;
    }

    /*  Arguments: Assignment object
        Returns: ID of Assignment in DB as string
        Preconditions: Connected to database and there is an Assignment object to be added to database.
        Postconditions: A new Assignment object has been added to the database and its ID is returned.
   */
    public String addAssignment(Assignment a){
        db.collection(QUESTION_COLLECTION)
                .add(a)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        aID = documentReference.getId();
                        Log.d(DATABASE_TAG, "addAssignment() succeeded with ID: "+documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(DATABASE_TAG, "addAssignment() failed ", e);
                        aID = "Failed";
                    }
                });
        return aID;
    }

    /*
        Arguments: new Question object; String for the questionID
        Returns: boolean indicating status of operation
        Preconditions: Question object with ID = currQID exists in the database and
         q is a valid Question Object
         Postconditions: Question with ID currQID is changed to q
     */
    public boolean updateQuestion(final String attribute, final String value, final String currQID){
        success = false;
        db.collection(QUESTION_COLLECTION)
                .document(currQID)
                .update(attribute, value)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(DATABASE_TAG, "updatedQuestion succeeded. Updated qID: "+ currQID +" attribute: "+ attribute
                        + " with new value: "+ value);
                        success = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(DATABASE_TAG, "updateQuestion() failed ", e);
                    }
                });
        return success;
    }
    /*
        Arguments: new Question object; String for the questionID
        Returns: boolean indicating status of operation
        Preconditions: Question object with ID = currQID exists in the database and
         q is a valid Question Object
         Postconditions: Question with ID currQID is changed to q
     */
    public void updateAssignment(final String attribute, final String value, final String currAID) {
        db.collection(QUESTION_COLLECTION)
                .document(currAID)
                .update(attribute, value)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(DATABASE_TAG, "updatedAssignment succeeded. Updated aID: " + currAID + " attribute: " + attribute
                                + " with new value: " + value);
                        success = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(DATABASE_TAG, "updateAssignment() failed ", e);
                    }
                });
    }
//    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//        @Override
//        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//            if (task.isSuccessful()){
//                for (DocumentSnapshot doc : task.getResult()){
//                    if (Arrays.asList(prevQs).contains(doc.getId())){
//                        Log.d(DATABASE_TAG, "Question with ID: "+ doc.getId() +" found. Not needed.");
//                    }
//                    else {
//                        Log.d(DATABASE_TAG, "Question with ID: "+ doc.getId() +" found. Success.");
//                        curr.setQuestionObject(doc.toObject(Question.class));
//                        curr.setImported(true);
//                    }
//                }
//            }
//        }
//    });
//    }

    public static void getNextQuesion(final String[] prevQs, final int diff, final CurrentQuestion curr){
        db.collection(QUESTION_COLLECTION)
                .whereEqualTo("difficulty", diff)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                if (Arrays.asList(prevQs).contains(doc.getId())){
                                    Log.d(DATABASE_TAG, "Question with ID: "+ doc.getId() +" found. Not needed.");
                                }
                                else {
                                    Log.d(DATABASE_TAG, "Question with ID: "+ doc.getId() +" found. Success.");
                                    Question q = doc.toObject(com.pythagorithm.mathsmartv2.Question.class);
                                    Log.d(DATABASE_TAG, "onComplete: "+ doc.getData());
                                    curr.setQuestionObject(q);
                                    curr.setImported(true);
//                                    LoginActivity.
                                }
                            }
                        }
                    }

        });
    }

}