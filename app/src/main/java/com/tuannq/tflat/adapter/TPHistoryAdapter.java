package com.tuannq.tflat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.tuannq.tflat.Model.TranslateParagraphHistory;
import com.tuannq.tflat.R;

// tạo adapter riêng cho custom recyclerView
public class TPHistoryAdapter extends RecyclerView.Adapter<TPHistoryAdapter.ViewHolder> {
    ArrayList<TranslateParagraphHistory> list;

    public TPHistoryAdapter (ArrayList<TranslateParagraphHistory> list){
        this.list = list;
    }
    // custom viewHolder, itemView là từng dòng trong list
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvInputParagraph, tvOutputParagraph, tvInputLang, tvOutputLang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInputParagraph = itemView.findViewById(R.id.tvInputParagraph);
            tvOutputParagraph = itemView.findViewById(R.id.tvOutputParagraph);
            tvInputLang = itemView.findViewById(R.id.tvInputLang);
            tvOutputLang = itemView.findViewById(R.id.tvOutputLang);

        }

    }

    // làm mới mỗi dòng bằng layout item
    @NonNull
    @Override
    public TPHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items_paragraph_history, viewGroup, false);
        return new ViewHolder(v);
    }

    // settext cho các thành phần thay đổi của mỗi dòng, i là index tăng dần ứng với duyệt toàn bộ list
    @Override
    public void onBindViewHolder(@NonNull TPHistoryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvInputParagraph.setText(list.get(i).getInputParagraph());
        viewHolder.tvOutputParagraph.setText(list.get(i).getOutputParagraph());
        viewHolder.tvInputLang.setText(list.get(i).getInputLang());
        viewHolder.tvOutputLang.setText(list.get(i).getOutputLang());
    }

    // Duyệt toàn bộ phần tử của list
    @Override
    public int getItemCount() {
        return list.size();
    }
}
