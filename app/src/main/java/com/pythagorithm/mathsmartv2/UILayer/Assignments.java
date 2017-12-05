package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentProgress;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.R;

public class Assignments extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    private Student student;
    private DatabaseConnector dc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        dc = new DatabaseConnector();

        Intent intent = getIntent();

        student = (Student)intent.getParcelableExtra("student");

        student.setAssignmentsActivity(this);

        getAssignments();

        myRoot1 = (ViewGroup) findViewById(R.id.pendingAssignmentsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.completedAssingmentsHolder);

        Toast.makeText(this,getIntent().getStringExtra("username"),
                Toast.LENGTH_SHORT).show();

    }

    private void getAssignments(){
        dc.getAvailableAssignments(student);
        dc.getCompletedAssignments(student);
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

    public void startAssignment(){

    }

//        if(assTitle.getText().toString().trim().endsWith("2")||assTitle.getText().toString().trim().endsWith("3")||
//                assTitle.getText().toString().trim().endsWith("1"))
//        {
//            Intent intent = new Intent(this, assignmentPreview.class);
//            intent.putExtra("assignmentnumber", assTitle.getText().toString());
//            intent.putExtra("assignmentdescription", assDesc.getText().toString());
//            intent.putExtra("assignmenttopic", assTopic.getText().toString());
//            intent.putExtra("assignmentduedate", assDueDate.getText().toString());
//            startActivity(intent);
//        }


    public void logout(View v){


        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

    }
    public void displayPendingAssingments(){


        for (int i = 0; i < student.getAssignmentList().size(); i++) {
            Assignment currAssignment = student.getAssignmentList().get(i);
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignment_box, null, false);
            inflatedLayout.setTag(currAssignment.getAssignmentID());
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            TextView assTopic = (TextView)inflatedLayout.findViewById(R.id.Descrip1);
            TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.Descrip2);
            assTitle.setText(currAssignment.getAssignmentName());
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText(currAssignment.getAssignmentTopic());
            //assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.BLACK);

            assTopic.setText(currAssignment.getAssignmentTopic());
            assTopic.setTextColor(Color.GRAY);
            assDueDate.setText(currAssignment.getDueDate());
            assDueDate.setTextColor(Color.GRAY);

            inflatedLayout.setPadding(0,0,25,0);
            myRoot1.addView(inflatedLayout);
            //R.id.editID
        }

    }
    public void displayCompleteAssingments(){
        for (int i = 0; i < student.getCompletedAssignments().size(); i++) {
            String compAss = student.getCompletedAssignments().get(i);
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentcompleted_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            TextView assTopic = (TextView)inflatedLayout.findViewById(R.id.Descrip1);
            TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.Descrip2);
            assTitle.setText(compAss);
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Description " + i);
            //assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.BLACK);
            inflatedLayout.setPadding(0,0,25,0);

            myRoot2.addView(inflatedLayout);
            //R.id.editID

        }

    }
}
