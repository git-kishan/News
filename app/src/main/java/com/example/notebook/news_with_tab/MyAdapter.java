package com.example.notebook.news_with_tab;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   private List<News> newsList ;
    Context context;
    private LayoutInflater inflater;
    public MyAdapter(Context context, List<News> newsList)
    {
        this.context=context;
        this.newsList=newsList;
        inflater=LayoutInflater.from(context);

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=inflater.inflate(R.layout.card_sample,viewGroup,false );
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        News currentObject=newsList.get(i);
        holder.setData(currentObject);
        holder.listners();

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView image;
        CardView cardView;
        News object;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
            cardView=itemView.findViewById(R.id.card_view);
        }

        public void setData(News currentObject) {
            this.object=currentObject;
            this.title.setText(currentObject.getTitle());
            Picasso.with(context).load(currentObject.getImageUrl()).into(this.image);

        }

        public void listners() {
            image.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,DetailActivity.class);
            intent.putExtra("imageUrl", object.getImageUrl());
            intent.putExtra("content",object.getContent());
            intent.putExtra("description",object.getDescription() );
            intent.putExtra("channel",object.getChannel() );
            context.startActivity(intent);


        }
    }
}
