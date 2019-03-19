package com.winhex.wys.wys.Activity.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.winhex.wys.wys.R;
import com.winhex.wys.wys.Utils.ToastUtils;

import java.util.List;

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {
    List<Integer> images;
    List<String> titel;
    Context context;
    public ClassifyAdapter(Context context,List<String> titel,List<Integer> images){
        this.titel=titel;
        this.images=images;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classify_muen,viewGroup,false);
        final ViewHolder viewHolder=new ViewHolder(v);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(context,"点击了"+viewHolder.getItemId());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.t.setText(titel.get(i));
        viewHolder.image.setImageResource(images.get(i));

    }

    @Override
    public int getItemCount() {
        return titel.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
           TextView t;
           ImageView image;
            public  ViewHolder(View v){
               super(v);
               image=v.findViewById(R.id.classify_images);
               t=v.findViewById(R.id.classify_title);
            }
    }
}
