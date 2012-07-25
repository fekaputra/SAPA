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
				String admin = "ADMIN";
				
				Intent back = new Intent(GrafikTumbuhKembang.this, tumbuhKembangAnak.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
	        }
		});
    }
}