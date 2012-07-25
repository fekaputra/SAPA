package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SpecialOccasion extends Activity
{
	EditText namaKegiatan, namaPenyelenggara, waktuKegiatan, Kota, Topik, tag;
	Button kirim, homeReport;
	ListView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.special_occasion);
		
		namaKegiatan = (EditText) findViewById(R.id.nama_kegiatan);
		namaPenyelenggara = (EditText) findViewById(R.id.nama_penyelenggara);
		waktuKegiatan = (EditText) findViewById(R.id.waktu_kegiatan);
		Kota = (EditText) findViewById(R.id.kota);
		Topik = (EditText) findViewById(R.id.topik);
		tag = (EditText) findViewById(R.id.tag);

		kirim = (Button) findViewById(R.id.kirim);
		kirim.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				String toastMessage =
					"Nama Kegiatan 	 : " + namaKegiatan.getText().toString() + "\n" +
					"Nama Penyelenggara : " + namaPenyelenggara.getText().toString() + "\n" +
					"Waktu Kegiatan 	 : " + waktuKegiatan.getText().toString() + "\n" +
					"Kota   			 : " + Kota.getText().toString() + "\n" +
					"Topik      		 : " + Topik.getText().toString() + "\n" +
					"Tag        		 : " + tag.getText().toString();
				
				Toast t = Toast.makeText(SpecialOccasion.this, toastMessage, Toast.LENGTH_LONG);
				t.show();
			}
		});
		
		homeReport = (Button) findViewById(R.id.home_report);
		homeReport.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent home_report = new Intent(SpecialOccasion.this, report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
	        }
		});
	}
}
