package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class gis extends Activity
{
	Button penduduk, kolega, kesehatan, home;
	String[] item_data_kesehatan;
	String[] data;
	ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e_gis);
		
		Intent i = getIntent();
		final String selected = i.getStringExtra("selected");
		//int cntChoice = i.getIntExtra("cntChoice", 0);
		//String[] array = selected.split("\n");
		
		if(selected != null)
		{
			Toast.makeText(gis.this, selected, Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this, "Tekan menu untuk memilih menu yang anda inginkan",Toast.LENGTH_SHORT).show();
		}
	}
		
	// Initiating Menu XML file (menu.xml) 
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    MenuInflater menuInflater = getMenuInflater();
	    menuInflater.inflate(R.layout.menu_gis, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
	  	Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
	   	switch (item.getItemId())
	    {
	    case R.id.penduduk:
	      	Intent penduduk = new Intent(gis.this, kependudukan.class);
	       	penduduk.putExtra("admin", admin);
	    	startActivity(penduduk);
	        return true;
	       
	    case R.id.kolega:
	       	Intent kolega = new Intent(gis.this, kolega.class);
	       	kolega.putExtra("admin", admin);
	    	startActivity(kolega);
	        return true; 
	            
	    case R.id.kesehatan:
	       	Intent kesehatan = new Intent(gis.this, Kesehatan.class);
	       	kesehatan.putExtra("admin", admin);
	    	startActivity(kesehatan);
	        return true;     
	            
	    case R.id.homePage:        	
	       	if(admin.equals("ADMIN"))
			{
				Intent home = new Intent (gis.this, homeAdmin.class);
				home.putExtra("admin", admin);
		    	startActivity(home);
			}
			else
			{
				Intent home = new Intent (gis.this, homeMember.class);
				home.putExtra("admin", admin);
		    	startActivity(home);
			}
	        return true;
	     
	   default:
	       return super.onOptionsItemSelected(item);
	    }
	 }
}
