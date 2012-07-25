package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class report extends Activity
{
	Button report_childcare, report_disease, report_specoccasion, perkembangan_posyandu, pkp_kecamatan, home;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.e_report);
		
		report_childcare = (Button) findViewById(R.id.childcare);
		report_childcare.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent child_care = new Intent(report.this, childcare.class);
				child_care.putExtra("admin", admin);
		    	startActivity(child_care);
	        }
		});
		
		report_disease = (Button) findViewById(R.id.disease);
		report_disease.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent start_disease = new Intent(report.this, disease.class);
				start_disease.putExtra("admin", admin);
		    	startActivity(start_disease);
	        }
		});
		
		report_specoccasion = (Button) findViewById(R.id.specoccasion);
		report_specoccasion.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent special_occasion = new Intent(report.this, SpecialOccasion.class);
				special_occasion.putExtra("admin", admin);
		    	startActivity(special_occasion);
	        }
		});
		
		perkembangan_posyandu = (Button) findViewById(R.id.perkembangan_posyandu);
		perkembangan_posyandu.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent special_occasion = new Intent(report.this, PerkembanganPosyandu.class);
				special_occasion.putExtra("admin", admin);
		    	startActivity(special_occasion);
	        }
		});
		
		pkp_kecamatan = (Button) findViewById(R.id.pkp_kecamatan);
		pkp_kecamatan.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent special_occasion = new Intent(report.this, LaporanKegPKPKecActivity.class);
				special_occasion.putExtra("admin", admin);
		    	startActivity(special_occasion);
	        }
		});
		
		home = (Button) findViewById(R.id.homePage);
		home.setOnClickListener(new Button.OnClickListener()
		{	
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				if(admin.equals("ADMIN"))
				{
					//ketika button Login di tekan, maka akan kembali ke halaman utama
					Intent home = new Intent (report.this, homeAdmin.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
				else
				{
					//ketika button Login di tekan, maka akan kembali ke halaman utama
					Intent home = new Intent (report.this, homeMember.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
	        }
		});
	}
	
	/**
	 * Method untuk Mengirimkan data keserver event by button submit diklik
	 *
	 * @param view
	 
	public void getRequest(String Url) 
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		
		Intent i = getIntent();
		String member = i.getStringExtra("admin");
		try 
		{
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Terima Kasih" ,Toast.LENGTH_SHORT).show();
			Intent out = new Intent (homeMember.this, LoginMain.class);
			out.putExtra("admin", member);
			startActivity(out);	
		} 
		catch (Exception ex) 
		{
			Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Method untuk Menenrima data dari server
	 *
	 * @param response
	 * @return
	 
	public static String request(HttpResponse response) {
		String result = "";

		try {
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
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
	}*/
}
