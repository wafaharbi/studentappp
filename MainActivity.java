package com.example.wafa.studentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SignStudent(View v){
        Intent i = new Intent(this, LoginStudent.class);
        startActivity(i);
    }

    public void SignParent(View v){
        Intent i = new Intent(this, LoginParent.class);
        startActivity(i);
    }

    public void SignTeacher(View v){
        Intent i = new Intent(this, LoginTeacher.class);
        startActivity(i);
    }

}
