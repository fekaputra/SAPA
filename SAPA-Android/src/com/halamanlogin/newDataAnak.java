package com.halamanlogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class newDataAnak extends Activity {
	
	private EditText newPosyandu, newNamaAnak, newTglLahir, newBbLahir, newNamaIbu, newNamaAyah;
	private Button btnSubmit, back;
	String jns_kel;
	RadioButton male, female;
	RadioGroup jenis_kelamin;
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
		setContentView(R.layout.new_data_anak);
		
		newPosyandu = (EditText) findViewById(R.id.nama_posyandu);
		newNamaAnak = (EditText) findViewById(R.id.new_namaAnak);
		newTglLahir = (EditText) findViewById(R.id.new_tglLahir);
		newBbLahir = (EditText) findViewById(R.id.new_bbLahir);
		newNamaIbu = (EditText) findViewById(R.id.new_namaIbu);
		newNamaAyah = (EditText) findViewById(R.id.new_namaAyah);
		
		//Menampilkan data dalam bentuk list
		/*list = (ListView)findViewById(R.id.listView1);
		mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mPresets);
		list.setAdapter(mAdapter);*/
		
		jenis_kelamin = (RadioGroup) findViewById(R.id.radioGroupSex);
		int jk = jenis_kelamin.getCheckedRadioButtonId();
		if(jk == R.id.radioMale)
		{
			jns_kel = "Laki-laki";
		}
		else if(jk == R.id.radioFemale)
		{
			jns_kel = "Perempuan";
		};
		

		btnSubmit = (Button) findViewById(R.id.daftar);
		// daftarkan even onClick pada btnSimpan
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				/*mAdapter.add("Nama Posyandu : " + newPosyandu.getText().toString());
				mAdapter.add("Nama Anak     : " + newNamaAnak.getText().toString());
				mAdapter.add("Tanggal Lahir : " + newTglLahir.getText().toString());
				mAdapter.add("Berat Badan   : " + newBbLahir.getText().toString());
				mAdapter.add("Nama Ibu      : " + newNamaIbu.getText().toString());
				mAdapter.add("Nama Ayah     : " + newNamaAyah.getText().toString());*/
				
				String toastMessage = 
						"Nama Posyandu : " + newPosyandu.getText().toString() + "\n" +
						"Nama Anak     : " + newNamaAnak.getText().toString() + "\n" +
						"Jenis Kelamin : " + jns_kel                          + "\n" +		
						"Tanggal Lahir : " + newTglLahir.getText().toString() + "\n" +
						"Berat Badan   : " + newBbLahir.getText().toString() + "\n" +
						"Nama Ibu      : " + newNamaIbu.getText().toString() + "\n" +
						"Nama Ayah     : " + newNamaAyah.getText().toString();
			
				Toast t = Toast.makeText(newDataAnak.this, toastMessage, Toast.LENGTH_LONG);
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
				
				Intent back = new Intent(newDataAnak.this, childcare.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
	        }
		});
	}
}