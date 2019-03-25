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
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winhex.wys.wys.bean.lateralBean;
import com.winhex.wys.wys.R;

import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.datails.Datails;
import com.yuyh.easyadapter.helper.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class LateralAdapter extends RecyclerView.Adapter<LateralAdapter.ViewHolder> implements RecyclerView.OnChildAttachStateChangeListener {
    List<lateralBean> list=new ArrayList<>();
    Context context;
    private  View.OnClickListener mItemClickListener;
    private View.OnLongClickListener mItemLongClickListener = null;
    int a;
    public  LateralAdapter(List<lateralBean> list, Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        if (mItemClickListener != null){
            view.setOnClickListener(mItemClickListener);
        }
        if (mItemLongClickListener != null){
            view.setOnLongClickListener(mItemLongClickListener);
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView phtonimage;
        ImageView headimage;
        Activity activity;
        public ViewHolder(View v){
            super(v);
            textView=v.findViewById(R.id.lateral_title);
            phtonimage=v.findViewById(R.id.lateral_iamge);
            headimage=v.findViewById(R.id.lateral_head);
            
        }
        
    }
    public  static interface OnItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_lateral,viewGroup,false);

        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(context,"我被点击了"+i);
                Intent intent=new Intent(context, Datails.class);
                intent.putExtra("urls",list.get(i).getImage());
                context.startActivity(intent);


            }
        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.textView.setText(list.get(i).getContent());
        String[] arr = list.get(i).getImage().split(",");
        Glide.with(context).load(arr[0]).into(viewHolder.phtonimage);
        viewHolder.headimage.setImageResource(list.get(i).getHead_portrait());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
