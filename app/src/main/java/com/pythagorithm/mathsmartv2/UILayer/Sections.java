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
import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Sections extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    Teacher t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        myRoot1 = (ViewGroup) findViewById(R.id.sectionsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.assingmentsHolder);
        Intent i = getIntent();
        ((TextView)findViewById(R.id.sectionListTxt)).setText(i.getStringExtra("username"));
        displayPendingAssingments(15);
        t = new Teacher("tuff");
        HashMap<String, Boolean> sections = new HashMap<String, Boolean>();
        sections.put("sectionA", true);
        t.setSectionList(sections);
        t.setSections(this);
    }
    public void createBtn(View v){
        if(v.getId() == R.id.button3) {
            Intent intent = new Intent(this, addQuestion.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.button5){
            Intent intent = new Intent(this, addAssignment.class);
            startActivity(intent);
        }

    }
    public void secClicked(View v){

        TextView secNum = (TextView) findViewById(R.id.sectionAssList);
        TextView secTitle = (TextView) v.findViewById(R.id.secTitle);
        secNum.setText(secTitle.getText().toString()+"'s Assignments");
        t.getAssignments("sectionA");
    }
    @Override
    public void onBackPressed(){

    }

    public void displayPendingAssingments(int number){

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
    public void displaySectionAssingments(ArrayList<Assignment> ass){
        myRoot2.removeAllViews();
        for (int i = 0; i < ass.size(); i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentteacher_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            TextView assID = (TextView) inflatedLayout.findViewById(R.id.Descrip1);
            assTitle.setText( ass.get(i).getAssignmentName());
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText(ass.get(i).getAssignmentID());
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
