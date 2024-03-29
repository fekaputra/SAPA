/* Nama Package : com.halamanlogin
   Nama Kelas   : Class GagalLogin.java
   Deskripsi    : Kelas yang berfungsi untuk menangani ketika username atau password yang diinputkan user salah
   Author       : Keukeu Anggarani Putri
   Referensi    : http://omayib.com/2012/04/09/tutorial-android-client-server/#codesyntax_5
                  nsafaat.wordpress.com/2011/07/29/formlogin-aplikasi-client-server-database-di-android/
			      http://agusharyanto.net/wordpress/?p=452&cpage=2#comments
*/

package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GagalLogin extends Activity
{
	Button homeBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gagallogin);
		
		homeBtn = (Button) findViewById(R.id.btnHome);
		homeBtn.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				//ketika button Login di tekan, maka akan kembali ke halaman utama
				Toast.makeText(getBaseContext(), "Maaf Username atau Password Salah", Toast.LENGTH_LONG).show();
				Intent home = new Intent (GagalLogin.this, LoginMain.class);
		    	startActivity(home);
	        }
		});
	}
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}
