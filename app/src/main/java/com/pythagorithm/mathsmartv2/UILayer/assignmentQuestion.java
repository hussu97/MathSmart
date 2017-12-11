package com.pythagorithm.mathsmartv2.UILayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentProgress;
import com.pythagorithm.mathsmartv2.AppLogic.Question;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.R;

import java.util.Random;

import io.github.kexanie.library.MathView;

public class assignmentQuestion extends AppCompatActivity {
    Student student;
    Assignment assignment;
    Question currentQuestion;
    MathView questionFormula;
    MathView answer1;
    MathView answer2;
    MathView answer3;
    MathView answer4;
    String questionNumm;
    private TextView qNo;
    boolean correctAnswer = false;


    TableRow row1;
    TableRow row2;
    TableRow row3;
    TableRow row4;

    final Random rand = new Random();
    boolean optionSelected;
    boolean done;
    boolean exit;
    int questionNum;
    int count;
    Button nxtbtn;
    TextView qstnNumber;
    private boolean waitingForQuestion=true;

    private String mathviewify(String questionStatement){
        return "$$" + questionStatement + "$$";
    }

    public void setQuestion() {
        waitingForQuestion = false;
        currentQuestion = student.getaH().getCurrentQuestion();
        questionFormula.setText(mathviewify(currentQuestion.getQuestionStatment()));
        //qNo.setText(student.getaH().get);
        answer1.setText(mathviewify(currentQuestion.getCorrectAnswer()));
        row1.setTag("correct");
        row2.setTag("wrong");
        answer2.setText(mathviewify(currentQuestion.getWrongAnswer1()));
        if(currentQuestion.getWrongAnswer2()!=null) {
            answer3.setText(mathviewify(currentQuestion.getWrongAnswer2()));
            answer4.setText(mathviewify(currentQuestion.getWrongAnswer3()));
        }
        optionSelected = false;
        nxtbtn.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_question);
        Bundle received = getIntent().getExtras();
        AssignmentProgress ap = received.getParcelable("assignmentProgress");

        qNo=(TextView)findViewById(R.id.questionNumber);
        exit=false;
        done = false;
        student = (Student) received.getParcelable("student");

        assignment = (Assignment) received.getParcelable("assignment");
        if (ap == null)
            student.createAssignmentHandler(false, null, assignment);
        else
            student.createAssignmentHandler(true, ap, assignment);
        student.getaH().setAssignmentQuestion(this);

        Log.d("assignmentQuestion", "received assignment " + assignment.getAssignmentID());
        questionFormula = (MathView) findViewById(R.id.questionFormula);
        answer1 = (MathView) findViewById(R.id.answer1);
        answer2 = (MathView) findViewById(R.id.answer2);
        answer3 = (MathView) findViewById(R.id.answer3);
        answer4 = (MathView) findViewById(R.id.answer4);

        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row3 = (TableRow) findViewById(R.id.row3);
        row4 = (TableRow) findViewById(R.id.row4);
        questionNumm = getIntent().getStringExtra("assignmentNum");
        nxtbtn = (Button) findViewById(R.id.nxtbtn);
        nxtbtn.setEnabled(false);

    }

    public void optionSelect(View v) {
        if (!waitingForQuestion) {
            nxtbtn.setEnabled(true);
            optionSelected = true;
            row1.setBackgroundResource(0);
            row2.setBackgroundResource(0);
            row3.setBackgroundResource(0);
            row4.setBackgroundResource(0);
            v.setBackgroundResource(R.drawable.border);
            if (v.getTag().equals("correct")) correctAnswer = true;
            else correctAnswer = false;
        }
    }
    void finishAssignment(){
        nxtbtn.setEnabled(true);
        done = true;
        Toast.makeText(this, "Done",Toast.LENGTH_LONG).show();
        nxtbtn.setText("Submit Assignment");
    }
    //
    public void nextQuestionBtn(View v) {
        if(done){
            Log.d("Hussu",student.getaH().getAssignmentReport().getAssignmentID());
            Intent returnIntent = new Intent(this, assignmentPreview.class);
            returnIntent.putExtra("result", 1);
            returnIntent.putExtra("report", student.getaH().getAssignmentReport());
            returnIntent.putExtra("assignment",assignment);
            returnIntent.putExtra("student", student);
            startActivity(returnIntent);
            finish();
        }
        else if (optionSelected&&!waitingForQuestion) {
            waitingForQuestion = true;
            nxtbtn.setEnabled(false);
            int version = rand.nextInt(4);
            row1.setBackgroundResource(0);
            row2.setBackgroundResource(0);
            row3.setBackgroundResource(0);
            row4.setBackgroundResource(0);
            if(student.getaH().solveQuestion(4, correctAnswer)){
                finishAssignment();
            }
            optionSelected=false;
        }
        if(exit){
            Intent returnIntent = new Intent(this, Assignments.class);
            returnIntent.putExtra("student", student);
            startActivity(returnIntent);
            finish();
        }
    }
    public void noQuestionsError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No more questions were found, click the exit assignment button to continue").
                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        nxtbtn.setText("Exit assignment");
        nxtbtn.setEnabled(true);
        exit=true;
    }
}
