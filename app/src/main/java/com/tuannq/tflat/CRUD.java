package com.tuannq.tflat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Model.Word;

public class CRUD {
    private SqliConnection database;
    public CRUD(Context c) {
        database = new SqliConnection(c, "tflat.sqlite", null, 1);
        database.queryData("CREATE TABLE IF NOT EXISTS words(id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255) UNIQUE, mean VARCHAR(255), examp VARCHAR(255))");
        database.queryData("CREATE TABLE IF NOT EXISTS favoriteWords(id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255) UNIQUE, mean VARCHAR(255), examp VARCHAR(255))");
    }

    public void insertWord(Word w){
            String query = String.format( "insert into words values(null, '%s', '%s', '%s')", w.getWord(), w.getMean(), w.getExamp());
            database.queryData(query);
    }

    public void insertFavoriteWord(Word w){
        String query = String.format( "insert into favoriteWords values(null, %s, %s, %s)", w.getWord(), w.getMean(), w.getExamp());
        database.queryData(query);
    }
    public Word findWord(String word){
        Word w = new Word();
        w.setMean("");
        w.setWord("");
        w.setExamp("");
        String query = "select * from words where word = "+"'"+word+"';";
        Cursor cursor = database.getData(query);
        if(cursor.moveToNext()){
            w.setWord(cursor.getString(1));
            w.setMean(cursor.getString(2));
            w.setExamp(cursor.getString(3));
        }
        return w;
    }

    public boolean checkFavorite(String word){
        String query = "select * from favoriteWords where word = "+"'"+word+"';";
        Cursor cursor = database.getData(query);
        if(cursor.moveToNext()) return true;
        return false;
    }

    public ArrayList<Word> getAllWords(){
        ArrayList<Word> arr = new ArrayList<>();
        String query = "select * from words;";
        Cursor cursor = database.getData(query);
        while(cursor.moveToNext()){
            arr.add(new Word(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
        }
        return arr;
    }

    public ArrayList<Word> getFavoriteWords(){
        ArrayList<Word> arr = new ArrayList<>();
        String query = "select * from favoriteWords where isFavorite = 1;";
        Cursor cursor = database.getData(query);
        while(cursor.moveToNext()){
            arr.add(new Word(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
        }
        return arr;
    }

}
