package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TabelPemberianVitamin extends Activity 
{
	
	Button back;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabel_pemberian_vitamin);
        
        back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = "ADMIN";
				String namaAnak = i.getStringExtra("namaAnak");
				String jk = i.getStringExtra("jk");
				
				Intent back = new Intent(TabelPemberianVitamin.this, PemberianVitamin.class);
				back.putExtra("namaAnak", namaAnak );
				back.putExtra("jk", jk );
				back.putExtra("admin", admin);
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