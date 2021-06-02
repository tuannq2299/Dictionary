package com.tuannq.tflat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tuannq.tflat.activity.PresentActivity;
import com.tuannq.tflat.R;

public class Fragment_Home2 extends Fragment {
    Button btnTOEIC;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2,container,false);;
        btnTOEIC = view.findViewById(R.id.btnToeic);
        btnTOEIC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickQues(v);
            }
        });
        return view;
    }

    public void onClickQues(View v){
        Intent i = new Intent(getActivity(), PresentActivity.class);
        startActivity(i);
    }
}
