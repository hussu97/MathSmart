package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.pythagorithm.mathsmartv2.R;

import java.util.Random;

import io.github.kexanie.library.MathView;

public class assignmentQuestion extends AppCompatActivity {
    MathView questionFormula;
    MathView answer1;
    MathView answer2;
    MathView answer3;
    MathView answer4;
    String question[][];
    String correctAnswer[][];
    String wrongAnswer[][][];
    String questionNumm;
    String ans1 = "$$x = 3$$";
    String ans2 = "$$x = 4$$";
    String ans3 = "$$x = 8$$";
    String ans4 = "$$x = 5$$";

    TableRow row1;
    TableRow row2;
    TableRow row3;
    TableRow row4;

    final Random rand=new Random();
    boolean optionSelected;
    int questionNum;
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
        question=new String[4][5];
        correctAnswer=new String [4][5];
        wrongAnswer=new String [4][5][3];
        question[0][0]="$$7*5$$";
        question[0][1]="$$4*3*2$$";
        question[0][2]="$$10*7$$";
        question[0][3]="$$12*3$$";
        question[0][4]="$$7*9$$";
        question[1][0]="$$3x+3=5$$";
        question[1][1]="$$5y=15$$";
        question[1][2]="$$7x-18x=9$$";
        question[1][3]="$$2=8x-9$$";
        question[1][4]="$$7z-9=0$$";
        question[2][0]="$$\\frac{5}{6}+\\frac{1}{6}$$";
        question[2][1]="$$\\frac{17}{8}+3$$";
        question[2][2]="$$\\frac{2}{3}-\\frac{1}{4}+\\frac{1}{9}$$";
        question[2][3]="$$\\frac{2}{3}*\\frac{3}{4}$$";
        question[2][4]="$$\\frac{1}{5}-\\frac{4}{3}$$";
        correctAnswer[0][0]="$$35$$";
        correctAnswer[0][1]="$$24$$";
        correctAnswer[0][2]="$$70$$";
        correctAnswer[0][3]="$$36$$";
        correctAnswer[0][4]="$$63$$";
        correctAnswer[1][0]="$$x=\\frac{2}{3}$$";
        correctAnswer[1][1]="$$y=3$$";
        correctAnswer[1][2]="$$x=\\frac{-9}{11}$$";
        correctAnswer[1][3]="$$x=\\frac{8}{11}$$";
        correctAnswer[1][4]="$$z=\\frac{9}{7}$$";
        correctAnswer[2][0]="$$1$$";
        correctAnswer[2][1]="$$\\frac{41}{8}$$";
        correctAnswer[2][2]="$$\\frac{19}{36}$$";
        correctAnswer[2][3]="$$\\frac{1}{2}$$";
        correctAnswer[2][4]="$$\\frac{17}{5}$$";
        wrongAnswer[0][0][0]="$$15$$";
        wrongAnswer[0][0][1]="$$12$$";
        wrongAnswer[0][0][2]="$$42$$";
        wrongAnswer[0][1][0]="$$67$$";
        wrongAnswer[0][1][1]="$$23$$";
        wrongAnswer[0][1][2]="$$13$$";
        wrongAnswer[0][2][0]="$$87$$";
        wrongAnswer[0][2][1]="$$15$$";
        wrongAnswer[0][2][2]="$$8$$";
        wrongAnswer[0][3][0]="$$56$$";
        wrongAnswer[0][3][1]="$$1$$";
        wrongAnswer[0][3][2]="$$27$$";
        wrongAnswer[0][4][0]="$$43$$";
        wrongAnswer[0][4][1]="$$44$$";
        wrongAnswer[0][4][2]="$$0$$";
        wrongAnswer[1][0][0]="$$x=-1$$";
        wrongAnswer[1][0][1]="$$x=54$$";
        wrongAnswer[1][0][2]="$$x=\\frac{7}{8}$$";
        wrongAnswer[1][1][0]="$$y=\\frac{5}{15}$$";
        wrongAnswer[1][1][1]="$$y=4$$";
        wrongAnswer[1][1][2]="$$y=\\frac{8}{5}$$";
        wrongAnswer[1][2][0]="$$x=9$$";
        wrongAnswer[1][2][1]="$$x=\\frac{9}{11}$$";
        wrongAnswer[1][2][2]="$$x=23$$";
        wrongAnswer[1][3][0]="$$x=8$$";
        wrongAnswer[1][3][1]="$$x=19$$";
        wrongAnswer[1][3][2]="$$x=\\frac{3}{4}$$";
        wrongAnswer[1][4][0]="$$z=79$$";
        wrongAnswer[1][4][1]="$$z=\\frac{7}{9}$$";
        wrongAnswer[1][4][2]="$$z=0$$";
        wrongAnswer[2][0][0]="$$-1$$";
        wrongAnswer[2][0][1]="$$\\frac{4}{6}$$";
        wrongAnswer[2][0][2]="$$-1$$";
        wrongAnswer[2][1][0]="$$\\frac{6}{11}$$";
        wrongAnswer[2][1][1]="$$\\frac{3}{2}$$";
        wrongAnswer[2][1][2]="$$\\frac{65}{3}$$";
        wrongAnswer[2][2][0]="$$\\frac{13}{111}$$";
        wrongAnswer[2][2][1]="$$\\frac{-39}{41}$$";
        wrongAnswer[2][2][2]="$$\\frac{3}{4}$$";
        wrongAnswer[2][3][0]="$$3$$";
        wrongAnswer[2][3][1]="$$2$$";
        wrongAnswer[2][3][2]="$$\\frac{3}{4}$$";
        wrongAnswer[2][4][0]="$$\\frac{14}{33}$$";
        wrongAnswer[2][4][1]="$$\\frac{4}{17}$$";
        wrongAnswer[2][4][2]="$$\\frac{17}{64}$$";
        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row3 = (TableRow) findViewById(R.id.row3);
        row4 = (TableRow) findViewById(R.id.row4);
        questionNumm=getIntent().getStringExtra("assignmentNum");
        questionNum=Integer.valueOf(questionNumm.substring(questionNumm.length()-1));
        questionNum--;
        count = 1;
        questionFormula.setText(question[questionNum][count-1]);
        answer1.setText(correctAnswer[questionNum][count-1]);
        answer2.setText(wrongAnswer[questionNum][count-1][0]);
        answer3.setText(wrongAnswer[questionNum][count-1][1]);
        answer4.setText(wrongAnswer[questionNum][count-1][2]);

