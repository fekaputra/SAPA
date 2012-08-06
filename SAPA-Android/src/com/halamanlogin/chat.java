package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chat extends Activity
{
	Button back;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e_communication);
		
		back = (Button) findViewById(R.id.kembali);
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				if(admin.equals("ADMIN"))
				{
					Intent home = new Intent (chat.this, homeAdmin.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
				else
				{
					Intent home = new Intent (chat.this, homeMember.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
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
