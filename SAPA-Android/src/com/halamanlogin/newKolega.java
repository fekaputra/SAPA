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

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_kolega);
		
		nama = (EditText) findViewById(R.id.nama_kolega);
		bidang = (EditText) findViewById(R.id.bidang_kolega);
		lokasi = (EditText) findViewById(R.id.lokasi_kolega);

		btnKirim = (Button) findViewById(R.id.kirim);
		btnKirim.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{			
				String toastMessage = "Nama Kolega       : " + nama.getText().toString() + "\n" +
									  "Bidang/ Keahlian  : " + bidang.getText().toString() + "\n" +
									  "Lokasi            : " + lokasi.getText().toString()  + "\n";
			
				Toast t = Toast.makeText(newKolega.this, toastMessage, Toast.LENGTH_LONG);
				t.show();	
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