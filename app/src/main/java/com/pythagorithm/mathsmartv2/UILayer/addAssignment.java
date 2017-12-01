package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pythagorithm.mathsmartv2.R;

public class addAssignment extends AppCompatActivity {
    Spinner topicSpinner;
    Spinner ansSpinner;
    Spinner secSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        topicSpinner = (Spinner) findViewById(R.id.topicSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.topics, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(adapter);

        ansSpinner = (Spinner) findViewById(R.id.ansSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.answers, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        ansSpinner.setAdapter(adapter2);

        secSpinner = (Spinner) findViewById(R.id.secSpinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.sections, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(adapter3);
    }

    public void buttonAssignmentClicked(View v){
        Intent intent = new Intent(this, Sections.class);
        startActivity(intent);
    }


}
