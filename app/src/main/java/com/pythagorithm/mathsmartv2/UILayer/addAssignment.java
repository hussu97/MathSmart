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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class addAssignment extends AppCompatActivity {
    private Spinner topicSpinner;
    private Spinner ansSpinner;
    private Spinner secSpinner;
    private Spinner daySpinner;
    private Spinner monthSpinner;
    private Spinner yearSpinner;
    private String[] sectionSet;
    private Teacher teacher;
    private EditText assignmentName;
    private EditText assignmentCreateSubmissionPeriod;
    private UIConnector uic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        teacher = (Teacher)getIntent().getParcelableExtra("teacher");
        sectionSet=teacher.getSectionList().keySet().toArray(new String[teacher.getSectionList().size()]);

        assignmentName=(EditText)findViewById((R.id.assignmentCreateTitle));
        assignmentCreateSubmissionPeriod=(EditText)findViewById((R.id.assignmentCreateSubPeriod));

        uic=new UIConnector((this));
        topicSpinner = (Spinner) findViewById(R.id.topicSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.topics, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(adapter);

        daySpinner=(Spinner)findViewById(R.id.daySpinner);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(
                this, R.array.day, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter4);
        monthSpinner=(Spinner)findViewById(R.id.monthSpinner);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(
                this, R.array.month, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter5);
        yearSpinner=(Spinner)findViewById(R.id.yearSpinner);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(
                this, R.array.year, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter6);
        ansSpinner = (Spinner) findViewById(R.id.ansSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.answers, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        ansSpinner.setAdapter(adapter2);

        secSpinner = (Spinner) findViewById(R.id.secSpinner);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                sectionSet);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(adapter3);
    }

    public void createAssignmentButton(View v) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        String date=daySpinner.getSelectedItem().toString()+"/"+monthSpinner.getSelectedItem().toString()+"/"+yearSpinner.getSelectedItem().toString();
        Log.d("Hussu","Assignment create date: "+date);
        Date strDate=null;
        try {
            strDate = sdf.parse(date);
        }catch(ParseException e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Date is invalid").
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
        Log.d("Hussu","Assignment create clicked");
        if(assignmentName.getText().toString().isEmpty()||
                assignmentCreateSubmissionPeriod.getText().toString().isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter values in all the required fields").
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(new Date().after(strDate)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Due Date is outdated, Please try again").
                    setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            HashMap<String,Boolean> sections=new HashMap<>();
            sections.put(secSpinner.getSelectedItem().toString(),true);
            teacher.createAssignment(assignmentName.getText().toString().trim(),
                    topicSpinner.getSelectedItem().toString(),
                    Integer.parseInt(ansSpinner.getSelectedItem().toString()),
                    date,
                    assignmentCreateSubmissionPeriod.getText().toString().trim(),
                    sections);
        }
    }

    public void addedAssignment(){
        Log.d("Hussu", "Going back to teacher activity");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Assignment successfully added").
                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(addAssignment.this, Sections.class);
                        intent.putExtra("teacher", teacher);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
