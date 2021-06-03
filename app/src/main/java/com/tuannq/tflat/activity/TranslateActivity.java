package com.tuannq.tflat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import com.tuannq.tflat.adapter.WordListViewAdapter;
import com.tuannq.tflat.control.CRUD;
import com.tuannq.tflat.control.DictionaryRequest;
import com.tuannq.tflat.Model.Word;
import com.tuannq.tflat.R;


public class TranslateActivity extends AppCompatActivity {
    TextView tvWord,tvMeaning;
    String url,word;
    MaterialToolbar topAppBar;
    CRUD crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        crud = new CRUD(this);
        tvWord = findViewById(R.id.tvWord);
        tvMeaning = findViewById(R.id.tvMeaning);
        word = getIntent().getStringExtra("word");
        tvWord.setText(word);
        url = dictionaryEntries(word);
        topAppBar = findViewById(R.id.topAppBar);
        DictionaryRequest dr = new DictionaryRequest(tvMeaning);
        dr.execute(url);
 //       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        topAppBar.setTitle(tvWord.getText().toString());
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(TranslateActivity.this, MainActivity.class);
//                startActivity(intent);
                TranslateActivity.this.finish();
            }
        });
        MenuItem fav= (MenuItem) topAppBar.getMenu().findItem(R.id.favorite);
        boolean isFavorite = crud.checkFavorite(word);

//        Kiem tra xem tu da co trong lich su chua va luu vao lich su
        if(crud.findWord(word).getWord().equals(word)){

        }else{
            crud.insertWord(new Word(1, word, " ", " "));
        }

        if(isFavorite==true){
            fav.setIcon(R.drawable.favorite);
            //Toast.makeText(TranslateActivity.this,"checked",Toast.LENGTH_SHORT).show();
        }
        else{
            fav.setIcon(R.drawable.no_favorite);
            //Toast.makeText(TranslateActivity.this,"unchecked",Toast.LENGTH_SHORT).show();
        }
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.favorite:
                        boolean mstate = item.isChecked();
                        if(mstate==false){
                            item.setChecked(!mstate);
                            item.setIcon(R.drawable.favorite);
                            Word temp =new Word();
                            temp.setWord(word);
                            crud.updateFavorite(temp,1);

                            Toast.makeText(TranslateActivity.this,"checked",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            item.setChecked(!mstate);
                            item.setIcon(R.drawable.no_favorite);
                            Word temp =new Word();
                            temp.setWord(word);
                            crud.updateFavorite(temp,0);
                            Toast.makeText(TranslateActivity.this,"unchecked",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.search:
                        Intent intent=new Intent(TranslateActivity.this, SearchActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });

    }
    private String dictionaryEntries(String word) {
        final String language = "en-gb";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id ;
    }

}