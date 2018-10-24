package com.example.p1024.mvp.ui.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p1024.R;
import com.example.p1024.bean.NewSousuo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SouAdapter extends RecyclerView.Adapter<SouAdapter.souHolder>{
    private Context context;
    private List<NewSousuo.DataBean> list;
    public SouAdapter(Context context, List<NewSousuo.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public souHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sou_item, null);
        souHolder holder = new souHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull souHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        String[] split = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class souHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final SimpleDraweeView simp;

        public souHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sou_title);
            simp = itemView.findViewById(R.id.sou_simp);
        }
    }
}
