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

import com.halamanlogin.R.id;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class homeAdmin extends Activity implements OnClickListener
{
	Button gis, report, knowledge, communication, logout;
	private String url = "http://fajarjuang.com/android/logout.php";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homeadmin);
			
		//Untuk menampilkan level user, apakah Admin atau Member
		/*TextView txtLevel = (TextView) findViewById(R.id.txtLevel);
		
		Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
		txtLevel.setText(admin);*/
		
		gis = (Button) findViewById(R.id.gis);
		gis.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_gis = new Intent(homeAdmin.this, gis.class);
				start_gis.putExtra("admin", admin);
		    	startActivity(start_gis);
	        }
		});
		
		report = (Button) findViewById(R.id.report);
		report.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_report = new Intent(homeAdmin.this, report.class);
				start_report.putExtra("admin", admin);
		    	startActivity(start_report);
	        }
		});
		
		knowledge = (Button) findViewById(R.id.listfile);
		knowledge.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent list_file = new Intent(homeAdmin.this, TabLayoutActivity.class);
				list_file.putExtra("admin", admin);
		    	startActivity(list_file);
	        }
		});
		
		communication = (Button) findViewById(R.id.chat);
		communication.setOnClickListener(new Button.OnClickListener()
		{			
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_chat = new Intent(homeAdmin.this, chat.class);
				start_chat.putExtra("admin", admin);
		    	startActivity(start_chat);
	        }
		});
		
		logout = (Button) findViewById(R.id.keluar);
		logout.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				String admin = "FALSE";

				Intent out = new Intent (homeAdmin.this, LoginMain.class);
				out.putExtra("admin", admin);
				startActivity(out);
				Toast.makeText(homeAdmin.this, "Terima Kasih",Toast.LENGTH_SHORT).show();
	        }		
		});
	}
	
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub	
	}
}
