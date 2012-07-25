package com.halamanlogin;

import java.io.IOException;
import java.util.ArrayList;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insertUser extends Activity 
{
	private EditText newUsername, newPassword, newEmail;
	private Button btnSubmit;
	private ArrayList<NameValuePair> authentication;

	private String url = Referensi.url + "/insertUser.php";

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		newUsername = (EditText) findViewById(R.id.newusername);
		newPassword = (EditText) findViewById(R.id.newpassword);
		newEmail = (EditText) findViewById(R.id.newemail);

		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				newUsername = (EditText) findViewById(R.id.newusername);
				newPassword = (EditText) findViewById(R.id.newpassword);
				newEmail = (EditText) findViewById(R.id.newemail);
				
				try 
				{
					//memanggil method sendAuthenticationData data untuk memproses username, password dan email baru
					this.sendAuthenticationData(newUsername.getText().toString(),newPassword.getText().toString(),newEmail.getText().toString() );
				} 
				catch (ClientProtocolException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			
			/**
			 * Method untuk mengambil data username, password dan email baru yang diinputkan user untuk dimasukkan ke database server
			 */
			public void sendAuthenticationData(String username, String password, String email) throws ClientProtocolException, IOException 
			{
				 authentication = new ArrayList<NameValuePair>();
				 authentication.add(new BasicNameValuePair("username", username));
				 authentication.add(new BasicNameValuePair("password", password));
				 authentication.add(new BasicNameValuePair("emai", email));
				 this.sendData(authentication);
			}

			/**
			 * Method untuk memproses data username, password dan email baru dan mencocokkan dengan data di database server 
			 */
			public void sendData(ArrayList<NameValuePair> data) throws ClientProtocolException, IOException 
			{
				readURL rL;	
				String temp = "";
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				httppost.setEntity(new UrlEncodedFormEntity(data));
				try
				{
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					temp = EntityUtils.toString(entity); 
					try {
						rL = new readURL(Referensi.url + "/insertUser.php?username=" + newUsername.getText().toString() + "&password=" + newPassword.getText().toString() + "&email=" + newEmail.getText().toString());
						String insert=rL.getHTML();
						
						if(insert.equals("FAILED")) //Jika username, password atau email ada yang tidak diisi
						{
							Toast.makeText(insertUser.this, "Maaf username sudah terdaftar di database" ,Toast.LENGTH_SHORT).show();
						}
						else
						{
							Toast.makeText(insertUser.this, "Selamat Anda Sudah Terdaftar" ,Toast.LENGTH_SHORT).show();
							Intent home = new Intent (insertUser.this, LoginMain.class);
							startActivity(home);	
						}      
					}
					catch(Exception ex)
					{
						Toast.makeText(insertUser.this, "Maaf registrasi gagal" ,Toast.LENGTH_SHORT).show();
						System.out.println("Error=" + ex.getMessage());
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}
}