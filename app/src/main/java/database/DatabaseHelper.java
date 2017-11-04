package database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.pythagorithm.mathsmartv2.*;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Amro Aljundi on 11/4/2017.
 * This class interfaces the Firestore realtime database with the application
 */
public class DatabaseHelper {
    // This is the name of the collection for questions
    final String QUESTION_COLLECTION = "question";
    // This tag is used for logging
    final String DATABASE_TAG = "Firestore";
    String qID="";
    FirebaseFirestore db;

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
        Preconditions: Connected to database and there is a question object to be added to database.
        Postconditions: A new quesiton object has been added to the database and its ID is returned
        to the databse.
   */
    public String addQuestion(Question q){
        Map<String, Object> question= new HashMap<>();
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

}
