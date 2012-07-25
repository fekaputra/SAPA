package com.halamanlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
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
import android.widget.Toast;

public class disease extends Activity
{
	private EditText namaPelapor, gejala, Lokasi, laporan, tag;
	private Button kirim, homeReport;
	private ListView list;
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> mPresets = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_disease);
		
		namaPelapor = (EditText) findViewById(R.id.nama_pelapor);
		Lokasi = (EditText) findViewById(R.id.lokasi);
		gejala = (EditText) findViewById(R.id.gejala_keluhan);
		laporan = (EditText) findViewById(R.id.laporan);
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
					"Nama Pelapor   : " + namaPelapor.getText().toString() + "\n" +
					"Lokasi         : " + Lokasi.getText().toString() + "\n" +
					"Gejala/Keluhan : " + gejala.getText().toString() + "\n" +
					"Laporan        : " + laporan.getText().toString() + "\n" +
					"Tag            : " + tag.getText().toString();
				
				Toast t = Toast.makeText(disease.this, toastMessage, Toast.LENGTH_LONG);
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
				
				Intent home_report = new Intent(disease.this, report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
	        }
		});
	}
}
