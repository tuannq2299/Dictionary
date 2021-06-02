package com.tuannq.tflat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.tuannq.tflat.Model.Present;
import com.tuannq.tflat.R;

public class PresentAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Present> presentsList;

    public PresentAdapter(Context context, int layout, List<Present> presentsList) {
        this.context = context;
        this.layout = layout;
        this.presentsList = presentsList;
    }

    @Override
    //hien thi so dong list View;
    public int getCount() {
        return presentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    //tra ve view tren moi dong
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        //anh xa vie
        TextView txtTen = (TextView) view.findViewById(R.id.ten);
        TextView txtMota = (TextView) view.findViewById(R.id.mota);
        Present present = presentsList.get(i);
        txtTen.setText(present.getTen());
        txtMota.setText(present.getMota());
        return view;
    }
}