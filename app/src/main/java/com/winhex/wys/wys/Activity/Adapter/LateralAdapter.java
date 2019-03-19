package com.winhex.wys.wys.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class LateralAdapter extends RecyclerView.Adapter<LateralAdapter.ViewHolder>  {
    List<String> list=new ArrayList<>();
    Context context;
    public  LateralAdapter(List<String> list,Context context){
        this.list=list;
        this.context=context;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View v){
            super(v);
            textView=v.findViewById(R.id.lateral_title);
        }
        
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_lateral,viewGroup,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(context,"我被点击了"+viewHolder.textView.getText().toString());
            }
        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(list.get(i));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
