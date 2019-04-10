package com.winhex.wys.wys.Activity.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winhex.wys.wys.Activity.ArticlActivity;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.bean.Articllistbean;
import com.winhex.wys.wys.bean.Homebean;
import com.winhex.wys.wys.bean.lateralBean;
import com.winhex.wys.wys.datails.Datails;

import java.util.ArrayList;
import java.util.List;

public class ArticlListAdapter extends RecyclerView.Adapter<ArticlListAdapter.ViewHolder> {
    List<Articllistbean> list=new ArrayList<>();
    Context context;
    private  View.OnClickListener mItemClickListener;
    private View.OnLongClickListener mItemLongClickListener = null;
    int a;
    public  ArticlListAdapter(List<Articllistbean> list, Context context){
        this.list=list;
        this.context=context;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView articlename_textview;
        ImageView mastergraph_image;
        TextView describes_textview ;

        public ViewHolder(View v){
            super(v);
            articlename_textview=v.findViewById(R.id.articlename);
            describes_textview=v.findViewById(R.id.describes);
            mastergraph_image=v.findViewById(R.id.mastergraph);

        }

    }

    @NonNull
    @Override
    public ArticlListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.articllist,viewGroup,false);

        final ArticlListAdapter.ViewHolder viewHolder=new ArticlListAdapter.ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.show(context,"点击了"+String.valueOf(viewHolder.getAdapterPosition()));
                Intent intent=new Intent(context, ArticlActivity.class);
                intent.putExtra("url",list.get(viewHolder.getAdapterPosition()).getUrl());
                intent.putExtra("Articlename",list.get(viewHolder.getAdapterPosition()).getArticlename());
                intent.putExtra("Aid",list.get(viewHolder.getAdapterPosition()).getAid());
                intent.putExtra("Calssfiy",list.get(viewHolder.getAdapterPosition()).getCalssfiy());
                intent.putExtra("Describes",list.get(viewHolder.getAdapterPosition()).getDescribes());
                intent.putExtra("Mastergraph",list.get(viewHolder.getAdapterPosition()).getMastergraph());
                context.startActivity(intent);


            }
        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlListAdapter.ViewHolder viewHolder, int i) {

        viewHolder.articlename_textview.setText(list.get(i).getArticlename());
        viewHolder.describes_textview.setText(list.get(i).getDescribes());

        Glide.with(context).load(list.get(i).getMastergraph()).into(viewHolder.mastergraph_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
