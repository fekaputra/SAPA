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

public class homePage extends Activity
{
	Button listfile, chat, logout;
	private String url = "http://10.0.2.2/android/logout.php";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
			
		//Untuk menampilkan level user, apakah Admin atau Member
		/*TextView txtLevel = (TextView) findViewById(R.id.txtLevel);
		
		Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
		txtLevel.setText(admin);*/
		
		listfile = (Button) findViewById(R.id.listfile);
		listfile.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent list_file = new Intent(homePage.this, TabLayoutActivity.class);
		    	startActivity(list_file);
	        }
		});
		
		chat = (Button) findViewById(R.id.chat);
		chat.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent start_chat = new Intent(homePage.this, chat.class);
		    	startActivity(start_chat);
	        }
		});
		
		logout = (Button) findViewById(R.id.keluar);
		logout.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				getRequest(url);
	        }
		});
	}
	
	/**
	 * Method untuk Mengirimkan data keserver event by button submit diklik
	 *
	 * @param view
	 */
	public void getRequest(String Url) 
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try 
		{
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Terima Kasih" ,Toast.LENGTH_SHORT).show();
			Intent out = new Intent (homePage.this, LoginMain.class);
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
	 */
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
	}
}
