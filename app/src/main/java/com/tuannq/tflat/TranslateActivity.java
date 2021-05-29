package com.tuannq.tflat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import Model.Word;

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
                Intent intent=new Intent(TranslateActivity.this, MainActivity.class);
                startActivity(intent);
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
            Toast.makeText(TranslateActivity.this,"checked",Toast.LENGTH_SHORT).show();
        }
        else{
            fav.setIcon(R.drawable.no_favorite);
            Toast.makeText(TranslateActivity.this,"unchecked",Toast.LENGTH_SHORT).show();
        }
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.favorite:
                        boolean mstate = item.isChecked();
                        if(mstate==true){
                            item.setChecked(!mstate);
                            item.setIcon(R.drawable.favorite);
                            Word temp =new Word();
                            temp.setWord(word);
                            temp.setMean("mean");
                            temp.setExamp("example");
                            crud.updateFavorite(temp,1);
                           // Toast.makeText(TranslateActivity.this,"checked",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            item.setChecked(!mstate);
                            item.setIcon(R.drawable.no_favorite);
                            Word temp =new Word();
                            temp.setWord(word);
                            crud.updateFavorite(temp,0);
                            //Toast.makeText(TranslateActivity.this,"unchecked",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.search:
                        Intent intent=new Intent(TranslateActivity.this,SearchActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        // Toast.makeText(MainActivity.this,"selected",Toa)
//        switch (item.getItemId()){
//            case R.id.menuSetting:
//                Toast.makeText(TranslateActivity.this,"Setting selected",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menuSearch:
//                Toast.makeText(TranslateActivity.this,"Search selected",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menuExit:
//                TranslateActivity.this.finish();
//                break;
//            case android.R.id.home:
//                // todo: goto back activity from here
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
    private String dictionaryEntries(String word) {
        final String language = "en-gb";

//        final String fields = "defintions";
//        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id ;//+ "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

}