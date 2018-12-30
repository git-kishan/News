package com.example.notebook.news_with_tab;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class General extends Fragment {

    private static final String GENERAL ="general" ;
    ProgressBar progressBar;
    public String url="https://newsapi.org/v2/top-headlines?country=in&category=general&apiKey=19f205fc38d8416fb78a888b6a03619c";
    RecyclerView recyclerView ;
    LinearLayoutManager layoutManager;
    List<News> list = new ArrayList<>();

    public General() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      if(isInternetConnection()) {
          View view = inflater.inflate(R.layout.fragment_first, container, false);
          recyclerView = view.findViewById(R.id.recycler_view);
          progressBar = view.findViewById(R.id.progress_circular);
          if(savedInstanceState!=null)
          {
              list=savedInstanceState.getParcelableArrayList(GENERAL);
              MyAdapter adapter=new MyAdapter(getActivity(),list );
              layoutManager=new LinearLayoutManager(getActivity());
              layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
              recyclerView.setLayoutManager(layoutManager);
              recyclerView.setAdapter(adapter);
              progressBar.setVisibility(View.INVISIBLE);
          }
          else {
              MyAsyncTask task = new MyAsyncTask();
              task.execute(url);

          }
          return view;
      }
      else
          {
              return inflater.inflate(R.layout.no_internet,container,false );

      }
    }
    public  boolean isInternetConnection()
    {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(GENERAL, (ArrayList<? extends Parcelable>) list);
    }

    public class MyAsyncTask extends AsyncTask<String ,Void,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected  String doInBackground(String... strings   ) {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(strings[0]).build();
            Response response=null;
            try {
                response=client.newCall(request).execute();
                return response.body().string();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected  void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            list=DataExtractor.extractor(s);

            MyAdapter adapter=new MyAdapter(getActivity(),list );
            layoutManager=new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

    }

}
