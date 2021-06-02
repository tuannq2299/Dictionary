package com.tuannq.tflat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import com.tuannq.tflat.control.CRUD;
import com.tuannq.tflat.Model.Word;
import com.tuannq.tflat.R;

public class SearchActivity extends AppCompatActivity {
    AutoCompleteTextView editSearch;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editSearch = findViewById(R.id.editSearch);
        topAppBar =findViewById(R.id.topSearch);
        CRUD crud = new CRUD(this);
        ArrayList<Word>words=crud.getAllWords();
        ArrayList<String> temp=new ArrayList<>();
        for(Word i:words){
            temp.add(i.getWord());
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,temp);
        editSearch.setAdapter(adapter);
        editSearch.setThreshold(1);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(SearchActivity.this, MainActivity.class);
//                startActivity(intent);
                SearchActivity.this.finish();
            }
        });
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.searchWord:
                        String t = editSearch.getText().toString().trim();
                        if(t.length()>0){
                            Intent intent = new Intent(SearchActivity.this, TranslateActivity.class);
                            intent.putExtra("word", t);
                            startActivity(intent);
                        }
                        break;
                }
                return false;
            }
        });
    }
}