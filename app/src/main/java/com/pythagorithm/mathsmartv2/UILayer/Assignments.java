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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Assignments extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    private ViewGroup myRoot3;
    private Student student;
    private ArrayList<Assignment>missed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        missed=new ArrayList<>();
        Intent intent = getIntent();

        student = (Student)intent.getParcelableExtra("student");

        student.setAssignmentsActivity(this);

        TextView hi=(TextView)findViewById(R.id.assDueTxt);
        //hi.setText(String.valueOf(student.getOverallScore()));
        getAssignments();

        myRoot1 = (ViewGroup) findViewById(R.id.pendingAssignmentsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.completedAssingmentsHolder);
        myRoot3 = (ViewGroup) findViewById(R.id.missedAssignmentsHolder);

    }

    private void getAssignments(){
        student.fetchAssignmentLists();
        Toast.makeText(this, "Getting assignments...",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
    }
    public void clicked(View v){

        TextView assTitle = (TextView) v.findViewById(R.id.assTitle);
        TextView assDesc = (TextView) v.findViewById(R.id.assDesc);
        TextView assTopic = (TextView) v.findViewById(R.id.Descrip1);
        TextView assDueDate = (TextView) v.findViewById(R.id.Descrip2);
        String title = assTitle.getText().toString();

        Intent newIntent = new Intent(this, assignmentPreview.class);
        newIntent.putExtra("student", student);

        Assignment assignmentToSend = new Assignment();
        for (int i =0; i< student.getAssignmentList().size(); i++){
            if (student.getAssignmentList().get(i).getAssignmentID().equals(v.getTag().toString()))
                assignmentToSend = student.getAssignmentList().get(i);
        }
        newIntent.putExtra("assignment",assignmentToSend);
        startActivityForResult(newIntent,3);

    }

    public void startReportsActivity(View v){


        Intent newIntent = new Intent(this, reportStudent.class);
        newIntent.putExtra("student",student);
        startActivity(newIntent);

    }

    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

    }

    public void displayPendingAssingments() throws ParseException {
        boolean cont,missedB;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate;
        Log.d("Hussu",new Date().toString());
        for (int i = 0; i < student.getAssignmentList().size(); i++) {
            cont = true;
            missedB=false;
            Assignment currAssignment = student.getAssignmentList().get(i);
            for (int j = 0; j< student.getCompletedAssignments().size();j++){
                strDate=sdf.parse(currAssignment.getDueDate().replaceAll("-", "/"));
                Log.d("Hussu","Assignment date: "+currAssignment.getDueDate());

                if  (currAssignment.getAssignmentID().equals(student.getCompletedAssignments().get(j))) {
                    cont = false;
                    break;
                }
                else if(new Date().after(strDate)){
                    cont=false;
                    missedB=true;
                    break;
                }


            }
            Log.d("Hussu","boolean: "+missedB);
            Log.d("Hussu","boolean: "+cont);
            if (cont) {
                View inflatedLayout = LayoutInflater.from(this).inflate(R.layout.assignment_box, null, false);
                inflatedLayout.setTag(currAssignment.getAssignmentID());
                TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
                TextView assTopic = (TextView) inflatedLayout.findViewById(R.id.Descrip1);
                TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.Descrip2);
                assTitle.setText(currAssignment.getAssignmentName());
                //assTitle.setPadding(0,0,20,0);
                assTitle.setTextColor(Color.BLACK);

                assTopic.setText(currAssignment.getAssignmentTopic());
                assTopic.setTextColor(Color.GRAY);
                assDueDate.setText(currAssignment.getDueDate());
                assDueDate.setTextColor(Color.GRAY);

                inflatedLayout.setPadding(0, 0, 25, 0);
                myRoot1.addView(inflatedLayout);
                //R.id.editID
            }

            else if(missedB){
                Log.d("Hussu","Date missed: "+currAssignment.getDueDate());
                missed.add(currAssignment);
            }
        }
        displayMissedAssignments();

    }

    public void displayCompleteAssingments(){
        for (int i = 0; i < student.getCompletedAssignments().size(); i++) {
            String compAss = student.getCompletedAssignments().get(i);
            Assignment assignment = new Assignment();
            for (int j =0; j< student.getAssignmentList().size(); j++){
                if (compAss.equals(student.getAssignmentList().get(j).getAssignmentID()))
                    assignment = student.getAssignmentList().get(j);
            }
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentcompleted_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            TextView assTopic = (TextView)inflatedLayout.findViewById(R.id.Descrip1);
            TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.Descrip2);
            assTitle.setText(assignment.getAssignmentName());
            assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Due date: "+(assignment.getDueDate()));
            assTopic.setText(assignment.getAssignmentTopic());
            assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.GRAY);
            assDueDate.setText("");
            inflatedLayout.setPadding(0,0,25,0);

            myRoot2.addView(inflatedLayout);

        }

    }
    public void displayMissedAssignments(){
        Log.d("Hussu","entered display missed assignments");
        Log.d("Hussu",String.valueOf(missed.size()));
        for(int i=0;i<missed.size();i++){
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentsmissed_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.missedAssignmentTitle);
            TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.missedAssignmentDueDate);
            TextView assTopic = (TextView)inflatedLayout.findViewById(R.id.missedAssignmentTopic);
            assTitle.setText(missed.get(i).getAssignmentName());
            assDueDate.setText(missed.get(i).getDueDate());
            assTopic.setText(missed.get(i).getAssignmentTopic());
            assTitle.setPadding(0,0,20,0);
            assDueDate.setTextColor(Color.GRAY);
            assDueDate.setPadding(0,0,20,0);
            assTopic.setTextColor(Color.GRAY);
            assTopic.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            myRoot3.addView(inflatedLayout);
        }
    }
}