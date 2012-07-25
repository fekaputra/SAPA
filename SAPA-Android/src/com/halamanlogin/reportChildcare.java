package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reportChildcare extends Activity
{
	Button PPA, imunisasi, PKVDT, home;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_menu);
		
		PPA = (Button) findViewById(R.id.PPA);
		PPA.setOnClickListener(new Button.OnClickListener()
		{	
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent child_care = new Intent(reportChildcare.this, tumbuhKembangAnak.class);
				child_care.putExtra("namaAnak", namaAnak );
				child_care.putExtra("admin", admin);
		    	startActivity(child_care);
	        }
		});
		
		imunisasi = (Button) findViewById(R.id.imunisasi);
		imunisasi.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent start_imunisasi = new Intent(reportChildcare.this, imunisasi.class);
				start_imunisasi.putExtra("namaAnak", namaAnak );
				start_imunisasi.putExtra("admin", admin);
		    	startActivity(start_imunisasi);
	        }
		});
		
		PKVDT = (Button) findViewById(R.id.PKVDT);
		PKVDT.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent start_pkvdt = new Intent(reportChildcare.this, pkvdt.class);
				start_pkvdt.putExtra("namaAnak", namaAnak );
				start_pkvdt.putExtra("admin", admin);
		    	startActivity(start_pkvdt);
	        }
		});
		
		home = (Button) findViewById(R.id.home_childcare);
		home.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent home = new Intent (reportChildcare.this, childcare.class);
				home.putExtra("namaAnak", namaAnak );
				home.putExtra("admin", admin);
		    	startActivity(home);
	        }
		});
	}
}
