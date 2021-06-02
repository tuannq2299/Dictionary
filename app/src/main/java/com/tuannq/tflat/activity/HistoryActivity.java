package com.tuannq.tflat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.tuannq.tflat.control.CRUD;
import com.tuannq.tflat.R;
import com.tuannq.tflat.adapter.WordListViewAdapter;
import com.tuannq.tflat.adapter.ParagraphAdapter;

import java.util.ArrayList;

import com.tuannq.tflat.Model.TranslateParagraphHistory;
import com.tuannq.tflat.Model.Word;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<Word> arrW;
    CRUD crud;
    WordListViewAdapter wordListViewAdapter;
    ListView listWords;
    MaterialToolbar topAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        topAppBar = findViewById(R.id.historyTop);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
                HistoryActivity.this.finish();
            }
        });

        crud = new CRUD(HistoryActivity.this);
        ArrayList<TranslateParagraphHistory> arrP = crud.getAllParagraph();
        String str = getIntent().getStringExtra("key");
        topAppBar.setTitle(str);

        if(str.equals("Dictionary")){
            arrW = crud.getAllWords();
            wordListViewAdapter = new WordListViewAdapter(arrW);
            listWords = findViewById(R.id.listWords);
            listWords.setAdapter(wordListViewAdapter);


            //        Su kien onclick
            listWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i =new Intent(HistoryActivity.this, TranslateActivity.class);
                        i.putExtra("word", arrW.get(position).getWord());
                        startActivity(i);
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
        else{

            Log.d("1", arrP.size()+"zzzzz");
            ParagraphAdapter paragraphAdapter = new ParagraphAdapter(arrP);
            listWords = findViewById(R.id.listWords);
            listWords.setAdapter(paragraphAdapter);
            //        Su kien onclick
            listWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i =new Intent(HistoryActivity.this, TranslateParagraphActivity.class);
                    i.putExtra("word", arrP.get(position).getInputParagraph());
                    i.putExtra("mean", arrP.get(position).getOutputParagraph());
//                    if(){
                        startActivity(i);
//                    }
                }
            });

            //   Su kien xoa item
            listWords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    if(crud.delete(arrP.get(position).getInputParagraph())){
                        Toast.makeText(HistoryActivity.this, "Deleted!", Toast.LENGTH_LONG).show();
                        arrP.remove(position);
                        paragraphAdapter.notifyDataSetChanged();
                    }
                    return true;
                }
            });
        }




    }
}