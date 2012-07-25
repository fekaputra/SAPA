package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class disease extends Activity
{
	private EditText namaPelapor, gejala, Lokasi, laporan, tag;
	private Button kirim, homeReport;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_disease);
		
		namaPelapor = (EditText) findViewById(R.id.nama_pelapor);
		Lokasi = (EditText) findViewById(R.id.lokasi);
		gejala = (EditText) findViewById(R.id.gejala_keluhan);
		laporan = (EditText) findViewById(R.id.laporan);
		tag = (EditText) findViewById(R.id.tag);		

		kirim = (Button) findViewById(R.id.kirim);
		kirim.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String toastMessage =
					"Nama Pelapor   : " + namaPelapor.getText().toString() + "\n" +
					"Lokasi         : " + Lokasi.getText().toString() + "\n" +
					"Gejala/Keluhan : " + gejala.getText().toString() + "\n" +
					"Laporan        : " + laporan.getText().toString() + "\n" +
					"Tag            : " + tag.getText().toString();
				
				Toast t = Toast.makeText(disease.this, toastMessage, Toast.LENGTH_LONG);
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
				
				Intent home_report = new Intent(disease.this, report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
	        }
		});
	}
}
