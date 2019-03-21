package com.winhex.wys.wys.Activity.Release;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;
import com.winhex.wys.wys.bean.lateralBean;

import java.util.ArrayList;
import java.util.List;

public class PhtonAdapter extends RecyclerView.Adapter<PhtonAdapter.ViewHolder>  {
    ImageView select_phton;
    LinearLayout Exhibition_phton;
    Context context;
    List<String> listimage=new ArrayList<>();
    public PhtonAdapter( Context context,List<String> listimage){
        
        this.listimage=listimage;
        this.context=context;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
       
        ImageView phtonimage;
       
        public ViewHolder(View v){
            super(v);
            phtonimage=v.findViewById(R.id.list_phton);
        }
        
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upload_phton,viewGroup,false);
        select_phton=viewGroup.findViewById(R.id.select_phton);
        Exhibition_phton=viewGroup.findViewById(R.id.Exhibition_phton);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        
            Glide.with(context).load(listimage.get(i)).into(viewHolder.phtonimage);
        
     
    }

    @Override
    public int getItemCount() {
        return listimage.size();
    }

}
