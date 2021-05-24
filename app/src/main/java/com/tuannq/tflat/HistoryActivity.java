package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.Word;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<Word> arrW;
    WordListViewAdapter wordListViewAdapter;
    ListView listWords;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        topAppBar = findViewById(R.id.historyBack);
        topAppBar.setTitle("History");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistoryActivity.this,TabActivity.class);
                startActivity(intent);
                HistoryActivity.this.finish();
            }
        });
        String str = getIntent().getStringExtra("arrW");
        Gson g = new Gson();
        Type listTye = new TypeToken<ArrayList<Word> >(){}.getType();
        arrW = g.fromJson(str, listTye);

        wordListViewAdapter = new WordListViewAdapter(arrW);
        listWords = findViewById(R.id.listWords);
        listWords.setAdapter(wordListViewAdapter);
    }
}