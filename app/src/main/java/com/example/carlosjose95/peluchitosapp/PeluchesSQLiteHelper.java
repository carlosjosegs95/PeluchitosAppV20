package com.example.carlosjose95.peluchitosapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PeluchesSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE peluches (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad TEXT, precio TEXT)";

    public PeluchesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS peluches");
        db.execSQL(sqlCreate);
    }
}
