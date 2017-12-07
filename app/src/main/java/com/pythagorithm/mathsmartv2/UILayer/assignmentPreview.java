package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentProgress;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentReport;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.R;

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

    AssignmentReport assignmentReport;

    final int FINISH_ASSIGNMENT = 1;
    final int START_ASSIGNMENT = 2;
    int nextButtonStatus = START_ASSIGNMENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_preview);
        nextButtonStatus = getIntent().getIntExtra("result",0);
        assTitle = (TextView) findViewById(R.id.assTitle);
        assTopic=(TextView) findViewById((R.id.assTopic));
        assDueDate=(TextView) findViewById((R.id.assDue));
        assNumQuestions=(TextView) findViewById((R.id.assCorrectAnswers));
        btn = (Button) findViewById(R.id.startAssignmentButton);
        student = getIntent().getParcelableExtra("student");
        student.setAssignmentPreview(this);
        if (nextButtonStatus ==FINISH_ASSIGNMENT){

            assignmentReport = getIntent().getParcelableExtra("report");
            showResult();
            showButton(null);
        }
        else{
            assignment = getIntent().getParcelableExtra("assignment");
            assTitle.setText(assignment.getAssignmentName());
            assTopic.setText(assignment.getAssignmentTopic());
            assDueDate.setText(assignment.getDueDate());
            btn.setVisibility(View.INVISIBLE);
            student.getAssignmentProgress(assignment.getAssignmentID(), this);

        }

    }
    private void showResult(){
        assDueDate.setText(String.valueOf(assignmentReport.getAssignmentScore()));
    }

    public void showButton(AssignmentProgress a){
        ap = a;
        if(nextButtonStatus == FINISH_ASSIGNMENT){
            showResult();
            btn.setText("Finish Assignment");
        }
        else if (ap == null){
            btn.setText("Resume Assignment");
        }
        btn.setVisibility(View.VISIBLE);

    }


    public void clicked(View v){
        if (v.getId()== R.id.startAssignmentButton) {
            if (nextButtonStatus != FINISH_ASSIGNMENT) {
                Intent intent = new Intent(this, assignmentQuestion.class);
                Bundle toSend = new Bundle();
                toSend.putParcelable("assignment", assignment);
                toSend.putParcelable("student", student);
                toSend.putParcelable("assignmentProgress", ap);
                intent.putExtras(toSend);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, Assignments.class);
                intent.putExtra("student", student);
                startActivity(intent);
                finish();
            }
        }

    }
}
