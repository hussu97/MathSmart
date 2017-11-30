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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pythagorithm.mathsmartv2.AppLogic.Assignment;
import com.pythagorithm.mathsmartv2.AppLogic.AssignmentHandler;
import com.pythagorithm.mathsmartv2.AppLogic.Question;
import com.pythagorithm.mathsmartv2.DatabaseConnector.DatabaseConnector;
import com.pythagorithm.mathsmartv2.R;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ViewGroup containerLayout;
    private ImageView logoView;
    private EditText username;
    private EditText password;
    private TextView userWarning;
    private FirebaseAuth mAuth;

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
        HashMap<String, Boolean> str= new HashMap<String, Boolean>();

        mAuth=FirebaseAuth.getInstance();

        str.put("sectionA", true);
        str.put("sectionB", true);
        Assignment a = new Assignment("name", "fractions", 3, "3-2-2018", "4 days", str);
        Question q = new Question("fractions", "fraction those numbers", "correct", "wrong", 3);
        DatabaseConnector c = new DatabaseConnector();
        c.addQuestion(q);
        c.addAssignment(a);
        AssignmentHandler ah = new AssignmentHandler(a, "123", 4.23,15);
        ah.saveAssignment();
    }

    @Override
    public void onBackPressed() {
    }
    /*
    Function used to login the user into the respective interface
    Description: FireBaseAuth listener created that listens for user pressing the login button
    Preconditions:
    Postcondition: If student, starts assignments activity and student continues from there
                   If teacher, starts sections activity and teacher continues from there
                   False: If username/password incorrect, and error message is shown
     */
    public void onBtnClick(View v){
        //Start loading screen dialogue
        mAuth.signInWithEmailAndPassword(username.getText().toString().toLowerCase().trim(),password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("FireAuth","CreateEmail:Success");
                            FirebaseUser user=mAuth.getCurrentUser();
                            //If user is a student
                            if(username.getText().toString().toLowerCase().trim().startsWith("s")) {
                                Intent intent = new Intent(LoginActivity.this, Assignments.class);
                                intent.putExtra("username", user.getUid());
                                startActivity(intent);
                            }
                            //If user is a teacher
                            else {
                                Intent intent = new Intent(LoginActivity.this, Sections.class);
                                intent.putExtra("username",user.getUid());
                                startActivity(intent);
                            }
                        }else {
                            Log.w("FireAuth","Authentication failed");
                            userWarning.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    public static void dispQ(){
        Log.d("Firestore", "found q lol");
    }

}
