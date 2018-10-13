package com.example.wafa.studentapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginParent extends AppCompatActivity {

    TextView forget, signup;
    EditText studentEmail, studentPassword, studentUsername;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_parent);

        studentUsername = (EditText) findViewById(R.id.editUsernamep);
        studentEmail = (EditText) findViewById(R.id.editEloginp);
        studentPassword = (EditText) findViewById(R.id.editPloginp);
        forget = (TextView) findViewById(R.id.textviewForgetp);
        signup = (TextView) findViewById(R.id.textViewSignupp);
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Parents");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupParent.class);
                startActivity(i);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ForgetPassword.class);
                startActivity(i);
            }
        });

    }

    public void userloginStudent(View v) {

        final String username = studentUsername.getText().toString().trim();
        final String email = studentEmail.getText().toString().trim();
        final String password = studentPassword.getText().toString().trim();


        if (username.isEmpty()) {
            studentUsername.setError(" invalid username");
            studentUsername.requestFocus();
            return;

        }
        if (email.isEmpty()) {
            studentEmail.setError(" error email");
            studentEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            studentEmail.setError("  invalid email");
            studentEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            studentPassword.setError("blank");
            studentPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            studentPassword.setError(" password at least 6 charecter");
            studentPassword.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), " user login ", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(getApplicationContext(), ParentInfo.class);
                    startActivity(i);

                }
                else {
                    Toast.makeText(getApplicationContext(), "you should  Signup first ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }




}
