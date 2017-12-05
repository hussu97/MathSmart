package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentProgress;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.R;

import java.util.Arrays;

public class assignmentPreview extends AppCompatActivity {
    TextView assTitle;
    TextView assNumQuestions;
    TextView assTopic;
    TextView assDueDate;
    Student student;
    String assID;
    Assignment assignment;
    AssignmentProgress ap;
    Button btn;
    Button prepareButton;
    DatabaseConnector dc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_preview);
        student = getIntent().getParcelableExtra("student");
        student.setAssignmentPreview(this);
        assTitle = (TextView) findViewById(R.id.assTitle);
        assTopic=(TextView) findViewById((R.id.assTopic));
        assDueDate=(TextView) findViewById((R.id.assDue));
        assNumQuestions=(TextView) findViewById((R.id.assCorrectAnswers));
        btn = (Button) findViewById(R.id.startAssignmentButton);
        dc = new DatabaseConnector(this);
        assignment = getIntent().getParcelableExtra("assignment");
        assTitle.setText(assignment.getAssignmentName());
        assTopic.setText(assignment.getAssignmentTopic());
        assDueDate.setText(assignment.getDueDate());
        btn.setVisibility(View.INVISIBLE);
        dc.getAssignmentProgress( student, student.getStudentID(), assignment.getAssignmentID());


    }

    public void showButton(AssignmentProgress a){
        if(a != null){
            btn.setText("Resume Assignment");
        }
        btn.setVisibility(View.VISIBLE);
        ap = a;
    }


    public void clicked(View v){
        if (v.getId()== R.id.startAssignmentButton) {
            Intent intent = new Intent(this, assignmentQuestion.class);
            Bundle toSend = new Bundle();
            toSend.putParcelable("assignment", assignment);
            toSend.putParcelable("student", student);
            toSend.putParcelable("assignmentProgress", ap);
            intent.putExtras(toSend);
            startActivityForResult(intent,3);
        }

    }
}
