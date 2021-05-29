package com.tuannq.tflat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.TranslateParagraphHistory;

public class Fragment_ListParagraphHistory extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    ArrayList<TranslateParagraphHistory> list = new ArrayList<>();

    public Fragment_ListParagraphHistory() {
        list.add(new TranslateParagraphHistory("hello", "xin chao", "en","vi"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        TranslateParagraphHistory temp = new TranslateParagraphHistory("hello", "xin chao", "en", "vi");
//        list.add(temp);

        recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new TPHistoryAdapter(list);
        recyclerView.setAdapter(myAdapter);
    }

    public void notifyDataChanged(){
        myAdapter.notifyDataSetChanged();
    }
}