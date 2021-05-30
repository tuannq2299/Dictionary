package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import Model.Word;

public class list extends AppCompatActivity {
    ArrayList<Word> arrI;
    WordListViewAdapter ListViewAdapter;
    ListView listI;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listI = findViewById(R.id.listI);
        topAppBar = findViewById(R.id.historyTop);
        topAppBar.setTitle("History");
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(list.this,TabActivity.class);
                startActivity(intent);
                list.this.finish();
            }
        });
        String str = getIntent().getStringExtra("arrW");
        arrI  = new ArrayList<>();
        Word dic = new Word(); dic.setWord("Dictionary");
        Word tran = new Word(); tran.setWord("Google Translate");
        arrI.add(dic);
        arrI.add(tran);
        ListViewAdapter = new WordListViewAdapter(arrI);
        listI.setAdapter(ListViewAdapter);

        listI.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(list.this, HistoryActivity.class);
                i.putExtra("key", arrI.get(position).getWord());
                startActivity(i);
            }
        });
    }
}