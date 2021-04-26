package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.logging.Logger;

import Model.Word;

public class MainActivity extends AppCompatActivity {

    ImageView ivSearch;
    EditText etSearch;
    TextView tvBanner;
    Button btnHistory,btnYW,btnVA;
    ArrayList<Word> words;
    ArrayList<Word> yourWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivSearch=findViewById(R.id.ivSearch);
        etSearch=findViewById(R.id.etSearch);
        tvBanner=findViewById(R.id.tvBanner);
        btnHistory=findViewById(R.id.btnHistory);
        btnYW=findViewById(R.id.btnYW);
        btnVA=findViewById(R.id.btnVA);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestApiButton(v);
            }
        });
        btnVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTranslateVi(v);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHistory(v);
            }
        });

        btnYW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickYW(v);
            }
        });

        //
        CRUD crud = new CRUD(this);
//        crud.insertFavoriteWord(new Word(1,"turtle","Con rùa", "."));
//        crud.insertFavoriteWord(new Word(1,"horse","Con ngựa", "."));
//        crud.insertFavoriteWord(new Word(1,"Tiger","Con hổ", "."));
//        crud.insertFavoriteWord(new Word(1,"Lion","Con sư tử", "."));
//        crud.insertFavoriteWord(new Word(1,"duck","Con vịt", "."));
//        SqliConnection database = new SqliConnection(this, "tflat.sqlit", null, 1);
//        database.queryData("CREATE TABLE IF NOT EXISTS words(id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255), mean VARCHAR(255), isFavorite BOOLEAN)");
//        database.queryData("drop table words;");
//        database.queryData("INSERT INTO words VALUES(null, 'hello', 'Xin chào', false)");
//        database.queryData("INSERT INTO words VALUES(null, 'hello', 'Xin chào', false)");
        //
         words = crud.getAllWords();
        yourWords = crud.getFavoriteWords();
        Log.d("W", words.get(0).getMean()+words.size());
    }
    public void requestApiButton(View v){
        if(etSearch.getText().toString().length()>0) {

            String word = etSearch.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
            intent.putExtra("word", word);
            startActivity(intent);

        }
    }
    public void requestTranslateVi(View v){
            Intent intent = new Intent(MainActivity.this, VnActivity.class);
            startActivity(intent);
    }

    public void onClickHistory(View v){
        Intent intent = new Intent(MainActivity.this, history.class);
        String str = (new Gson()).toJson(words);
        intent.putExtra("arrW", str);

        startActivity(intent);
    }

    public void onClickYW(View v){
        Intent intent= new Intent(MainActivity.this, favorite.class);
        String str = (new Gson()).toJson(yourWords);
        intent.putExtra("arrW", str);
        startActivity(intent);
    }
}