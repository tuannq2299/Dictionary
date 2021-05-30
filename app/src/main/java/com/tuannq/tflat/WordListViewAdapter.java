package com.tuannq.tflat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Model.Word;

public class WordListViewAdapter extends BaseAdapter {
    final ArrayList<Word> arrW;

    public WordListViewAdapter(ArrayList<Word> arrW) {
        this.arrW = arrW;
    }

    @Override
    public int getCount() {
        return arrW.size();
    }

    @Override
    public Object getItem(int position) {
        return arrW.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View viewWord;
         if(convertView==null){
             viewWord = View.inflate(parent.getContext(), R.layout.activity_one_word, null);
         }
         else viewWord = convertView;

         Word w = (Word) getItem(position);
        ((TextView) viewWord.findViewById(R.id.word)).setText(String.format(w.getWord()));
        ((TextView) viewWord.findViewById(R.id.mean)).setText("");
        if(!w.getExamp().equals(".")){
            ((TextView) viewWord.findViewById(R.id.examp)).setText("");
        }
        return viewWord;


    }
}
