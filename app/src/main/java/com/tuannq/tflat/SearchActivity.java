package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;

public class SearchActivity extends AppCompatActivity {
    EditText editSearch;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editSearch = findViewById(R.id.editSearch);
        topAppBar =findViewById(R.id.topSearch);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,TabActivity.class);
                startActivity(intent);
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