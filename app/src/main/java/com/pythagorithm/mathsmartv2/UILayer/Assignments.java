package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.R;

public class Assignments extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);

        myRoot1 = (ViewGroup) findViewById(R.id.pendingAssignmentsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.completedAssingmentsHolder);

        getIntent().getStringExtra("ass");

        displayPendingAssingments(15);
        displayCompleteAssingments(3);
    }
    @Override
    public void onBackPressed() {
    }
    public void clicked(View v){

        TextView assTitle = (TextView) v.findViewById(R.id.assTitle);
        TextView assDesc = (TextView) v.findViewById(R.id.assDesc);
        TextView assTopic = (TextView) v.findViewById(R.id.Descrip1);
        TextView assDueDate = (TextView) v.findViewById(R.id.Descrip2);
        if(assTitle.getText().toString().trim().endsWith("2")||assTitle.getText().toString().trim().endsWith("3")||
                assTitle.getText().toString().trim().endsWith("1"))
        {
            Intent intent = new Intent(this, assignmentPreview.class);
            intent.putExtra("assignmentnumber", assTitle.getText().toString());
            intent.putExtra("assignmentdescription", assDesc.getText().toString());
            intent.putExtra("assignmenttopic", assTopic.getText().toString());
            intent.putExtra("assignmentduedate", assDueDate.getText().toString());
            startActivity(intent);
        }

    }
    public void logout(View v){


        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

    }
    void displayPendingAssingments(int number){

        String dates[]={"Nov 12 2017","Nov 28 2017","Dec 2 2017"};
        String topics[]={"Multiplication","Algebra","Fractions"};
        for (int i = 1; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignment_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            TextView assTopic = (TextView)inflatedLayout.findViewById(R.id.Descrip1);
            TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.Descrip2);
            assTitle.setText("Assingment " + i);
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Description " + i);
            //assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.BLACK);

            if(i<4){
                assTopic.setText(topics[i-1]);
                assTopic.setTextColor(Color.GRAY);
                assDueDate.setText(dates[i-1]);
                assDueDate.setTextColor(Color.GRAY);
            }
            else{
                assTopic.setText("");
                assDueDate.setText("");
            }
            inflatedLayout.setPadding(0,0,25,0);
            myRoot1.addView(inflatedLayout);
            //R.id.editID

        }

    }
    void displayCompleteAssingments(int number){
        String dates[]={"Nov 5 2017","Nov 7 2017"};
        String topics[]={"Addition","Subtraction",};
        for (int i = 1; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentcompleted_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            TextView assTopic = (TextView)inflatedLayout.findViewById(R.id.Descrip1);
            TextView assDueDate = (TextView) inflatedLayout.findViewById(R.id.Descrip2);
            assTitle.setText("Assingment " + i);
            //assTitle.setPadding(0,0,20,0);
            assTitle.setTextColor(Color.BLACK);

            assDesc.setText("Description " + i);
            //assDesc.setPadding(0,0,20,0);
            assDesc.setTextColor(Color.BLACK);
            inflatedLayout.setPadding(0,0,25,0);

            if(i<3){
                assTopic.setText(topics[i-1]);
                assTopic.setTextColor(Color.GRAY);
                assDueDate.setText(dates[i-1]);
                assDueDate.setTextColor(Color.GRAY);
            }
            myRoot2.addView(inflatedLayout);
            //R.id.editID

        }

    }
}
