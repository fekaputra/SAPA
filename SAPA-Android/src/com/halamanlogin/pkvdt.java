package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pkvdt extends Activity 
{
	
	Button back;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pkvdt);
        
        back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String namaAnak = i.getStringExtra("namaAnak");
				String admin = i.getStringExtra("admin");
				
				Intent back = new Intent(pkvdt.this, reportChildcare.class);
				back.putExtra("namaAnak", namaAnak );
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