package com.halamanlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class gis extends Activity
{
	Button penduduk, kolega, kesehatan, home;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.peta);
		
		Toast.makeText(gis.this, "Tekan menu untuk memilih menu yang anda inginkan",Toast.LENGTH_SHORT).show();
	}
		
	// Initiating Menu XML file (menu.xml) 
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
	    MenuInflater menuInflater = getMenuInflater();
	    menuInflater.inflate(R.layout.menu_gis, menu);
	    return true;
	}
	 
	/**
	  * Event Handling for Individual menu item selected
	  * Identify single menu item by it's id
	  */
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
				//ketika button Login di tekan, maka akan kembali ke halaman utama
				Intent home = new Intent (gis.this, homeAdmin.class);
				home.putExtra("admin", admin);
		    	startActivity(home);
			}
			else
			{
				//ketika button Login di tekan, maka akan kembali ke halaman utama
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
