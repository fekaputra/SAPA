package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Childcare extends Activity implements	OnClickListener 
{
	Button databaru, home, next;
	Spinner spin;
	String[] namaAnak = { "Ary", "Bintang", "Hana", "Tisa" };
	String jk;
	
	private ArrayAdapter<String> mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.childcare);
		
		databaru = (Button) findViewById(R.id.daftar_data_anak);
		databaru.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent data_baru = new Intent(Childcare.this, NewDataAnak.class);
				data_baru.putExtra("admin", admin);
		    	startActivity(data_baru);
	        }
		});
		
		home = (Button) findViewById(R.id.home_report);
		home.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent home_report = new Intent(Childcare.this, Report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
	        }
		});
		
		spin = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, namaAnak);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		
		next = (Button) findViewById(R.id.tumbuh_kembang_anak);
		next.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				String nama = namaAnak[spin.getSelectedItemPosition()]; 
				
				if(nama.equals("Ary") || nama.equals("Bintang"))
				{
					jk = "Laki-laki";
				}
				else if(nama.equals("Hana") || nama.equals("Tisa"))
				{
					jk = "Perempuan";
				}

				Intent start_report = new Intent(Childcare.this, ReportChildcare.class);
				start_report.putExtra("namaAnak", nama );
				start_report.putExtra("jk", jk);
				start_report.putExtra("admin", admin);
		    	startActivity(start_report);
		    	//Toast.makeText(childcare.this, namaAnak[spin.getSelectedItemPosition()],Toast.LENGTH_LONG).show();
	        }
		});
	}

	//@Override
	public void onClick(View v) 
	{
		if( v.equals(next) )
		{
			mAdapter.add(namaAnak[spin.getSelectedItemPosition()]);
		}
	}
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}
