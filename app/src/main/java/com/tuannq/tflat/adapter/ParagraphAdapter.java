package com.tuannq.tflat.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuannq.tflat.R;

import java.util.ArrayList;
import com.tuannq.tflat.Model.TranslateParagraphHistory;


public class ParagraphAdapter extends BaseAdapter {
    ArrayList<TranslateParagraphHistory> paragraphs;

    public ParagraphAdapter(ArrayList<TranslateParagraphHistory> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public int getCount() {
        return paragraphs.size();
    }

    @Override
    public Object getItem(int position) {
        return this.paragraphs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null){
            view = View.inflate(parent.getContext(), R.layout.activity_one_word, null);
        }
        else view = convertView;

        TranslateParagraphHistory p = (TranslateParagraphHistory) getItem(position);
        ((TextView) view.findViewById(R.id.word)).setText(p.getInputParagraph());
        ((TextView) view.findViewById(R.id.mean)).setText(p.getInputLang()+" - " +p.getOutputLang());
        ((TextView) view.findViewById(R.id.examp)).setText(p.getOutputParagraph());

        return view;
    }
}
