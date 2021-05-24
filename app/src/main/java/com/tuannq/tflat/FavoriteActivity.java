package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Model.Word;

public class FavoriteActivity extends AppCompatActivity {
    ArrayList<Word> arrW;
    WordListViewAdapter wordListViewAdapter;
    ListView listView;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        topAppBar = findViewById(R.id.favoriteTop);
        topAppBar.setTitle("Favorite");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FavoriteActivity.this,TabActivity.class);
                startActivity(intent);
                FavoriteActivity.this.finish();
            }
        });
        String str = getIntent().getStringExtra("arrW");
        Gson g = new Gson();
        Type listType = new TypeToken<ArrayList<Word> >(){}.getType();
        arrW = g.fromJson(str, listType);

        wordListViewAdapter = new WordListViewAdapter(arrW);
        listView = findViewById(R.id.listWords);
        listView.setAdapter(wordListViewAdapter);
    }
}