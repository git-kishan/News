package com.example.notebook.news_with_tab;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView contentView,descriptionView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar=findViewById(R.id.toolbar);
        collapsingToolbarLayout=findViewById(R.id.collapsing);
        contentView=findViewById(R.id.news_content);
        toolbar.setTitle("News App");


        descriptionView=findViewById(R.id.news_description);
        imageView=findViewById(R.id.image_view);
        Intent intent=getIntent();

        String content=intent.getStringExtra("content");
        String description=intent.getStringExtra("description");
        String imageUrl=intent.getStringExtra("imageUrl");
        String channel=intent.getStringExtra("channel");
        if(content==null)
            content = "";
        if(description==null)
            description="";
        if(channel==null)
            channel=null;
        if(imageUrl==null)
            imageUrl=null;

        collapsingToolbarLayout.setTitle(channel);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        contentView.setText(content);
        descriptionView.setText(description);
        Picasso.with(this).load(imageUrl).into(imageView);



    }

}

