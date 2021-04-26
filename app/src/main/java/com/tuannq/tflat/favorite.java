package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.Word;

public class favorite extends AppCompatActivity {
    ArrayList<Word> arrW;
    WordListViewAdapter wordListViewAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        String str = getIntent().getStringExtra("arrW");
        Gson g = new Gson();
        Type listType = new TypeToken<ArrayList<Word> >(){}.getType();
        arrW = g.fromJson(str, listType);

        wordListViewAdapter = new WordListViewAdapter(arrW);
        listView = findViewById(R.id.listWords);
        listView.setAdapter(wordListViewAdapter);
    }
}