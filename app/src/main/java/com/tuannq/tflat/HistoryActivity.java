package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

        topAppBar = findViewById(R.id.historyTop);
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


        //        Su kien onclick
        listWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("1", arrW.get(position).getExamp());
                if(!arrW.get(position).getExamp().equals(".")){
//                    Day sang EE
                    Intent i =new Intent(HistoryActivity.this, TranslateActivity.class);
                    i.putExtra("word", arrW.get(position).getWord());
                    startActivity(i);
                }
                else{
//                    Day sang Viet - ANH
//                    Intent i =new Intent(FavoriteActivity.this, TranslateActivity.class);
//                    i.putExtra("word", arrW.get(position).getWord());
//                    startActivity(i);
                    Toast.makeText(HistoryActivity.this, "Ben kia chua xu ly", Toast.LENGTH_LONG);
                }
            }
        });

//   Su kien xoa item
        listWords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CRUD db = new CRUD(HistoryActivity.this);
                if(db.deleteWord(arrW.get(position).getWord())){
                    Toast.makeText(HistoryActivity.this, "Deleted!", Toast.LENGTH_LONG).show();
                    arrW.remove(position);
                    wordListViewAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }
}