        optionSelected=false;
        nxtbtn = (Button) findViewById(R.id.nxtbtn);
        qstnNumber = (TextView) findViewById(R.id.questionNumber);
        qstnNumber.setText("Question #1");
    }

    public void optionSelect(View v){
        optionSelected=true;
        row1.setBackgroundResource(0);
        row2.setBackgroundResource(0);
        row3.setBackgroundResource(0);
        row4.setBackgroundResource(0);
        Toast.makeText(getApplicationContext(), "ass", Toast.LENGTH_SHORT).show();
        v.setBackgroundResource(R.drawable.border);

    }

    public void nextQuestionBtn(View v){
        if(optionSelected) {
            int version=rand.nextInt(4);
            row1.setBackgroundResource(0);
            row2.setBackgroundResource(0);
            row3.setBackgroundResource(0);
            row4.setBackgroundResource(0);
            count++;
            if (count == 5) {
                nxtbtn.setText("Submit Assingment");

            }
            if (count >= 6) {
                Intent intent = new Intent(this, Assignments.class);
                startActivity(intent);
            } else {
                qstnNumber.setText("Question #" + count);
            }
            if (count < 6) {
                questionFormula.setText(question[questionNum][count - 1]);
                switch(version){
                    case 0:
                        answer1.setText(correctAnswer[questionNum][count-1]);
                        answer2.setText(wrongAnswer[questionNum][count-1][0]);
                        answer3.setText(wrongAnswer[questionNum][count-1][1]);
                        answer4.setText(wrongAnswer[questionNum][count-1][2]);
                        break;
                    case 1:
                        answer2.setText(correctAnswer[questionNum][count-1]);
                        answer1.setText(wrongAnswer[questionNum][count-1][0]);
                        answer3.setText(wrongAnswer[questionNum][count-1][1]);
                        answer4.setText(wrongAnswer[questionNum][count-1][2]);
                        break;
                    case 2:
                        answer3.setText(correctAnswer[questionNum][count-1]);
                        answer2.setText(wrongAnswer[questionNum][count-1][0]);
                        answer1.setText(wrongAnswer[questionNum][count-1][1]);
                        answer4.setText(wrongAnswer[questionNum][count-1][2]);
                        break;
                    case 3:
                        answer4.setText(correctAnswer[questionNum][count-1]);
                        answer2.setText(wrongAnswer[questionNum][count-1][0]);
                        answer3.setText(wrongAnswer[questionNum][count-1][1]);
                        answer1.setText(wrongAnswer[questionNum][count-1][2]);
                        break;
                    default:
                }

            }
            optionSelected=false;
        }
    }


}
