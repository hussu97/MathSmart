package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.R;

public class reportStudent extends AppCompatActivity {

    private ViewGroup myRoot1;
    private Student student;
    private DatabaseConnector dc;
    private TextView overall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_student);
        myRoot1 = (ViewGroup) findViewById(R.id.pendingAssignmentsHolder);
        student = getIntent().getParcelableExtra("student");
        overall=(TextView)findViewById((R.id.OverallScoreStudent));
        displayAssignmentScores();

    }
    @Override
    public void onBackPressed() {}
    public void logout_SR(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void assignmentClicked_SR(View v){
        Intent intent = new Intent(this, Assignments.class);
        startActivity(intent);
    }
    public void reportClicked_SR(View v){
        Intent intent = new Intent(this, reportStudent.class);
        startActivity(intent);
    }
    public void displayAssignmentScores(){
        String topics[]={"algebra,addition,multiplication,algebra,fractions"};
        double scores[]={5.40,6.10,5.42,6.30,6.85};

        overall.setText(String.valueOf(6.42));
        for (int i = 0; i < 5; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentscore_box, null, false);
            TextView assignmentTitle=(TextView)findViewById(R.id.assScoreTitle);
            TextView assignmentScore=(TextView)findViewById(R.id.assScoreScore);
            TextView assignmentTopic=(TextView)findViewById(R.id.assScoreTopic);
            assignmentTitle.setText("Assignment"+(i+1));
            assignmentScore.setText(String.valueOf(scores[i]));
            assignmentTopic.setText(topics[i]);
            inflatedLayout.setPadding(0,0,25,0);

            myRoot1.addView(inflatedLayout);
        }
    }
    public void updateUI(){
        View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentcompleted_box, null, false);
        TextView assignmentTitle=(TextView)findViewById(R.id.assScoreTitle);
        TextView assignmentScore=(TextView)findViewById(R.id.assScoreScore);
        TextView assignmentTopic=(TextView)findViewById(R.id.assScoreTopic);
        assignmentTitle.setText("Assignment 5");
        assignmentScore.setText(String.valueOf(6.94));
        assignmentTopic.setText("fractions");
        inflatedLayout.setPadding(0,0,25,0);
        myRoot1.addView(inflatedLayout);
        overall.setText(String.valueOf(6.58));
    }
}
