package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeAdmin extends Activity
{
	Button gis, report, knowledge, communication, logout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homeadmin);
		
		Intent i = getIntent();
		String username = i.getStringExtra("username");
		
		if(username != null)
		{
			Toast.makeText(getBaseContext(), "Selamat Datang " + username, Toast.LENGTH_SHORT).show();
		}
		
		gis = (Button) findViewById(R.id.gis);
		gis.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_gis = new Intent(HomeAdmin.this, Gis.class);
				start_gis.putExtra("admin", admin);
		    	startActivity(start_gis);
	        }
		});
		
		report = (Button) findViewById(R.id.report);
		report.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_report = new Intent(HomeAdmin.this, Report.class);
				start_report.putExtra("admin", admin);
		    	startActivity(start_report);
	        }
		});
		
		knowledge = (Button) findViewById(R.id.listfile);
		knowledge.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent list_file = new Intent(HomeAdmin.this, TabLayoutActivity.class);
				list_file.putExtra("admin", admin);
		    	startActivity(list_file);
	        }
		});
		
		communication = (Button) findViewById(R.id.chat);
		communication.setOnClickListener(new Button.OnClickListener()
		{			
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_chat = new Intent(HomeAdmin.this, Chat.class);
				start_chat.putExtra("admin", admin);
		    	startActivity(start_chat);
	        }
		});
		
		logout = (Button) findViewById(R.id.keluar);
		logout.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				String admin = "FALSE";

				Intent out = new Intent (HomeAdmin.this, LoginMain.class);
				out.putExtra("admin", admin);
				startActivity(out);
				Toast.makeText(HomeAdmin.this, "Terima Kasih",Toast.LENGTH_SHORT).show();
	        }		
		});
	}
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}
