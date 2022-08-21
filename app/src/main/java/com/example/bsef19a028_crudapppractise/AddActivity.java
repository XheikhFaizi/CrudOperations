package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        EditText edtName = findViewById(R.id.edtName);
        EditText edtEmail = findViewById(R.id.Email);
        EditText edtContact = findViewById(R.id.edtPhone);
        EditText edtDob = findViewById(R.id.edtdob);
        Button btnUpdate = findViewById(R.id.btnUpdate);

        DBHelper db = new DBHelper(AddActivity.this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact cc = new Contact();
                cc.name=edtName.getText().toString().trim();
                cc.email=edtEmail.getText().toString().trim();
                cc.contact=edtContact.getText().toString().trim();
                cc.dob= edtDob.getText().toString().trim();
                String id = cc.contact;

                try {
                    db.Adddata(cc);

                    Toast.makeText(getApplicationContext(), "Contact Added Successfull", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error){
                    Log.e("Update error", error.getMessage());
                }
                Intent homepage = new Intent(AddActivity.this,ViewAllActivity.class);
                startActivity(homepage);
            }


        });
    }
}