package com.example.notebook.news_with_tab;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class News implements Parcelable {

   private  String title;
   private String imageUrl;
   private String description;
   private String content;
   private String channel;

    public News() { }

    public News(String title,String imageUrl,String description,String content,String channel){
        this.title=title;
        this.imageUrl=imageUrl;
        this.description=description;
        this.content=content;
        this.channel=channel;
    }

    protected News(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        content = in.readString();
        channel = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(imageUrl);
        parcel.writeString(description);
        parcel.writeString(content);
        parcel.writeString(channel);
    }


}
