package com.halamanlogin;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabLayoutActivity extends TabActivity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview);
        
        TabHost tabHost = getTabHost();
        
        // Tab for All Files
        TabSpec allspec = tabHost.newTabSpec("All");
        allspec.setIndicator("All", getResources().getDrawable(R.drawable.icon_all_tab));
        Intent allIntent = new Intent(this, KnowledgeAll.class);
        allspec.setContent(allIntent);
        
        // Tab for News
        TabSpec newsspec = tabHost.newTabSpec("News");
        newsspec.setIndicator("News", getResources().getDrawable(R.drawable.icon_news_tab));
        Intent newsIntent = new Intent(this, KnowledgeNews.class); //activity news
        newsspec.setContent(newsIntent);
        
        // Tab for Tutorial
        TabSpec tutorialspec = tabHost.newTabSpec("Tutorial");
        // setting Title and Icon for the Tab
        tutorialspec.setIndicator("Tutorial", getResources().getDrawable(R.drawable.icon_tutorial_tab));
        Intent tutorialIntent = new Intent(this, KnowledgeTutorial.class);
        tutorialspec.setContent(tutorialIntent);
        
        // Tab for e-library
        TabSpec libraryspec = tabHost.newTabSpec("e-Library");
        libraryspec.setIndicator("e-Library", getResources().getDrawable(R.drawable.icon_library_tab));
        Intent libraryIntent = new Intent(this, KnowledgeLibrary.class);
        libraryspec.setContent(libraryIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(allspec); // Adding all tab
        tabHost.addTab(newsspec); // Adding news tab
        tabHost.addTab(tutorialspec); // Adding tutorial tab
        tabHost.addTab(libraryspec); // Adding library tab
        
    }
}