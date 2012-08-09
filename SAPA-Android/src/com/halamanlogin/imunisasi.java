package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class imunisasi extends Activity implements	OnClickListener 
{
	TextView nama, jenis_imunisasi, JK;
	Button kirim, grafik, back;
	DatePicker tgl_imunisasi;
	Spinner spin;
	String[] usia_bulan = { "0", "1", "2", "3", "4", "9" };
	String imunisasi;
	
	private ArrayAdapter<String> mAdapter;
	
	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imunisasi);
		
		nama = (TextView) findViewById(R.id.namaAnak);
		JK = (TextView) findViewById(R.id.jk);

		Intent i = getIntent();
    	String namaAnak = i.getStringExtra("namaAnak");
    	String jk = i.getStringExtra("jk");
    	               
        nama.setText(namaAnak);
        JK.setText(jk);
              
        spin = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, usia_bulan);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		
		tgl_imunisasi = (DatePicker) findViewById(R.id.dateTanggal);
		
		kirim = (Button) findViewById(R.id.kirim);
        kirim.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				//mengambil tanggal dari datepicker
				int day = tgl_imunisasi.getDayOfMonth();
				int month = tgl_imunisasi.getMonth() + 1;
				int year = tgl_imunisasi.getYear();
				String tanggal = day + "-" + month + "-" + year;
				
				String usia = usia_bulan[spin.getSelectedItemPosition()];
					
				if(usia.equals("0"))
				{	imunisasi = "HBO";	}
				else if(usia.equals("1"))
				{	imunisasi = "BCG, Polio 1";	}
				else if(usia.equals("2"))
				{	imunisasi = "DPT/HB1, Polio 2";	}
				else if(usia.equals("3"))
				{	imunisasi = "DPT/HB2, Polio 3";	}
				else if(usia.equals("4"))
				{	imunisasi = "DPT/HB3, Polio 4";	}
				else if(usia.equals("9"))
				{	imunisasi = "Campak";	}
					
				jenis_imunisasi = (TextView) findViewById(R.id.jns_imunisasi);
				jenis_imunisasi.setText(imunisasi);
				
				String toastMessage = 
						"Nama Anak         : " + nama.getText().toString() + "\n" +
						"Usia/ Bulan       : " + usia           + "\n" +	
						"Jenis Imunisasi   : " + imunisasi           + "\n" +		
						"Tanggal Imunisasi : " + tanggal ;
				
				Toast.makeText(imunisasi.this, toastMessage, Toast.LENGTH_LONG).show();
	        }
		});
        
        grafik = (Button) findViewById(R.id.grafik);
        grafik.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
		    	String namaAnak = i.getStringExtra("namaAnak");
		    	String jk = i.getStringExtra("jk");
		    	String admin = i.getStringExtra("admin");
				
				Intent start_grafik = new Intent(imunisasi.this, TabelPemberianVitamin.class);
				start_grafik.putExtra("namaAnak", namaAnak);
				start_grafik.putExtra("jk", jk);
				start_grafik.putExtra("admin", admin);
		    	startActivity(start_grafik);
	        }
		});
        
        back = (Button) findViewById(R.id.kembali);
		back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
		    	String jk = i.getStringExtra("jk");
				String namaAnak = i.getStringExtra("namaAnak");
				
				Intent home_report = new Intent(imunisasi.this, reportChildcare.class);
				home_report.putExtra("admin", admin);
				home_report.putExtra("jk", jk);
				home_report.putExtra("namaAnak", namaAnak);
		    	startActivity(home_report);
	        }
		});
	}

	@Override
	public void onClick(View v) 
	{
		if( v.equals(kirim) )
		{
			mAdapter.add(usia_bulan[spin.getSelectedItemPosition()]);
		}
	}
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}
