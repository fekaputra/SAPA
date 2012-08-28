package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SpecialOccasion extends Activity
{
	EditText namaKegiatan, namaPenyelenggara, Kota, Topik, tag;
	Button kirim, homeReport;
	DatePicker waktuKegiatan;
	ListView list;
	
	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.special_occasion);
		
		namaKegiatan = (EditText) findViewById(R.id.nama_kegiatan);
        validasi.message(namaKegiatan);
		namaPenyelenggara = (EditText) findViewById(R.id.nama_penyelenggara);
        validasi.message(namaPenyelenggara);
		Kota = (EditText) findViewById(R.id.kota);
        validasi.message(Kota);
		Topik = (EditText) findViewById(R.id.topik);
        validasi.message(Topik);
		tag = (EditText) findViewById(R.id.tag);
        validasi.message(tag);
        
        waktuKegiatan = (DatePicker) findViewById(R.id.dateTanggal);
        
        final EditText[] editText = new EditText[] {namaKegiatan, namaPenyelenggara, Kota, Topik, tag};		

		kirim = (Button) findViewById(R.id.kirim);
		kirim.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				//call class validation insert to insert new data
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(SpecialOccasion.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					//mengambil tanggal dari datepicker
					int day = waktuKegiatan.getDayOfMonth();
					int month = waktuKegiatan.getMonth() + 1;
					int year = waktuKegiatan.getYear();
					String tanggal = day + "-" + month + "-" + year;
					
					String toastMessage =
							"Nama Kegiatan 	 : " + namaKegiatan.getText().toString() + "\n" +
							"Nama Penyelenggara : " + namaPenyelenggara.getText().toString() + "\n" +
							"Waktu Kegiatan 	 : " + tanggal + "\n" +
							"Kota   			 : " + Kota.getText().toString() + "\n" +
							"Topik      		 : " + Topik.getText().toString() + "\n" +
							"Tag        		 : " + tag.getText().toString();
						
					Toast.makeText(SpecialOccasion.this, toastMessage, Toast.LENGTH_LONG).show();
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
				
				Intent home_report = new Intent(SpecialOccasion.this, Report.class);
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
