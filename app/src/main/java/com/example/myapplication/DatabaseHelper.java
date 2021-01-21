package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(6207886695,'Vikash Kumar Prajapati',1895000.00,'vikash10357@gmail.com','XXXXXXXXXXXX4321','VKP09876541')");
        db.execSQL("insert into user_table values(6235678901,'Vikku',75582.67,'vikku@gmail.com','XXXXXXXXXXXX5682','VIK98765438')");
        db.execSQL("insert into user_table values(9846789012,'Rohit',78359.56,'rohit@gmail.com','XXXXXXXXXXXX8917','HIT87654325')");
        db.execSQL("insert into user_table values(8841890123,'Pikachu',56500.01,'pikachu@gmail.com','XXXXXXXXXXXX4784','PIK76543213')");
        db.execSQL("insert into user_table values(8978901234,'Pika Pika',7803.48,'pikapika@gmail.com','XXXXXXXXXXXX2389','CHU65432108')");
        db.execSQL("insert into user_table values(8584012345,'Vikku Ro hit',9635.16,'vikkurohit@gmail.com','XXXXXXXXXXXX2559','KKU54321099')");
        db.execSQL("insert into user_table values(9890175556,'Yo Yo Vikku',25036.00,'yoyo@gmail.com','XXXXXXXXXXXX5565','YOV43210984')");
        db.execSQL("insert into user_table values(8912347567,'Vikash',88967.22,'vikash@gmail.com','XXXXXXXXXXXX5289','MCG32109879')");
        db.execSQL("insert into user_table values(9632345678,'Development',44700.46,'development@gmail.com','XXXXXXXXXXXX3758','DEV21098766')");
        db.execSQL("insert into user_table values(8964567809,'Sledger',44700.00,'sledger@gmail.com','XXXXXXXXXXXX7860','SLD10987650')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
