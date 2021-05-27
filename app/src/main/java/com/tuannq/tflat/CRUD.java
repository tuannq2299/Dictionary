package com.tuannq.tflat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import Model.Question;
import Model.Word;

public class CRUD {
    private SqliConnection database;
    public CRUD(Context c) {
        database = new SqliConnection(c, "tflat.sqlite", null, 1);
        database.queryData("CREATE TABLE IF NOT EXISTS words(id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255) UNIQUE, mean VARCHAR(255), examp VARCHAR(255))");
        database.queryData("CREATE TABLE IF NOT EXISTS favoriteWords(id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255) UNIQUE, mean VARCHAR(255), examp VARCHAR(255))");

        database.queryData("CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(255), a VARCHAR(255), b VARCHAR(255)" +
                ", c VARCHAR(255), d VARCHAR(255), rs VARCHAR(255))");
    }

//    question

//    Insert cau hoi vao csdl
    public boolean insertQuestion(Question q){
        try{
            String query = String.format("insert into questions values(null, '%s', '%s', '%s', '%s', '%s', '%s')",
                    q.getQuestion(), q.getA(), q.getB(), q.getC(), q.getD(), q.getRs());

            database.queryData(query);

        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
//    Tim cau hoi theo id
    public Question getQuestionById(int i){
        String query = "Select * from questions where id = "+i;
        Question q=null;

        try{
            Cursor c = database.getData(query);
            if(c.moveToNext()){
                q = new Question(c.getInt(0),c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5),
                        c.getString(6));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return q;
    }
//    Lay tat ca cau hoi
    public ArrayList<Question> getAllQuestion(){
        String query = "Select * from questions";
        ArrayList<Question> q=null;

        try{
            Cursor c = database.getData(query);
            while (c.moveToNext()){
                Question a = new Question(c.getInt(0),c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5),
                        c.getString(6));
                q.add(a);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return q;
    }



//    word
    public void insertWord(Word w){
            String query = String.format( "insert into words values(null, '%s', '%s', '%s')", w.getWord(), w.getMean(), w.getExamp());
            database.queryData(query);
    }

    public void insertFavoriteWord(Word w){
        String query = String.format( "insert into favoriteWords values(null,' %s', '%s', '%s')", w.getWord(), w.getMean(), w.getExamp());
        database.queryData(query);
    }
    public Word findWord(String word){
        Word w = new Word(1,"","","");
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
        word=word.trim();
        String query = "select * from favoriteWords where word LIKE '%"+word+"%';";
        Cursor cursor = database.getData(query);
        if(cursor.moveToNext()) return true;
        return false;
    }
    public void updateFavorite(Word word,int i){
        String w= word.getWord();
        if(i==0){
            String query = "delete from favoriteWords where word LIKE '%"+w+"%';";
            database.queryData(query);
        }
        else{
            String query = String.format( "insert into favoriteWords values(null,' %s', '%s', '%s')", w, word.getMean(), word.getExamp());
            database.queryData(query);
        }

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
        String query = "select * from favoriteWords;";
        Cursor cursor = database.getData(query);
        while(cursor.moveToNext()){
            arr.add(new Word(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3)));
        }
        return arr;
    }

    public boolean deleteWord(String str){
        String query = "delete from words where word like '"+str+"'";
        try{
            database.queryData(query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteFavoriteWord(String str){
        String query = "delete from favoriteWords where word like '"+str+"'";
        try{
            database.queryData(query);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

//    CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(255), a VARCHAR(255), b VARCHAR(255)), c VARCHAR(255)), d VARCHAR(255), rs VARCHAR(255))