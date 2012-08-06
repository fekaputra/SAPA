package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class report extends Activity
{
	Button report_childcare, report_disease, report_specoccasion, perkembangan_posyandu, pkp_kecamatan, home;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e_report);
		
		report_childcare = (Button) findViewById(R.id.childcare);
		report_childcare.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent child_care = new Intent(report.this, childcare.class);
				child_care.putExtra("admin", admin);
		    	startActivity(child_care);
	        }
		});
		
		report_disease = (Button) findViewById(R.id.disease);
		report_disease.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent start_disease = new Intent(report.this, disease.class);
				start_disease.putExtra("admin", admin);
		    	startActivity(start_disease);
	        }
		});
		
		report_specoccasion = (Button) findViewById(R.id.specoccasion);
		report_specoccasion.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent special_occasion = new Intent(report.this, SpecialOccasion.class);
				special_occasion.putExtra("admin", admin);
		    	startActivity(special_occasion);
	        }
		});
		
		perkembangan_posyandu = (Button) findViewById(R.id.perkembangan_posyandu);
		perkembangan_posyandu.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent special_occasion = new Intent(report.this, PerkembanganPosyandu.class);
				special_occasion.putExtra("admin", admin);
		    	startActivity(special_occasion);
	        }
		});
		
		pkp_kecamatan = (Button) findViewById(R.id.pkp_kecamatan);
		pkp_kecamatan.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent special_occasion = new Intent(report.this, LaporanKegPKPKecActivity.class);
				special_occasion.putExtra("admin", admin);
		    	startActivity(special_occasion);
	        }
		});
		
		home = (Button) findViewById(R.id.homePage);
		home.setOnClickListener(new Button.OnClickListener()
		{	
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				if(admin.equals("ADMIN"))
				{
					//ketika button Login di tekan, maka akan kembali ke halaman utama
					Intent home = new Intent (report.this, homeAdmin.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
				else
				{
					//ketika button Login di tekan, maka akan kembali ke halaman utama
					Intent home = new Intent (report.this, homeMember.class);
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
