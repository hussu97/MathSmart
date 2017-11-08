package com.pythagorithm.mathsmartv2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Assignments extends AppCompatActivity {
    private ViewGroup myRoot1;
    private ViewGroup myRoot2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);





        myRoot1 = (ViewGroup) findViewById(R.id.pendingAssignmentsHolder);
        myRoot2 = (ViewGroup) findViewById(R.id.completedAssingmentsHolder);


        displayPendingAssingments(15);
        displayCompleteAssingments(15);
    }
    @Override
    public void onBackPressed() {
    }
    public void clicked(View v){

        TextView assTitle = (TextView) v.findViewById(R.id.assTitle);

        Toast.makeText(getApplicationContext(), assTitle.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, assignmentPreview.class);
        intent.putExtra("assignmentnumber", assTitle.getText().toString());
        startActivity(intent);

    }
    public void logout(View v){


        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

    }
    void displayPendingAssingments(int number){

        for (int i = 1; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignment_box, null, false);
            TextView assTitle = (TextView) inflatedLayout.findViewById(R.id.assTitle);
            TextView assDesc = (TextView) inflatedLayout.findViewById(R.id.assDesc);
            assTitle.setText("Assingment " + i);
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
    void displayCompleteAssingments(int number){

        for (int i = 1; i < number; i++) {
            View inflatedLayout= LayoutInflater.from(this).inflate(R.layout.assignmentcompleted_box, null, false);
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
}
