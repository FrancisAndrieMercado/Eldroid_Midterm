package com.example.midterm_eldroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

public class DatabaseConnector extends SQLiteOpenHelper {
    public DatabaseConnector(Context context) {
        super(context, "UserData.db", null, 1); }
    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        sqlDB.execSQL("create Table userDetails(idNumber INTEGER primary key, firstName TEXT, lastName TEXT, contact INTEGER, dateBirth TEXT)"); }
    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int u, int u1) {
        sqlDB.execSQL("drop Table if exists userDetails"); }
    /*Create*/
    public boolean create(String idNumber, String firstName, String lastName, String contact, String dateBirth) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idNumber", idNumber);
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("contact", contact);
        values.put("dateBirth", dateBirth);
        long result = sqlDb.insert("userDetails", null, values);
        if (result == -1) {
            return false;
        } else {
            return true; } }
    /*Update*/
    public boolean update(String idNumber, String firstName, String lastName, String contact, String dateBirth) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("contact", contact);
        values.put("dateBirth", dateBirth);
        Cursor cursor = sqlDb.rawQuery("Select * from userDetails where idNumber = ?", new String[]{idNumber});
        if (cursor.getCount() > 0) {
            long result = sqlDb.update("userDetails", values, "idNumber=?", new String[]{idNumber});
            if (result == -1) {
                return false;
            } else {
                return true; }
        } else {
            return false; } }
    /*Delete*/
    public boolean delete(String idNumber) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from userDetails where idNumber = ?", new String[]{idNumber});
        if (cursor.getCount() > 0) {
            long result = sqlDb.delete("userDetails", "idNumber=?", new String[]{idNumber});
            if (result == -1) {
                return false;
            } else {
                return true; }
        } else {
            return false; } }
    /*RetrieveAll*/
    public Cursor getdata() {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from userDetails", null);
        return cursor; }
    /*RetrieveId*/
    public Cursor retrievedata(EditText idNumber) {
        String id = idNumber.getText().toString();
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from userDetails where idNumber ='" + id + "'", null);
        return cursor;
    }
}


