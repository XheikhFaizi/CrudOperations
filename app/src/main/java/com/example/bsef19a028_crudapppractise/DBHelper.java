package com.example.bsef19a028_crudapppractise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {



    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    //deleteData
    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM RECORD WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }




    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table UserDetails(name Text primary key ,contact Text ,email Text ,dob Text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists UserDetails");

    }

    public void Adddata(Contact cc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", cc.name);
        values.put("contact", cc.contact);
        values.put("email", cc.email);
        values.put("dob", cc.dob);

        db.insert("UserDetails", null, values);
        Log.d("Userdata", "All DOne");
        db.close();

    }

    public List<Contact> viewall()
    {
        List<Contact> contactlist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Read data
        String select = "SELECT * FROM UserDetails ";
        Cursor cursor =db.rawQuery(select,null);

        /////Moving Cursor through all data
        if(cursor.moveToFirst())
        {
            do
            {   Contact chck = new Contact();
                chck.setname(cursor.getString(0));
                chck.setcontact(cursor.getString(1));
                chck.setemail(cursor.getString(2));
                chck.setdob(cursor.getString(3));

                contactlist.add(chck);

            }
            while (cursor.moveToNext());
        }
    return contactlist;
    }
    public void updateData(String name, String email, String contact, String dob ,String Id){
        SQLiteDatabase database = getWritableDatabase();
        //query to update record
        String sql = "UPDATE UserDetails SET name=?, contact=?, email=?,  dob=? WHERE contact=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, contact);
        statement.bindString(3, email);
        statement.bindString(4, dob);
        statement.bindString(5, Id);

        statement.execute();
        database.close();
    }

    public int update(Contact cc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", cc.name);
        values.put("contact", cc.contact);
        values.put("email", cc.email);
        values.put("dob", cc.email);

    //the below statement will update the old content of contact to the new one.
        return db.update("UserDetails", values, "contact" + "=?",
                new String[]{String.valueOf(cc.getId())});

    }


    public void deletecontact(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("UserDetails","contact" + "=?", new String[]{String.valueOf(id)});
    }
}


