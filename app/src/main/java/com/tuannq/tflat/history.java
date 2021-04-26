package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.Word;

public class history extends AppCompatActivity {
    ArrayList<Word> arrW;
    WordListViewAdapter wordListViewAdapter;
    ListView listWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        String str = getIntent().getStringExtra("arrW");
        Gson g = new Gson();
        Type listTye = new TypeToken<ArrayList<Word> >(){}.getType();
        arrW = g.fromJson(str, listTye);

        wordListViewAdapter = new WordListViewAdapter(arrW);
        listWords = findViewById(R.id.listWords);
        listWords.setAdapter(wordListViewAdapter);
    }
}