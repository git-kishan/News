package com.example.notebook.news_with_tab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataExtractor {


    public static List<News> extractor(String response)
    {
        if(response==null)
            return null;
        try {
            List<News> list=new ArrayList<>();
            JSONObject root=new JSONObject(response);
            JSONArray rootArray=root.getJSONArray("articles");
            for(int i=0;i<rootArray.length();i++)
            {
                JSONObject rootObject=rootArray.getJSONObject(i);
                String title=rootObject.getString("title");
                String imageUrl=rootObject.getString("urlToImage");
                String description=rootObject.getString("description");
                String content=rootObject.getString("content");

                JSONObject source=rootObject.getJSONObject("source");
                String name=source.getString("name");


                News news=new News(title,imageUrl,description,content,name );
                list.add(news);
            }
            return list;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
