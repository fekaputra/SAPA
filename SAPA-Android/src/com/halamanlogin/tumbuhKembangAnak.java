package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class tumbuhKembangAnak extends Activity 
{
    TextView nama;
    Button kirim, grafik, back;
    EditText JK, tgl_data, panjang, tinggi, berat;
    
    ValidasiInsert validasi = new ValidasiInsert();
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_tumbuh_kembang);
        
        nama = (TextView) findViewById(R.id.namaAnak);
        
        Intent i = getIntent();
    	String namaAnak = i.getStringExtra("namaAnak");
               
        nama.setText(namaAnak);
        
        JK = (EditText) findViewById(R.id.jk);
        validasi.message(JK);
        tgl_data = (EditText) findViewById(R.id.tgl_data);
        validasi.message(tgl_data);
        panjang = (EditText) findViewById(R.id.panjang);
        validasi.message(panjang);
        tinggi = (EditText) findViewById(R.id.tinggi);
        validasi.message(tinggi);
        berat = (EditText) findViewById(R.id.berat);
        validasi.message(berat);
        
        final EditText[] editText = new EditText[] {JK, tgl_data, panjang, tinggi, berat};
        
        kirim = (Button) findViewById(R.id.kirim);
        kirim.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(tumbuhKembangAnak.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					String admin = "ADMIN";
					
					Intent start_kirim = new Intent(tumbuhKembangAnak.this, tumbuhKembangAnak.class);
					start_kirim.putExtra("admin", admin);
			    	startActivity(start_kirim);
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
				
				Intent start_grafik = new Intent(tumbuhKembangAnak.this, GrafikTumbuhKembang.class);
				start_grafik.putExtra("namaAnak", namaAnak);
		    	startActivity(start_grafik);
	        }
		});
        
        back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				String admin = "ADMIN";
				
				Intent back = new Intent(tumbuhKembangAnak.this, reportChildcare.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
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