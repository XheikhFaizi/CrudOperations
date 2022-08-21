package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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