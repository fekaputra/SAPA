package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class homeMember extends Activity implements OnClickListener
{
	Button gis, knowledge, communication, logout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homemember);
		
		gis = (Button) findViewById(R.id.gis);
		gis.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_gis = new Intent(homeMember.this, gis.class);
				start_gis.putExtra("admin", admin);
		    	startActivity(start_gis);
	        }
		});
		
		knowledge = (Button) findViewById(R.id.listfile);
		knowledge.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent list_file = new Intent(homeMember.this, TabLayoutActivity.class);
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
				Intent start_chat = new Intent(homeMember.this, chat.class);
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

				Intent out = new Intent (homeMember.this, LoginMain.class);
				out.putExtra("admin", admin);
				startActivity(out);
				Toast.makeText(homeMember.this, "Terima Kasih",Toast.LENGTH_SHORT).show();
	        }
		});
	}
	
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub	
	}
}
