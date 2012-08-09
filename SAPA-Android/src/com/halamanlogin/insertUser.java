package com.halamanlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertUser extends Activity
{
	private EditText newUsername, newPassword, newEmail;
	private Button btnSubmit, btnBack;

	private String url = Referensi.url + "/insertUser.php";

	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		newUsername = (EditText) findViewById(R.id.newusername);
		validasi.message(newUsername);
		newPassword = (EditText) findViewById(R.id.newpassword);
		validasi.message(newPassword);
		
		final EditText[] editText = new EditText[] {newUsername, newPassword, newEmail};

		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				try 
				{
					//call class validation insert to insert new data
					boolean check = validasi.validation(editText);//validation(editText);
					if(check == false)
					{
						Toast.makeText(insertUser.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
						validasi.messages(editText);
					}
					else
					{
						String username = URLEncoder.encode(newUsername.getText().toString(), "utf-8");
						String password = URLEncoder.encode(newPassword.getText().toString(), "utf-8");
						String email = URLEncoder.encode(newEmail.getText().toString(), "utf-8");
					
						url += "?username="+ username+ "&password=" + password + "&email=" + email;

						getRequest(url);
					}
				} 
				catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		btnBack = (Button) findViewById(R.id.kembali);
		btnBack.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				Intent back = new Intent(insertUser.this, LoginMain.class);
		    	startActivity(back);
			}
		});
	}
	
	public void message (EditText record)
	{
		EditText valid = record;
		if (valid.getText().toString().length() == 0)
		{
			valid.setError("This Record is Required");
		}
	}

	/**
	 * Method untuk Mengirimkan data keserver event by button submit diklik
	 */
	public void getRequest(String Url) 
	{	
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try 
		{
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Registrasi " + request(response), Toast.LENGTH_SHORT).show();
			Intent home = new Intent (insertUser.this, LoginMain.class);
			startActivity(home);
		} 
		catch (Exception ex) 
		{
			Toast.makeText(this, "Maaf username sudah terdaftar", Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * Method untuk Menenrima data dari server
	 */
	public static String request(HttpResponse response) 
	{
		String result = "";

		try 
		{
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
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
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}