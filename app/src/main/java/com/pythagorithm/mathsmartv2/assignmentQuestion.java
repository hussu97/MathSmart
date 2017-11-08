package com.pythagorithm.mathsmartv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import io.github.kexanie.library.MathView;

public class assignmentQuestion extends AppCompatActivity {
    MathView questionFormula;
    MathView answer1;
    MathView answer2;
    MathView answer3;
    MathView answer4;
    String tex = "$$x+5=8$$";
    String ans1 = "$$x = 3$$";
    String ans2 = "$$x = 4$$";
    String ans3 = "$$x = 8$$";
    String ans4 = "$$x = 5$$";

    TableRow row1;
    TableRow row2;
    TableRow row3;
    TableRow row4;

    int count;
    Button nxtbtn;
    TextView qstnNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_question);
        questionFormula = (MathView) findViewById(R.id.questionFormula);
        answer1 = (MathView) findViewById(R.id.answer1);
        answer2 = (MathView) findViewById(R.id.answer2);
        answer3 = (MathView) findViewById(R.id.answer3);
        answer4 = (MathView) findViewById(R.id.answer4);

        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row3 = (TableRow) findViewById(R.id.row3);
        row4 = (TableRow) findViewById(R.id.row4);
        questionFormula.setText(tex);
        answer1.setText(ans1);
        answer2.setText(ans2);
        answer3.setText(ans3);
        answer4.setText(ans4);

        nxtbtn = (Button) findViewById(R.id.nxtbtn);
        count = 1;
        qstnNumber = (TextView) findViewById(R.id.questionNumber);
        qstnNumber.setText("Question #1");
    }

    public void optionSelect(View v){
        row1.setBackgroundResource(0);
        row2.setBackgroundResource(0);
        row3.setBackgroundResource(0);
        row4.setBackgroundResource(0);
        Toast.makeText(getApplicationContext(), "ass", Toast.LENGTH_SHORT).show();
        v.setBackgroundResource(R.drawable.border);

    }

    public void nextQuestionBtn(View v){
        row1.setBackgroundResource(0);
        row2.setBackgroundResource(0);
        row3.setBackgroundResource(0);
        row4.setBackgroundResource(0);

        count++;

        if(count == 5){
            nxtbtn.setText("Submit Assingment");

        }
        if(count >= 6){
            Intent intent = new Intent(this, Assignments.class);
            startActivity(intent);
        }
        else{
        qstnNumber.setText("Question #" + count);
        }

    }


}
