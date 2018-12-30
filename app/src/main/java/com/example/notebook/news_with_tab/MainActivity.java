package com.example.notebook.news_with_tab;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        toolbar.setTitle("News App");
    }


    public void setUpViewPager(ViewPager viewPager){

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Buisness(),"Buisness" );
        adapter.addFragment(new Entertainment(),"Entertainment" );
        adapter.addFragment(new General(),"General" );
        adapter.addFragment(new Health(),"Health" );
        adapter.addFragment(new Science(),"Science" );
        adapter.addFragment(new Sports(),"sports" );
        adapter.addFragment(new Technology(),"Technology");
        viewPager.setAdapter(adapter);

    }

}
