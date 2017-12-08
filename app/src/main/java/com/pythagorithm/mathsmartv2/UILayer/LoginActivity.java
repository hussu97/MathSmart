package com.pythagorithm.mathsmartv2.UILayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pythagorithm.mathsmartv2.AppLogic.Student;
import com.pythagorithm.mathsmartv2.AppLogic.Teacher;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.R;
import com.pythagorithm.mathsmartv2.UIConnector.UIConnector;

public class LoginActivity extends AppCompatActivity {

    private ViewGroup containerLayout;
    private ImageView logoView;
    private EditText username;
    private EditText password;
    private TextView userWarning;
    private FirebaseAuth mAuth;
    LoginActivity la;

    UIConnector uiConnector;

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
        la = this;
        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    public void onBackPressed() {}
    /*
    Function used to login the user into the respective interface
    Description: FireBaseAuth listener created that listens for user pressing the login button
    Preconditions:
    Postcondition: If student, starts assignments activity and student continues from there
                   If teacher, starts sections activity and teacher continues from there
                   False: If username/password incorrect, and error message is shown
     */
    public void LoginButtonClick(View v){
        //Start loading screen dialogue
        String usrname=username.getText().toString().trim().toLowerCase().concat("@mathsmart.edu");
        mAuth.signInWithEmailAndPassword(usrname,password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.d("FireAuth","CreateEmail:Success");
                            FirebaseUser user=mAuth.getCurrentUser();
                            DatabaseConnector c = new DatabaseConnector();
                            //If user is a student
                            if(username.getText().toString().toLowerCase().trim().startsWith("s")) {
                                Toast.makeText(la, "Loggin student in...", Toast.LENGTH_SHORT).show();
                                uiConnector = new UIConnector(la, user.getUid(), false);
                            }
                            // If user is a teacher
                            else {
                                Toast.makeText(la, "Loggin teacher in...", Toast.LENGTH_SHORT).show();
                                uiConnector = new UIConnector(la, user.getUid(), true);
                            }
                        }else {
                            Log.w("FireAuth","Authentication failed");
                            userWarning.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    public void startSectionsActivity(Teacher teacher){
        Log.d("Hussu","Teacher going to sections activity");
        Intent intent = new Intent(LoginActivity.this, Sections.class);
        intent.putExtra("teacher", teacher);
        startActivity(intent);
        finish();
    }

    public void startAssignmentsActivity(Student student){
        Log.d("FireAuth","Student Starting assignments activity");
        Intent intent = new Intent(LoginActivity.this, Assignments.class);
        intent.putExtra("student", student);
        startActivity(intent);
        finish();
    }

    public void displayError(String errorMessag){
        Toast.makeText(this,errorMessag, Toast.LENGTH_LONG).show();
    }
//l
}
