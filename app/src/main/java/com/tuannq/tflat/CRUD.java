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
//       database.queryData("Drop table questions");
        database.queryData("CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(255) UNIQUE, a VARCHAR(255), b VARCHAR(255)" +
                ", c VARCHAR(255), d VARCHAR(255), rs VARCHAR(255), _group VARCHAR(255))");
        //database.queryData("insert into questions values(null, 'question', 'a', 'b','c','d', 'c','thtd')");
        //database.queryData("INSERT INTO questions VALUES(null, '1. The wedding party________ held at the Rex Hotel.', 'A. is', 'B. is being', 'C. will be', 'D. is going to be', 'C. will be', '1')");
    }

//    question

//    Insert cau hoi vao csdl
    public boolean insertQuestion(Question q, String group){
        try{
            String query = String.format("insert into questions values(null, '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    q.getQuestion(), q.getA(), q.getB(), q.getC(), q.getD(), q.getRs(), group);
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
        ArrayList<Question> q=new ArrayList<>();
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
//    Lay cau hoi theo thi
public ArrayList<Question> getAllQuestionByType(String group){
    String query = "Select * from questions where _group like '%"+group+"'";
    ArrayList<Question> q=new ArrayList<>();
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

    public void insertData(String str){
        //database.queryData(


//                "INSERT INTO questions VALUES(null, '3. Hurry! The train________ I don't want to miss it.', 'A. comes', 'B. is coming', 'C. came', 'D. has come', 'B. is coming', '1');\n" +
//                "INSERT INTO questions VALUES(null, '4. Angelina Jolie is a famous actress. She ________ in several film.', 'A. appears', 'B. is appearing', 'C. appeared', 'D. has appeared', 'D-. has appeared', '1');\n" +
  //              "INSERT INTO questions VALUES(null, '5. I saw Maggie at the party. She ________ in several film.', 'A. wears', 'B. wore', 'C. was wearing', 'D. has worn', 'C. was wearing', '1');\n" +
 //               "INSERT INTO questions VALUES(null, '6. What time________ the next train leave?', 'A. does', 'B. will', 'C. shall', 'D. would', 'A. does', '2');\n" +
   //             "INSERT INTO questions VALUES(null, '7. Monica________ with her sister at the moment until she finds a flat.', 'A. stays', 'B. is staying', 'C. will stay', 'D. is going to stay', 'B. is staying', '2');\n" +
   //             "INSERT INTO questions VALUES(null, '8. After I________ lunch, I looked for my bag.', 'A. had', 'B. had had', 'C. have has', 'D. have had', 'B. had had', '2');\n" +
    //            "INSERT INTO questions VALUES(null, '9. By the end of next year, George________ English for 2 years.', 'A. will have learned', 'B. will learn', 'C. has leaned', 'D. would learn', 'A. will have learned', '2');\n" +
     //           "INSERT INTO questions VALUES(null, '10. The man got out of the car,_______ round to the back and opened the boot.', 'A. walking', 'B. walked', 'C. walks', 'D. walk', 'B. walked', '2');\n" +
      //          "INSERT INTO questions VALUES(null, '11. For several years his ambition ________ to be a pilot.', 'A. is', 'B. has been', 'C. was', 'D. had been', 'B. has been', '3');\n" +
       //         "INSERT INTO questions VALUES(null, '12. Henry________ into the restaurant when the writer was having dinner.', 'A. was going', 'B. went', 'C. has gone', 'D. did go', 'B. went', '3');\n" +
        //        "INSERT INTO questions VALUES(null, '13. He will take the dog out for a walk as soon as he ________ dinner.', 'A. finish', 'B. finishes', 'C. will finish', 'D. shall have finished', 'B. finishes', '3');\n" +
         //       "INSERT INTO questions VALUES(null, '14. Before you asked, the letter________', 'A. was written', 'B. had been written', 'C. had written', 'D. has been written', 'B. had been written', '3');\n" +
          //      "INSERT INTO questions VALUES(null, '15. She ________ English at RMIT these days.', 'A. studies', 'B. is studying', 'C. will study', 'D. is gong to study', 'B. is studying', '3');");
    }

}

//    CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY AUTOINCREMENT, question VARCHAR(255), a VARCHAR(255), b VARCHAR(255)), c VARCHAR(255)), d VARCHAR(255), rs VARCHAR(255))