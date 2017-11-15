package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.pythagorithm.mathsmartv2.R;

public class Sections extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        myRoot1 = (ViewGroup) findViewById(R.id.sectionsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.assingmentsHolder);

        displayPendingAssingments(15);
    }
    public void createBtn(View v){

        Intent intent = new Intent(this, addQuestion.class);
        startActivity(intent);

    }
    public void secClicked(View v){

        TextView secNum = (TextView) findViewById(R.id.sectionAssList);
        TextView secTitle = (TextView) v.findViewById(R.id.secTitle);
        secNum.setText(secTitle.getText().toString()+"'s Assignments");
        displaySectionAssingments(8);

    }
    @Override
    public void onBackPressed(){

    }

    void displayPendingAssingments(int number){

        for (int i = 0; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.section_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.secTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.secDesc);
            assTitle.setText("Section " + i);
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Description " + i);
            //assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.BLACK);
            inflatedLayout.setPadding(0,0,25,0);
            myRoot1.addView(inflatedLayout);
            //R.id.editID

        }

    }
    void displaySectionAssingments(int number){
        myRoot2.removeAllViews();
        for (int i = 0; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentteacher_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            assTitle.setText("Assingment " + i);
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Completed " + i);
            //assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.BLACK);
            inflatedLayout.setPadding(0,0,25,0);

            myRoot2.addView(inflatedLayout);
            //R.id.editID

        }

    }
    public void logOut(View v){
        FirebaseAuth.getInstance().signOut();
        Log.d("FireAuth",FirebaseAuth.getInstance().getUid());
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
