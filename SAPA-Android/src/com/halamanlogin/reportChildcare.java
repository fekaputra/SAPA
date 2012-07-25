package com.halamanlogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class reportChildcare extends Activity
{
	Button PPA, imunisasi, PKVDT, home;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_menu);
			
		//Untuk menampilkan level user, apakah Admin atau Member	
		/*Intent i = getIntent();
		String namaAnak = i.getStringExtra("namaAnak");*/
		
		
		PPA = (Button) findViewById(R.id.PPA);
		PPA.setOnClickListener(new Button.OnClickListener()
		{	
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent child_care = new Intent(reportChildcare.this, tumbuhKembangAnak.class);
				child_care.putExtra("namaAnak", namaAnak );
				child_care.putExtra("admin", admin);
		    	startActivity(child_care);
	        }
		});
		
		imunisasi = (Button) findViewById(R.id.imunisasi);
		imunisasi.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent start_imunisasi = new Intent(reportChildcare.this, imunisasi.class);
				start_imunisasi.putExtra("namaAnak", namaAnak );
				start_imunisasi.putExtra("admin", admin);
		    	startActivity(start_imunisasi);
	        }
		});
		
		PKVDT = (Button) findViewById(R.id.PKVDT);
		PKVDT.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent start_pkvdt = new Intent(reportChildcare.this, pkvdt.class);
				start_pkvdt.putExtra("namaAnak", namaAnak );
				start_pkvdt.putExtra("admin", admin);
		    	startActivity(start_pkvdt);
	        }
		});
		
		home = (Button) findViewById(R.id.home_childcare);
		home.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent home = new Intent (reportChildcare.this, childcare.class);
				home.putExtra("namaAnak", namaAnak );
				home.putExtra("admin", admin);
		    	startActivity(home);
	        }
		});
	}
	
	/**
	 * Method untuk Mengirimkan data keserver event by button submit diklik
	 *
	 * @param view
	 
	public void getRequest(String Url) 
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		
		Intent i = getIntent();
		String member = i.getStringExtra("admin");
		try 
		{
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Terima Kasih" ,Toast.LENGTH_SHORT).show();
			Intent out = new Intent (homeMember.this, LoginMain.class);
			out.putExtra("admin", member);
			startActivity(out);	
		} 
		catch (Exception ex) 
		{
			Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Method untuk Menenrima data dari server
	 *
	 * @param response
	 * @return
	 
	public static String request(HttpResponse response) {
		String result = "";

		try {
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line + "\n");
			}
			in.close();
			result = str.toString();
		} 
		catch (Exception ex) 
		{
			result = "Error";
		}
		return result;
	}*/
}
