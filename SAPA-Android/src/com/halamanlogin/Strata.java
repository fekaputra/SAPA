package com.halamanlogin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Strata extends Activity {
	
	private EditText new_pratama, new_madya, new_purnama, new_mandiri;
	private TextView jmlStrata;
	private Button btnHitung, btnSubmit;
	
	private String url = Referensi.url + "/insertStrata.php";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.strata);
		
		//Menampilkan Id Kelurahan/Desa yang dipilih User
		final TextView idKelurahan = (TextView) findViewById(R.id.idKelurahan);	
		
		//Mengambil data yang diintent dari activity sebelumnya
		Intent i = getIntent();
		String id = i.getStringExtra("id");
				
    	idKelurahan.setText(id);
		
		new_pratama = (EditText) findViewById(R.id.new_pratama);
		new_madya = (EditText) findViewById(R.id.new_madya);
		new_purnama =  (EditText) findViewById(R.id.new_purnama);
		new_mandiri =  (EditText) findViewById(R.id.new_mandiri);
		
		jmlStrata =  (TextView) findViewById(R.id.jumlahStrata);
		
		btnHitung = (Button) findViewById(R.id.hitung_strata);
		btnHitung.setOnClickListener(new View.OnClickListener() 
	    {
			//@Override
			public void onClick(View v) 
			{
				int jml_pratama = Integer.parseInt(new_pratama.getText().toString());
				int jml_madya = Integer.parseInt(new_madya.getText().toString());
				int jml_purnama = Integer.parseInt(new_purnama.getText().toString());
				int jml_mandiri = Integer.parseInt(new_mandiri.getText().toString());
				int jumlahStrata = jml_pratama + jml_madya + jml_purnama + jml_mandiri;
				
				jmlStrata.setText(String.valueOf(jumlahStrata));
			}
		});
		
		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				try {
					String idPosyandu = URLEncoder.encode(idKelurahan.getText().toString(), "utf-8");
					String pratama = URLEncoder.encode(new_pratama.getText().toString(), "utf-8");
					String madya = URLEncoder.encode(new_madya.getText().toString(), "utf-8");
					String purnama = URLEncoder.encode(new_purnama.getText().toString(), "utf-8");
					String mandiri = URLEncoder.encode(new_mandiri.getText().toString(), "utf-8");
					String jumlahStrata = URLEncoder.encode(jmlStrata.getText().toString(), "utf-8");
					
					url += "?idPosyandu="+ idPosyandu + "&pratama=" + pratama + "&madya=" + madya + "&purnama=" + purnama + "&mandiri=" + mandiri + "&jumlahStrata=" + jumlahStrata;

					getRequest(url);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Method untuk Mengirimkan data kes erver event by button login diklik
	 *
	 * @param view
	 */
	public void getRequest(String Url) 
	{
		//Toast.makeText(this, "Tambah Data " + Url + " ", Toast.LENGTH_SHORT).show();
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try 
		{	
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Tambah Data " + request(response), Toast.LENGTH_SHORT).show();
			
			//Pindah ke halaman Perkembangan Posyandu
			Intent home = new Intent (Strata.this, PerkembanganPosyandu.class);
			startActivity(home);
			
		} 
		catch (Exception ex) 
		{
			Toast.makeText(this, "Tambah Data Gagal !", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Method untuk Menenrima data dari server
	 *
	 * @param response
	 * @return
	 */
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
		} catch (Exception ex) {
			result = "Error";
		}
		return result;
	}
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}

