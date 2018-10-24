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
import com.example.p1024.bean.NewsTuijian;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class TuijianAdapter extends RecyclerView.Adapter<TuijianAdapter.tuiHolder>{
    private Context context;
    private List<NewsTuijian.DataBean.TuijianBean.ListBeanX> list;
    public TuijianAdapter(Context context, List<NewsTuijian.DataBean.TuijianBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public tuiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuijian_item, null);
        tuiHolder holder = new tuiHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull tuiHolder holder, int position) {
        holder.name.setText(list.get(position).getTitle());
        String[] split = list.get(position).getImages().split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.simp.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class tuiHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final SimpleDraweeView simp;
        private final TextView name;

        public tuiHolder(View itemView) {
            super(itemView);
            simp = itemView.findViewById(R.id.tui_simp);
            name = itemView.findViewById(R.id.tui_name);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            onitemclick.onclick(position);
        }
    }


    //定义详情点击的接口回调
    private onitemclick onitemclick;
    public interface onitemclick{
        void onclick(int position);
    }
    public void setOnitemclick(TuijianAdapter.onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }
}
