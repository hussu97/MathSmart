package com.pythagorithm.mathsmartv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private ViewGroup containerLayout;
    private ImageView logoView;
    private EditText username;
    private EditText password;
    private TextView userWarning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logoView = (ImageView) findViewById(R.id.LogoImageView);
        containerLayout = (ViewGroup) findViewById(R.id.containerLayout);
        username = (EditText) findViewById(R.id.UsernameEditText);
        password = (EditText) findViewById(R.id.PasswordEditText);
        userWarning = (TextView) findViewById(R.id.warningUserTextView);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

    }
    @Override
    public void onBackPressed() {
    }
    public void onBtnClick(View v){
        if(username.getText().toString().trim().equals("") || password.getText().toString().trim().equals("")){
            userWarning.setVisibility(View.VISIBLE);

        }
        else{
            userWarning.setVisibility(View.GONE);

            if (username.getText().toString().trim().contains("s")) {
                Intent intent = new Intent(this, Assignments.class);
                startActivity(intent);
            }
            else if(username.getText().toString().trim().contains("t")){
                Intent intent = new Intent(this, Sections.class);
                startActivity(intent);
            }

        }




    }

}
