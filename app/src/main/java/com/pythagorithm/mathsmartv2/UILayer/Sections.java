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

import java.util.ArrayList;
import java.util.HashMap;

public class Sections extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    private Teacher teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        myRoot1 = (ViewGroup) findViewById(R.id.sectionsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.assingmentsHolder);
        Intent i = getIntent();
        teacher = (Teacher)i.getParcelableExtra("teacher");
        teacher.setSections(this);
        displaySections(teacher.getSectionList().size());
    }
    public void createBtn(View v){
        if(v.getId() == R.id.button3) {
            Intent intent = new Intent(this, addQuestion.class);
            intent.putExtra("teacher",teacher);
            startActivity(intent);
        }
        if(v.getId() == R.id.button5){
            Intent intent = new Intent(this, addAssignment.class);
            intent.putExtra("teacher",teacher);
            startActivity(intent);
        }

    }
    public void secClicked(View v){

        TextView secNum = (TextView) findViewById(R.id.sectionAssList);
        TextView secTitle = (TextView) v.findViewById(R.id.secTitle);
        secNum.setText(secTitle.getText().toString()+"'s Assignments");
        teacher.getAssignments(secTitle.getText().toString());
    }
    @Override
    public void onBackPressed(){

    }

    public void displaySections(int number){

        HashMap<String,Boolean> sectionList=teacher.getSectionList();
        for (int i = 0; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.section_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.secTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.secDesc);
            assTitle.setText( sectionList.keySet().toArray()[0].toString());
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Description " + i);
            assDesc.setTextColor(Color.BLACK);
            inflatedLayout.setPadding(0,0,25,0);
            myRoot1.addView(inflatedLayout);

        }

    }
    public void displaySectionAssingments(ArrayList<Assignment> ass){
        Log.d("Hussu","Entered displaySecAss "+ass.size());
        for (int i = 0; i < ass.size(); i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentteacher_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.teacherAssignmentTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.teacherAssignmentDesc);
            TextView assID = (TextView) inflatedLayout.findViewById(R.id.Descrip1);
            Log.d("Hussu","AssignmentName "+ass.get(i).getAssignmentName());
            assTitle.setText( ass.get(i).getAssignmentName());
            assTitle.setTextColor(Color.BLACK);
            assDesc.setText(ass.get(i).getAssignmentTopic());
            assDesc.setTextColor(Color.BLACK);

            inflatedLayout.setPadding(0,0,25,0);

            myRoot2.addView(inflatedLayout);

        }

    }
    public void startReportsActivityTeacher(View v){
        Intent newIntent = new Intent(this, reportTeacher.class);
        newIntent.putExtra("teacher",teacher);
        startActivity(newIntent);

    }
    public void logOut(View v){
        Log.d("FireAuth",FirebaseAuth.getInstance().getUid());
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
