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
import com.example.p1024.bean.NewsJiugongge;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class JiugonggeAdapter extends RecyclerView.Adapter<JiugonggeAdapter.jiuHolder>{
    private Context context;
    private List<NewsJiugongge.DataBean> list;
    public JiugonggeAdapter(Context context, List<NewsJiugongge.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public jiuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiugongge_item, null);
        jiuHolder holder = new jiuHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull jiuHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        String[] split = list.get(position).getIcon().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class jiuHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView simp;
        private final TextView name;

        public jiuHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.jiu_simp);
            name = itemView.findViewById(R.id.jiu_name);
        }
    }
}
