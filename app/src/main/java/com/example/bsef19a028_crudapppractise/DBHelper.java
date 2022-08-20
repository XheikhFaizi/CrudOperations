package com.example.bsef19a028_crudapppractise;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(Context context)
    {
        super(context,"Userdata.db",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create Table UserDetails(name Text primary key ,contact Text ,email Text ,dob Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("drop Table if exists UserDetails");

    }

    public void Adddata(String Name, String Email, String Contact ,String Dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",Name);
        values.put("contact",Contact);
        values.put("email",Email);
        values.put("dob",Dob);

        db.insert("UserDetails",null,values);
        Log.d("Userdata" , "All DOne");
        db.close();

    }





}
