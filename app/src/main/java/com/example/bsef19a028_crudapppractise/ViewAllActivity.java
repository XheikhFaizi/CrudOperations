package com.example.bsef19a028_crudapppractise;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.EditText;
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
                        if (i == 0)
                        {
                            //update
                            Cursor cv = db.getData("SELECT contact FROM UserDetails");

                            ArrayList<String> arrID = new ArrayList<String>();
                            while (cv.moveToNext()) {
                                arrID.add(cv.getString(0));
                            }
                            //show update dialog
                             showDialogUpdate(ViewAllActivity.this, arrID.get(position));
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
                        Intent homepage = new Intent(ViewAllActivity.this,ViewAllActivity.class);
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

    private void showDialogUpdate(Activity activity, String id){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_insert);
        dialog.setTitle("Update");


         EditText edtName = dialog.findViewById(R.id.edtName);
         EditText edtEmail = dialog.findViewById(R.id.Email);
         EditText edtContact = dialog.findViewById(R.id.edtPhone);
         EditText edtDob = dialog.findViewById(R.id.edtdob);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);

        //set width of dialog
        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        //set hieght of dialog
        int height = (int)(activity.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact cc = new Contact();
                cc.name=edtName.getText().toString().trim();
                cc.email=edtEmail.getText().toString().trim();
                cc.contact=edtContact.getText().toString().trim();
                cc.dob= edtDob.getText().toString().trim();


                try {
                    db.updateData(cc.name,cc.email,cc.contact,cc.dob,id);


                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successfull", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error){
                    Log.e("Update error", error.getMessage());
                }
                Intent homepage = new Intent(ViewAllActivity.this,ViewAllActivity.class);
                startActivity(homepage);
            }


        });












    }


    }
