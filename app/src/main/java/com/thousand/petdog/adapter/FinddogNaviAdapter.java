package com.thousand.petdog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thousand.petdog.R;
import com.thousand.petdog.bean.NavgationItem;

import java.util.List;

public class FinddogNaviAdapter extends RecyclerView.Adapter<FinddogNaviAdapter.MyViewHolder> {

    private Context mContext;
    private List<NavgationItem> mNavgationItemList;
    public FinddogNaviAdapter(Context context, List<NavgationItem> list) {
        this.mContext = context;
        this.mNavgationItemList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_find_navigation,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final NavgationItem navgationItem = mNavgationItemList.get(i);
        myViewHolder.imageView.setImageResource(navgationItem.getNavgationImg());
        myViewHolder.textView.setText(navgationItem.getNavgationName());
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,navgationItem.getNavgationName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNavgationItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_dogfind_naviname);
            imageView = itemView.findViewById(R.id.item_dogfind_naviimg);
        }
    }
}
