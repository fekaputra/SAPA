package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DPJenisKelamin extends Activity
{
	TextView level;
    Button back;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penduduk_jeniskelamin);
        
        /*level = (TextView) findViewById(R.id.level);
        
        Intent i = getIntent();
    	String admin = i.getStringExtra("admin");
               
        level.setText(admin);*/
        
        back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent parameter = getIntent();
				String admin = parameter.getStringExtra("admin");
				
				Intent back = new Intent(DPJenisKelamin.this, kependudukan.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
	        }
		});
    }
}
