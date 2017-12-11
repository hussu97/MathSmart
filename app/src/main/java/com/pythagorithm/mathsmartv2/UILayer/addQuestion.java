package com.pythagorithm.mathsmartv2.UILayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.R;
import com.pythagorithm.mathsmartv2.UIConnector.UIConnector;

import io.github.kexanie.library.MathView;

public class addQuestion extends AppCompatActivity {
    private MathView formula;
    private Spinner topicSpinner;
    private Spinner difSpinner;
    private Teacher teacher;
    private EditText qStatement;
    private EditText cAnswer;
    private EditText wAnswer1;
    private EditText wAnswer2;
    private EditText wAnswer3;
    private UIConnector uic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        formula = (MathView) findViewById(R.id.questionFormula);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        teacher = (Teacher)getIntent().getParcelableExtra("teacher");

        uic=new UIConnector(this);
        topicSpinner = (Spinner) findViewById(R.id.topicSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.topics, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(adapter);

        difSpinner = (Spinner) findViewById(R.id.difSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.difficulty, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        difSpinner.setAdapter(adapter2);

        qStatement=(EditText)findViewById(R.id.qEditTxt);
        cAnswer=(EditText) findViewById(R.id.caEditTxt);
        wAnswer1=(EditText) findViewById(R.id.waEditTxt1);
        wAnswer2=(EditText) findViewById(R.id.waEditTxt2);
        wAnswer3=(EditText) findViewById(R.id.waEditTxt3);
    }
    public void previewBtn(View v){
        EditText previewer= (EditText) findViewById(R.id.qEditTxt);

        switch(v.getId()){
            case R.id.qPrev:
                previewer= qStatement;
                break;
            case R.id.caPrev:
                previewer= cAnswer;
                break;
            case R.id.waPrev1:
                previewer= wAnswer1;
                break;
            case R.id.waPrev2:
                previewer= wAnswer2;
                break;
            case R.id.waPrev3:
                previewer= wAnswer3;
                break;
        }
        String displayable = "$$" + previewer.getText().toString() + "$$";
        formula.setText(displayable);



    }
    public void addQuestionClicked(View v){
        Log.d("Hussu","{"+wAnswer2.getText().toString()+"}");
        if(qStatement.getText().toString().isEmpty()||
                cAnswer.getText().toString().isEmpty()||
                wAnswer1.getText().toString().isEmpty()||
                wAnswer2.getText().toString().isEmpty()||
                wAnswer3.getText().toString().isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter values in all the required fields").
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {}
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            teacher.createQuestion(qStatement.getText().toString().trim(),
                    cAnswer.getText().toString().trim(),
                    wAnswer1.getText().toString().trim(),
                    wAnswer2.getText().toString().trim(),
                    wAnswer3.getText().toString().trim(),
                    topicSpinner.getSelectedItem().toString(),
                    Integer.parseInt(difSpinner.getSelectedItem().toString()));
        }
    }
    public void addedQuestion(){
        Log.d("Hussu", "Going back to teacher activity");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Question successfully added").
                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(addQuestion.this, Sections.class);
                        intent.putExtra("teacher", teacher);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
