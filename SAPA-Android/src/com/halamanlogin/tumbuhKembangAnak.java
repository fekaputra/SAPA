package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TumbuhKembangAnak extends Activity 
{
    TextView nama, JK;
    Button kirim, grafik, back;
    EditText panjang, tinggi, berat;
    DatePicker tgl_data;
    
    ValidasiInsert validasi = new ValidasiInsert();
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_tumbuh_kembang);
        
        nama = (TextView) findViewById(R.id.namaAnak);
        JK = (TextView) findViewById(R.id.jk);
        
        Intent i = getIntent();
    	String namaAnak = i.getStringExtra("namaAnak");
    	String jk = i.getStringExtra("jk");
               
        nama.setText(namaAnak);
        JK.setText(jk);
        
        tgl_data = (DatePicker) findViewById(R.id.dateTanggal);
        
        panjang = (EditText) findViewById(R.id.panjang);
        validasi.message(panjang);
        tinggi = (EditText) findViewById(R.id.tinggi);
        validasi.message(tinggi);
        berat = (EditText) findViewById(R.id.berat);
        validasi.message(berat);
        
        final EditText[] editText = new EditText[] {panjang, tinggi, berat};
        
        kirim = (Button) findViewById(R.id.kirim);
        kirim.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(TumbuhKembangAnak.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					//mengambil tanggal dari datepicker
					int day = tgl_data.getDayOfMonth();
					int month = tgl_data.getMonth() + 1;
					int year = tgl_data.getYear();
					String tanggal = day + "-" + month + "-" + year;
					
					String toastMessage = 
							"Nama Anak     : " + nama.getText().toString() 	  + "\n" +
							"tgl_data      : " + tanggal          			  + "\n" +	
							"panjang badan : " + panjang.getText().toString() + "\n" +
							"berat badan   : " + berat.getText().toString()   + "\n" +
							"tinggi badan  : " + tinggi.getText().toString() ;
					
					Toast.makeText(TumbuhKembangAnak.this, toastMessage, Toast.LENGTH_LONG).show();
				}
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
				
				Intent start_grafik = new Intent(TumbuhKembangAnak.this, GrafikTumbuhKembang.class);
				start_grafik.putExtra("namaAnak", namaAnak);
				start_grafik.putExtra("jk", jk);
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
				String namaAnak = i.getStringExtra("namaAnak");
				String jk = i.getStringExtra("jk");
				
				Intent home_report = new Intent(TumbuhKembangAnak.this, ReportChildcare.class);
				home_report.putExtra("admin", admin);
				home_report.putExtra("namaAnak", namaAnak);
				home_report.putExtra("jk", jk);
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