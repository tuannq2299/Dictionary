package com.tuannq.tflat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

import androidx.annotation.Nullable;

public class SqliConnection extends SQLiteOpenHelper {
    public SqliConnection(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//    private static final String DbName = "tflat";
//    private static final int ver = 1;
//
//    public SqliConnection(Context context) {
//        super(context, DbName, null, ver);
//    }

    public void queryData(String query){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(query);
    }

    public Cursor getData(String query){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(query, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
