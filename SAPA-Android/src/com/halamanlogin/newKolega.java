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

public class newKolega extends Activity {
	
	private EditText nama, bidang, lokasi;
	private Button btnKirim, back;
	private ListView list;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> mPresets = new ArrayList<String>();

	/**
	 * Method yang dipanggil pada saat aplikaasi dijalankan
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_kolega);
		
		nama = (EditText) findViewById(R.id.nama_kolega);
		bidang = (EditText) findViewById(R.id.bidang_kolega);
		lokasi = (EditText) findViewById(R.id.lokasi_kolega);
		
		/*list = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mPresets);
		list.setAdapter(mAdapter);*/
		

		btnKirim = (Button) findViewById(R.id.kirim);
		// daftarkan even onClick pada btnSimpan
		btnKirim.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub			
				String toastMessage = 
						"Nama Kolega       : " + nama.getText().toString() + "\n" +
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