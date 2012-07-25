package com.halamanlogin;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class childcare extends Activity implements	OnClickListener 
{
	Button databaru, home, next;
	Spinner spin;
	String[] namaAnak = { "Ary", "Bintang", "Hana", "Tisa" };
	
	private ArrayAdapter<String> mAdapter;
	private ArrayList<String> mPresets = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.childcare);
			
		//Untuk menampilkan level user, apakah Admin atau Member
		/*TextView txtLevel = (TextView) findViewById(R.id.txtLevel);
		
		Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
		txtLevel.setText(admin);*/
		
		databaru = (Button) findViewById(R.id.daftar_data_anak);
		databaru.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent data_baru = new Intent(childcare.this, newDataAnak.class);
				data_baru.putExtra("admin", admin);
		    	startActivity(data_baru);
	        }
		});
		
		home = (Button) findViewById(R.id.home_report);
		home.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent home_report = new Intent(childcare.this, report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
	        }
		});
		
		spin = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, namaAnak);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		
		next = (Button) findViewById(R.id.tumbuh_kembang_anak);
		next.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				String nama = namaAnak[spin.getSelectedItemPosition()]; 

				Intent start_report = new Intent(childcare.this, reportChildcare.class);
				start_report.putExtra("namaAnak", nama );
				start_report.putExtra("admin", admin);
		    	startActivity(start_report);
		    	//Toast.makeText(childcare.this, namaAnak[spin.getSelectedItemPosition()],Toast.LENGTH_LONG).show();
	        }
		});
	}

	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		if( v.equals(next) )
		{
			mAdapter.add(namaAnak[spin.getSelectedItemPosition()]);
		}
	}
}
