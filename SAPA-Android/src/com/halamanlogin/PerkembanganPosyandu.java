package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class PerkembanganPosyandu extends Activity implements OnClickListener
{
	private Button btnData, btnStrata, btnKader, btnProgPokok, btnBack;
	Spinner spin;
	String idPosyandu;
	String[] kelurahan = { "Gudang Kahuripan", "Baros" };
	
	private ArrayAdapter<String> mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perkembangan_posyandu);
		
		btnData = (Button) findViewById(R.id.data_posyandu);
		btnData.setOnClickListener(new Button.OnClickListener()
		{
			Intent i = getIntent();
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				Intent start_strata = new Intent(PerkembanganPosyandu.this, DataPosyandu.class);
				start_strata.putExtra("admin", admin);
		    	startActivity(start_strata);
	        }
		});
		
		btnStrata = (Button) findViewById(R.id.strata);
		btnStrata.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				String namaKelurahan = kelurahan[spin.getSelectedItemPosition()];
				
				//Set id Posyandu sesuai dengan kelurahan/ desa yang dipilih
				if(namaKelurahan.equals("Gudang Kahuripan"))
				{
					idPosyandu = "1";
				}
				else if(namaKelurahan.equals("Baros"))
				{
					idPosyandu = "2";
				}	
				
				String id = idPosyandu;

				Intent start_strata = new Intent(PerkembanganPosyandu.this, Strata.class);
				start_strata.putExtra("id", id);
				start_strata.putExtra("kelurahan", namaKelurahan); //namaKelurahan ditampung di "variabel" kelurahan
				start_strata.putExtra("admin", admin);
		    	startActivity(start_strata);
		    	Toast.makeText(PerkembanganPosyandu.this, "Anda memilih Kelurahan/Desa " + namaKelurahan ,Toast.LENGTH_LONG).show();
	        }
		});
		
		btnKader = (Button) findViewById(R.id.kader);
		btnKader.setOnClickListener(new Button.OnClickListener()
		{	
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				String namaKelurahan = kelurahan[spin.getSelectedItemPosition()];
				
				//Set id Posyandu sesuai dengan kelurahan/ desa yang dipilih
				if(namaKelurahan.equals("Gudang Kahuripan"))
				{
					idPosyandu = "1";
				}
				else if(namaKelurahan.equals("Baros"))
				{
					idPosyandu = "2";
				}	
				
				String id = idPosyandu;
				
				Intent start_kader = new Intent(PerkembanganPosyandu.this, Kader.class);
				start_kader.putExtra("id", id);
				start_kader.putExtra("kelurahan", namaKelurahan); //namaKelurahan ditambung di "variabel" kelurahan
				start_kader.putExtra("admin", admin);
		    	startActivity(start_kader);
		    	Toast.makeText(PerkembanganPosyandu.this, "Anda memilih Kelurahan/Desa" + namaKelurahan ,Toast.LENGTH_LONG).show();
	        }
		});
		
		btnProgPokok = (Button) findViewById(R.id.prog_pokok);
		btnProgPokok.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				String namaKelurahan = kelurahan[spin.getSelectedItemPosition()];
				
				//Set id Posyandu sesuai dengan kelurahan/ desa yang dipilih
				if(namaKelurahan.equals("Gudang Kahuripan"))
				{
					idPosyandu = "1";
				}
				else if(namaKelurahan.equals("Baros"))
				{
					idPosyandu = "2";
				}	
				
				String id = idPosyandu;
				
				Intent program_pokok = new Intent(PerkembanganPosyandu.this, ProgramPokok.class);
				program_pokok.putExtra("id", id);
				program_pokok.putExtra("kelurahan", namaKelurahan); //namaKelurahan ditambung di "variabel" kelurahan
				program_pokok.putExtra("admin", admin);
		    	startActivity(program_pokok);
		    	Toast.makeText(PerkembanganPosyandu.this, "Anda memilih Kelurahan/Desa" + namaKelurahan ,Toast.LENGTH_LONG).show();
	        }
		});
		
		btnBack = (Button) findViewById(R.id.kembali);
		btnBack.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent back = new Intent(PerkembanganPosyandu.this, report.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
	        }
		});
		
		spin = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kelurahan);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
	}
	
	public void onClick(View v) 
	{
		if( v.equals(btnStrata) || v.equals(btnKader) || v.equals(btnProgPokok) )
		{
			mAdapter.add(kelurahan[spin.getSelectedItemPosition()]);
		}
	}
}