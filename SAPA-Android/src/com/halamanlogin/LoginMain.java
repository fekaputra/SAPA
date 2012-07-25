/* Nama Package : com.halamanlogin
   Nama Kelas   : Activity LoginMain.java
   Deskripsi    : Main class/ main activity yang berfungsi untuk mengirimkan data yang diinputkan user untuk dicocokkan dengan data-
                  yang ada di database server melalui file login.php, menerima hasil dari proses yang ada di file login.php, 
                  dan menentukan activity selanjutnya yang harus dikerjakan untuk kondisi ketika proses berhasil dan ketika- 
                  proses tidak berhasil
   Author       : Keukeu Anggarani Putri
   Referensi    : http://nielpoenya.blogspot.com/2011/07/cara-mudah-membuat-menu-login-dengan.html
                  http://omayib.com/2012/04/09/tutorial-android-client-server/#codesyntax_5
                  nsafaat.wordpress.com/2011/07/29/formlogin-aplikasi-client-server-database-di-android/
			      http://agusharyanto.net/wordpress/?p=452&cpage=2#comments
*/
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


public class LoginMain extends Activity  
{
	String result = null;
	StringBuilder sb=null;
	EditText user, pass;
	Button btnlog, btnRegister, btnExit;
	ArrayList<NameValuePair> authentication;
	String passIn, userIn;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		user = (EditText) findViewById(R.id.username); //untuk memasukkan inputan username
		pass = (EditText) findViewById(R.id.passwd_input); //untuk memasukkan inputan password
		
		//button untuk masuk ke halaman registrasi
		btnRegister = (Button) findViewById(R.id.daftar); 
		btnRegister.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent register = new Intent (LoginMain.this, insertUser.class);
		    	startActivity(register);
	        }
		});
		
		//button untuk menutup aplikasi
		btnExit = (Button) findViewById(R.id.close);
		btnExit.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				if (v == btnExit) 
				{
					AlertDialog.Builder ad = new AlertDialog.Builder(LoginMain.this);
					ad.setMessage("Apakah anda benar-benar ingin keluar?");
					ad.setPositiveButton("Ya", new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog, int id) 
						{
							// closeDialog.this.finish();
							Intent exit = new Intent(Intent.ACTION_MAIN);
							exit.addCategory(Intent.CATEGORY_HOME);
							exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(exit);
						}
					});
					ad.setNegativeButton("Tidak", new DialogInterface.OnClickListener() 
					{
						public void onClick(DialogInterface dialog, int id) 
						{
							dialog.cancel();
						}
					});
					ad.show();
				}
	        }
		});
		
		//button untuk melakukan proses login
		btnlog =(Button) findViewById(R.id.masuk);
		btnlog.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View tombol_login) 
			{
				user = (EditText) findViewById(R.id.username);
				pass = (EditText) findViewById(R.id.passwd_input);
					
				try 
				{
					this.sendAuthenticationData(user.getText().toString(),pass.getText().toString() );
				} 
				catch (ClientProtocolException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			public void sendAuthenticationData(String username, String password) throws ClientProtocolException, IOException 
			{
				 authentication = new ArrayList<NameValuePair>();
				 authentication.add(new BasicNameValuePair("username", username));
				 authentication.add(new BasicNameValuePair("password", password));
				 this.sendData(authentication);
			}

			/**
			 * Method untuk memproses data username dan password untuk mencocokkan dengan data di database server 
			 * dengan menggunakan file login.php sebagai perantara komunikasi antara android dan mysql, 
			 * kemudian menerima hasil dari proses login.php dan menjalankan perintah yg harus dilakukan sesuai dengan hasil yg diterima.
			 * Jika proses berhasil, maka file login.php akan mengirimkan TRUE.ADMIN (jika berhasil login sebagai admin) atau 
			 * TRUE.MEMBER (jika berhasil login sebagai member)
			 * Jika proses gagal, maka file login.php akan mengirimkan FALSE
			 */
			public void sendData(ArrayList<NameValuePair> data) throws ClientProtocolException, IOException 
			{
				readURL rL;	
				String temp = "";
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://fajarjuang.com/android/login.php");
				httppost.setEntity(new UrlEncodedFormEntity(data));
				try
				{
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					temp = EntityUtils.toString(entity); 
					try {
						//mengirimkan username dan password yang diinput user untuk di proses oleh url http://10.0.2.2/Android/login.php
						rL = new readURL("http://fajarjuang.com/android/login.php?username=" + user.getText().toString() + "&password=" + pass.getText().toString());
						String auth=rL.getHTML();
						
						String filename = auth;
						String filenameArray[] = filename.split("\\."); //memisahkan string yang diperoleh dengan tanda . 
						String level = filenameArray[0]; //nilai 0 untuk TRUE, yaitu proses login berhasil
						
						if(level.equals("TRUE"))
						{
							/*String admin = filenameArray[1]; //nilai 1 untuk admin
							
							Toast.makeText(getBaseContext(), "Selamat Datang " + user.getText(), Toast.LENGTH_LONG).show();
							Intent s = new Intent(LoginMain.this, homePage.class); //memanggil activity/ class homePage
							
							//mengirimkan parameter, apakah user yang login itu Admin atau member, ke Class homePage
							s.putExtra("admin", admin);
							startActivity(s);*/
							
							String admin = filenameArray[1]; //nilai 1 untuk admin
							if(admin.equals("ADMIN"))
							{	
								Toast.makeText(getBaseContext(), "Selamat Datang " + user.getText(), Toast.LENGTH_LONG).show();
								Intent s = new Intent(LoginMain.this, homeAdmin.class);
								s.putExtra("admin", admin); //mengirimkan level user, admin atau member
								startActivity(s);
							}
							else
							{
								Toast.makeText(getBaseContext(), "Selamat Datang " + user.getText(), Toast.LENGTH_LONG).show();
								Intent s = new Intent(LoginMain.this, homeMember.class);
								s.putExtra("admin", admin);
							    startActivity(s);
							}
						}
						else
						{
							Toast.makeText(getBaseContext(), "Maaf Username atau Password Salah", Toast.LENGTH_LONG).show();						    	  
						  	Intent s = new Intent (LoginMain.this, GagalLogin.class); //memanggil activity/ class GagalLogin
						    startActivity(s);
						}      
					}
					catch(Exception ex)
					{
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