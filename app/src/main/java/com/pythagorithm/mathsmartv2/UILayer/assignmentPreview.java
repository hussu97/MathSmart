package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.R;

public class assignmentPreview extends AppCompatActivity {
    TextView assTitle;
    TextView assNumQuestions;
    TextView assTopic;
    TextView assDueDate;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_preview);
        String name = getIntent().getStringExtra("assignmentnumber");
        assTitle = (TextView) findViewById(R.id.assTitle);
        assTopic=(TextView) findViewById((R.id.assTopic));
        assDueDate=(TextView) findViewById((R.id.assDue));
        assNumQuestions=(TextView) findViewById((R.id.assCorrectAnswers));
        assTitle.setText(name);
        assTopic.setText(getIntent().getStringExtra("assignmenttopic"));
        assDueDate.setText(getIntent().getStringExtra("assignmentduedate"));

    }
    public void clicked(View v){
        Intent intent = new Intent(this, assignmentQuestion.class);
        intent.putExtra("assignmentNum",(assTitle.getText().toString().trim()));
        startActivity(intent);

    }
}
