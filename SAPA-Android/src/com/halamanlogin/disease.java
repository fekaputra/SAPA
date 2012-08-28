package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Disease extends Activity
{
	private EditText namaPelapor, gejala, Lokasi, laporan, tag;
	private Button kirim, homeReport;
	
	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_disease);
		
		namaPelapor = (EditText) findViewById(R.id.nama_pelapor);
		validasi.message(namaPelapor);
		Lokasi = (EditText) findViewById(R.id.lokasi);
        validasi.message(Lokasi);
		gejala = (EditText) findViewById(R.id.gejala_keluhan);
        validasi.message(gejala);
		laporan = (EditText) findViewById(R.id.laporan);
        validasi.message(laporan);
		tag = (EditText) findViewById(R.id.tag);
        validasi.message(tag);
        
        final EditText[] editText = new EditText[] {namaPelapor, Lokasi, gejala, laporan, tag};	

		kirim = (Button) findViewById(R.id.kirim);
		kirim.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(Disease.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					String toastMessage =
							"Nama Pelapor   : " + namaPelapor.getText().toString() + "\n" +
							"Lokasi         : " + Lokasi.getText().toString() + "\n" +
							"Gejala/Keluhan : " + gejala.getText().toString() + "\n" +
							"Laporan        : " + laporan.getText().toString() + "\n" +
							"Tag            : " + tag.getText().toString();
						
						Toast.makeText(Disease.this, toastMessage, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		homeReport = (Button) findViewById(R.id.home_report);
		homeReport.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent home_report = new Intent(Disease.this, Report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
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
