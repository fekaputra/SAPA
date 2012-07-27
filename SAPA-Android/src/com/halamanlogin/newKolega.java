package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newKolega extends Activity {
	
	private EditText nama, bidang, lokasi;
	private Button btnKirim, back;

	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_kolega);
		
		nama = (EditText) findViewById(R.id.nama_kolega);
		validasi.message(nama);
		bidang = (EditText) findViewById(R.id.bidang_kolega);
		validasi.message(bidang);
		lokasi = (EditText) findViewById(R.id.lokasi_kolega);
		validasi.message(lokasi);
		
		final EditText[] editText = new EditText[] {nama, bidang, lokasi};
		
		btnKirim = (Button) findViewById(R.id.kirim);
		btnKirim.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{			
				//call class validation insert to insert new data
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(newKolega.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					String toastMessage = 
							"Nama Kolega       : " + nama.getText().toString() + "\n" +
							"Bidang/ Keahlian  : " + bidang.getText().toString() + "\n" +
							"Lokasi            : " + lokasi.getText().toString()  + "\n";
				
					Toast.makeText(newKolega.this, toastMessage, Toast.LENGTH_LONG).show();	
				}
			}
		});
		
        back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent back = new Intent(newKolega.this, kolega.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
	        }
		});
	}
}