package com.pythagorithm.mathsmartv2;


import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URL;
import java.util.Arrays;

import database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    CurrentQuestion curr;
    TextView tv;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper();
        tv =  (TextView) findViewById(R.id.idddd);
//        for (int i =0; i< 3; i ++){
//            for (int j = 3;j<5; j++){
//                db.addQuestion( new Question("topic af", "question statement af", "correct af", "wrong af", j));
//            }
//        }
        String[] prevQ = {"B5R04fNFieHy6iqigZtl","MdtknP6c3Vs7iCuqRXen"};

        curr = new CurrentQuestion();
        sup s = new sup();
        s.getNextQuesion(prevQ, 3, curr);



    }

    void display(){
        tv.setText(curr.getQuestionObject().getQuestionStatment());
    }

    private class sup {
        public  void getNextQuesion(final String[] prevQs, final int diff, final CurrentQuestion curr){
            FirebaseFirestore.getInstance().collection("question")
                    .whereEqualTo("difficulty", diff)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult()){
                                    if (Arrays.asList(prevQs).contains(doc.getId())){
                                        Log.d("Firestore", "Question with ID: "+ doc.getId() +" found. Not needed.");
                                    }
                                    else {
                                        Log.d("Firestore", "Question with ID: "+ doc.getId() +" found. Success.");
                                        Question q = doc.toObject(com.pythagorithm.mathsmartv2.Question.class);
                                        Log.d("Firestore", "onComplete: "+ doc.getData());
                                        curr.setQuestionObject(q);
                                        curr.setImported(true);
                                        display();
                                    }
                                }
                            }
                        }

                    });
        }
    }
}
