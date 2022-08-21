package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText name, contact,email, dob;
    Button insert,update,delete,view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        insert= findViewById(R.id.button12);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , InsertActivity.class);
                startActivity(intent);
            }
        });

        update= findViewById(R.id.button13);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , UpdateActivity.class);
                startActivity(intent);
            }
        });

        delete= findViewById(R.id.button14);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DeleteActivity.class);
                startActivity(intent);
            }
        });

        view= findViewById(R.id.button19);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , ViewAllActivity.class);
                startActivity(intent);
            }
        });













        DBHelper db = new DBHelper(MainActivity.this);


        Contact Contact1  =new Contact();
        Contact1.name="Con1";
        Contact1.contact="312312";
        Contact1.email="asdas";
        Contact1.dob="625365127";

        Contact Contact2  =new Contact();
        Contact2.name="Con2";
        Contact2.contact="8734282";
        Contact2.email="asdas";
        Contact2.dob="625365127";

        db.Adddata(Contact1);
        db.Adddata(Contact2);

        Contact2.name="IMRAAAN PASHA";

        db.update(Contact2);




        List<Contact> mylust = db.viewall();

        for (Contact cc : mylust)
        {   Log.d("Userdata", "Name: " + cc.name + "\n" +"Email: " + cc.email + "\n" + "Contact: " + cc.contact + "\n" );

        }
    }



}