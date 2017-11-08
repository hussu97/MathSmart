package com.pythagorithm.mathsmartv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class assignmentPreview extends AppCompatActivity {
    TextView assTitle;
    TextView assDesc;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_preview);
        String name = getIntent().getStringExtra("assignmentnumber");
        assTitle = (TextView) findViewById(R.id.assTitle);
        assDesc = (TextView) findViewById(R.id.assDesc);
        assTitle.setText(name);
    }
    public void clicked(View v){
        TextView assTitle = (TextView) v.findViewById(R.id.assDesc);

//        Toast.makeText(getApplicationContext(), assTitle.getText().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, assignmentQuestion.class);
        //intent.putExtra("assignmentnumber", assTitle.getText().toString());
        startActivity(intent);

    }
}
