package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class knowledge extends Activity
{
	Button news, tutorial, library, home;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e_knowledge);
			
		//Untuk menampilkan level user, apakah Admin atau Member
		/*TextView txtLevel = (TextView) findViewById(R.id.txtLevel);
		
		Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
		txtLevel.setText(admin);*/
		
		news = (Button) findViewById(R.id.news);
		news.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent start_chat = new Intent(knowledge.this, chat.class);
		    	startActivity(start_chat);
	        }
		});
		
		tutorial = (Button) findViewById(R.id.tutorial);
		tutorial.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent list_file = new Intent(knowledge.this, KnowledgeTutorial.class);
		    	startActivity(list_file);
	        }
		});
		
		library = (Button) findViewById(R.id.library);
		library.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent start_chat = new Intent(knowledge.this, KnowledgeLibrary.class);
		    	startActivity(start_chat);
	        }
		});
		
		home = (Button) findViewById(R.id.home);
		home.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				if(admin.equals("ADMIN"))
				{
					//ketika button Login di tekan, maka akan kembali ke halaman utama
					Intent home = new Intent (knowledge.this, homeAdmin.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
				else
				{
					//ketika button Login di tekan, maka akan kembali ke halaman utama
					Intent home = new Intent (knowledge.this, homeMember.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
	        }
		});
	}
}
