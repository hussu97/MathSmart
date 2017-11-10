package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.pythagorithm.mathsmartv2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.github.kexanie.library.MathView;

public class addQuestion extends AppCompatActivity {
    MathView formula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        formula = (MathView) findViewById(R.id.questionFormula);
        String tex = "";
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
    public void previewBtn(View v){
        EditText previewer= (EditText) findViewById(R.id.qEditTxt);

        switch(v.getId()){
            case R.id.qPrev:
                previewer= (EditText) findViewById(R.id.qEditTxt);
                break;
            case R.id.caPrev:
                previewer= (EditText) findViewById(R.id.caEditTxt);
                break;
            case R.id.waPrev1:
                previewer= (EditText) findViewById(R.id.waEditTxt1);
                break;
            case R.id.waPrev2:
                previewer= (EditText) findViewById(R.id.waEditTxt2);
                break;
            case R.id.waPrev3:
                previewer= (EditText) findViewById(R.id.waEditTxt3);
                break;
        }
        String displayable = "$$" + previewer.getText().toString() + "$$";
        formula.setText(displayable);



    }
    public void assclicked(View v){
        try {
            Process process = new ProcessBuilder()
                    .command("logcat", "-c")
                    .redirectErrorStream(true)
                    .start();
        } catch (IOException e) {
        }

        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log=new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }
            TextView tv = (TextView)findViewById(R.id.textView10);
            if (log.toString().contains("Failed to parse")) //this is the line that contains all the needed information
            {

                tv.setText("TRUE");
            }
            else{
                tv.setText("FAlse");
            }

        }
        catch (IOException e) {}
    }
    public void buttonClicked(View v){
            Intent intent = new Intent(this, Sections.class);
            startActivity(intent);
    }
}
