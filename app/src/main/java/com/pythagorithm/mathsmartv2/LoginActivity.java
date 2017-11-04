package com.pythagorithm.mathsmartv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper();
        Question q = new Question("this is a stupid q", "correct", "wrong", "3", "maths","ds");

        db.addQuestion(q);
    }


}
