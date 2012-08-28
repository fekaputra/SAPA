package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GrafikTumbuhKembang extends Activity 
{
    TextView nama;
    Button back;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafik_tumbuhkembang);
        
        nama = (TextView) findViewById(R.id.nama);
        
        Intent i = getIntent();
    	String namaAnak = i.getStringExtra("namaAnak");
               
        nama.setText(namaAnak);
        
        back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = "ADMIN";
				String namaAnak = i.getStringExtra("namaAnak");
				String jk = i.getStringExtra("jk");
				
				Intent back = new Intent(GrafikTumbuhKembang.this, TumbuhKembangAnak.class);
				back.putExtra("admin", admin);
				back.putExtra("namaAnak", namaAnak);
				back.putExtra("jk", jk);
		    	startActivity(back);
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