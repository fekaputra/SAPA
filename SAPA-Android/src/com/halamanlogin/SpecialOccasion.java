package com.halamanlogin;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SpecialOccasion extends Activity
{
	
	EditText namaKegiatan, namaPenyelenggara, waktuKegiatan, Kota, Topik, tag;
	Button kirim, homeReport;
	ListView list;
	
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> mPresets = new ArrayList<String>();
	
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
		
		/*list = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mPresets);
		list.setAdapter(mAdapter);*/
		

		kirim = (Button) findViewById(R.id.kirim);
		// daftarkan even onClick pada btnSimpan
		kirim.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
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
