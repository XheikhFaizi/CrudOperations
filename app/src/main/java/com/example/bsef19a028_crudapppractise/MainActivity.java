package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText name, contact,email, dob;
    Button insert,update,delete,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(MainActivity.this);

        db.Adddata("FAizan","adasda", "asdasd","asdasda");


    }
}