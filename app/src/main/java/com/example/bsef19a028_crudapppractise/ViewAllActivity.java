package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        ListView lsView =findViewById(R.id.myListview);

        DBHelper db = new DBHelper(ViewAllActivity.this);


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


        List<Contact> mylust = db.viewall();

        ArrayAdapter arrayAdapter = new ArrayAdapter<Contact>
                (ViewAllActivity.this, android.R.layout.simple_list_item_1,mylust);
        lsView.setAdapter(arrayAdapter);

        back= findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}