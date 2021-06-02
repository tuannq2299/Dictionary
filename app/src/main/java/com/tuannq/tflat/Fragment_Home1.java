package com.tuannq.tflat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;

import Model.Word;

public class Fragment_Home1 extends Fragment {
    ImageView ivSearch;
    AutoCompleteTextView etSearch;
    TextView tvBanner;
    Button btnHistory,btnYW,btnVA;
    ArrayList<Word> words;
    ArrayList<Word> yourWords;
    TabLayout tabLayout;
    ViewPager viewPager;
    CRUD crud;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home1,container,false);
        ivSearch=view.findViewById(R.id.ivSearch_1);
        etSearch=view.findViewById(R.id.etSearch);
        tvBanner=view.findViewById(R.id.tvBanner);
        btnHistory=view.findViewById(R.id.btnHistory);
        btnYW=view.findViewById(R.id.btnYW);
        btnVA=view.findViewById(R.id.btnVA);

        ivSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                requestApiButton(v);
            }
        });
        btnVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestTranslateVi(v);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHistory(v);
            }
        });

        btnYW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickYW(v);
            }
        });
        crud = new CRUD(getActivity());
//        crud.insertFavoriteWord(new Word(1,"turtle","Con rùa", "."));
//        crud.insertFavoriteWord(new Word(1,"horse","Con ngựa", "."));
//        crud.insertFavoriteWord(new Word(1,"Tiger","Con hổ", "."));
//        crud.insertFavoriteWord(new Word(1,"Lion","Con sư tử", "."));
//        crud.insertFavoriteWord(new Word(1,"duck","Con vịt", "."));
        words = crud.getAllWords();
        ArrayList<String>temp=new ArrayList<>();
        for(Word i:words){
            temp.add(i.getWord());
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_activated_1,temp);
        etSearch.setAdapter(adapter);
        etSearch.setThreshold(1);
        yourWords = crud.getFavoriteWords();
        return view;
    }
    public void requestApiButton(View v){
        if(etSearch.getText().toString().length()>0) {

            String word = etSearch.getText().toString().trim();
            Intent intent = new Intent(getActivity(), TranslateActivity.class);
            intent.putExtra("word", word);
            startActivity(intent);
        }
    }
    public void requestTranslateVi(View v){
        Intent intent = new Intent(getActivity(), TranslateParagraphActivity.class);
        intent.putExtra("word", "");
        intent.putExtra("mean", "");
        startActivity(intent);
    }

    public void onClickHistory(View v){
        Intent intent = new Intent(getActivity(), list.class);
        String str = (new Gson()).toJson(words);
        intent.putExtra("arrW", str);

        startActivity(intent);
    }

    public void onClickYW(View v){
        Intent intent= new Intent(getActivity(), FavoriteActivity.class);
        String str = (new Gson()).toJson(yourWords);
        intent.putExtra("arrW", str);
        startActivity(intent);
    }
}
