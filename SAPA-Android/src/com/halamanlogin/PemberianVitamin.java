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

public class PemberianVitamin extends Activity implements	OnClickListener 
{
	TextView nama, dosis, JK;
	Button kirim, grafik, back;
	DatePicker tgl_pemberian_vitamin;
	Spinner spin;
	String[] usia_bulan = { "6-11", "12-23", "24-35", "36-47", "48-59" };
	String pemberian_vitamin;
	
	private ArrayAdapter<String> mAdapter;
	
	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pkvdt);
    	
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
		
		tgl_pemberian_vitamin = (DatePicker) findViewById(R.id.dateTanggal);
		
		kirim = (Button) findViewById(R.id.kirim);
        kirim.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				String usia = usia_bulan[spin.getSelectedItemPosition()];
				
				//get from datepicker
				int day = tgl_pemberian_vitamin.getDayOfMonth();
				int month = tgl_pemberian_vitamin.getMonth() + 1;
				int year = tgl_pemberian_vitamin.getYear();
				String tanggal = day + "-" + month + "-" + year;
				
				if(usia.equals("6-11"))
				{	
					pemberian_vitamin = "1 kapsul biru di bln Feb. ATAU Agust.";	
				}
				else if(usia.equals("12-23") || usia.equals("24-35") || usia.equals("36-47") || usia.equals("48-59"))
				{	
					pemberian_vitamin = "1 kapsul merah setiap bln Feb. DAN Agust.";	
				}
					
				dosis = (TextView) findViewById(R.id.dosis);
				dosis.setText(pemberian_vitamin);
				
				String toastMessage = 
						"Nama Anak         : " + nama.getText().toString() + "\n" +
						"Usia/ Bulan       : " + usia           + "\n" +	
						"Jenis pemberian_vitamin   : " + pemberian_vitamin + "\n" +		
						"Tanggal pemberian_vitamin : " + tanggal ;
				
				Toast.makeText(PemberianVitamin.this, toastMessage, Toast.LENGTH_LONG).show();
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
				
				Intent start_grafik = new Intent(PemberianVitamin.this, TabelPemberianVitamin.class);
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
				
				Intent home_report = new Intent(PemberianVitamin.this, ReportChildcare.class);
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
