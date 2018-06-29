package com.example.abmoiz.myapplication2.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abmoiz.myapplication2.Interface.ItemClickListener;
import com.example.abmoiz.myapplication2.R;
import com.example.abmoiz.myapplication2.model.RSSobject;

class FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{



    public TextView txtTitle ,txtPubdate,txtContent;
    private ItemClickListener  itemclicklistener;

    public FeedViewHolder(View itemView) {
        super(itemView);
        txtTitle=(TextView) itemView.findViewById(R.id.txtTitle);
        txtPubdate=(TextView) itemView.findViewById(R.id.txtPubDate);
        txtContent=(TextView) itemView.findViewById(R.id.txtContent);


        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);



    }


    public void setItemclicklistener(ItemClickListener itemclicklistener) {
        this.itemclicklistener = itemclicklistener;
    }

    @Override
    public void onClick(View view) {
    itemclicklistener.OnClick(view,getAdapterPosition(),false);





    }

    @Override
    public boolean onLongClick(View view) {

        itemclicklistener.OnClick(view,getAdapterPosition(),true);
        return true;
    }
}

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{
    private RSSobject rssobject;
    private Context mContext;
    private LayoutInflater inflater;

    public FeedAdapter(RSSobject rssobject, Context mContext) {
        this.rssobject = rssobject;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemview=inflater.inflate(R.layout.row,parent,false);


        return new FeedViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

        holder.txtTitle.setText(rssobject.getItems().get(position).getTitle());
        holder.txtPubdate.setText(rssobject.getItems().get(position).getPubDate());
        holder.txtContent.setText(rssobject.getItems().get(position).getContent());

        holder.setItemclicklistener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position, Boolean isLongclick) {
                if(!isLongclick)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssobject.getItems().get(position).getLink()));
                    mContext.startActivity(browserIntent);
                }
            }


        });











    }

    @Override
    public int getItemCount() {
        return rssobject.items.size();
    }
}
