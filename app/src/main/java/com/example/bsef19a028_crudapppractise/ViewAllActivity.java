package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    Button back;

    DBHelper db = new DBHelper(ViewAllActivity.this);

    List<Contact> mylust = new ArrayList<Contact>();

    ArrayAdapter arrayAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        ListView lsView = findViewById(R.id.myListview);


        mylust=db.viewall();
        arrayAdapter = new ArrayAdapter<Contact>
                (ViewAllActivity.this, android.R.layout.simple_list_item_1, mylust);


        Contact Contact1 = new Contact();
        Contact1.name = "Con1";
        Contact1.contact = "312312";
        Contact1.email = "asdas";
        Contact1.dob = "625365127";

        Contact Contact2 = new Contact();
        Contact2.name = "Con2";
        Contact2.contact = "8734282";
        Contact2.email = "asdas";
        Contact2.dob = "625365127";

        db.Adddata(Contact1);
        db.Adddata(Contact2);



        lsView.setAdapter(arrayAdapter);

        back = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAllActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        lsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};


                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewAllActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //update

                            Cursor c = db.getData("SELECT id FROM RECORD");

                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
                        }
                        if (i == 1) {
                            //delete
                            Cursor c = db.getData("SELECT contact FROM UserDetails");

                            ArrayList<String> arrID = new ArrayList<String>();
                            while (c.moveToNext()) {
                                arrID.add(c.getString(0));
                            }

                            showDialogDelete(arrID.get(0));
                        }
                    }
                });
                dialog.show();
                return true;
            }


        });
    }




        private void showDialogDelete(final String idRecord) {
            AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ViewAllActivity.this);
            dialogDelete.setTitle("Warning!!");
            dialogDelete.setMessage("Are you sure to delete?");
            dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                       db.deletecontact(idRecord);

                        Toast.makeText(ViewAllActivity.this, "Delete successfully", Toast.LENGTH_SHORT).show();
                        Intent homepage = new Intent(ViewAllActivity.this, MainActivity.class);
                        startActivity(homepage);

                    }
                    catch (Exception e){
                        Log.e("error", e.getMessage());
                    }

                }
            });
            dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialogDelete.show();
        }




    }
