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

public class Kader extends Activity {
	
	private EditText new_totalKader, new_kaderTerlatih;
	private Button btnSubmit;
	
	private String url = Referensi.url + "/insertKader.php";
	
	ValidasiInsert validasi = new ValidasiInsert();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kader);
		
		//Menampilkan Id Kelurahan/Desa yang dipilih User
		final TextView idKelurahan = (TextView) findViewById(R.id.idKelurahan);	
		
		//Mengambil data yang diintent dari activity sebelumnya
		Intent i = getIntent();
		String id = i.getStringExtra("id");
				
    	idKelurahan.setText(id);
		
    	new_totalKader = (EditText) findViewById(R.id.jml_total);
		validasi.message(new_totalKader);
		new_kaderTerlatih = (EditText) findViewById(R.id.kader_terlatih);
		validasi.message(new_kaderTerlatih);
		
		final EditText[] editText = new EditText[] {new_totalKader, new_kaderTerlatih};
		
		
		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				try {
					boolean check = validasi.validation(editText);//validation(editText);
					if(check == false)
					{
						Toast.makeText(Kader.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
						validasi.messages(editText);
					}
					else
					{
						String idPosyandu = URLEncoder.encode(idKelurahan.getText().toString(), "utf-8");
						String totalKader = URLEncoder.encode(new_totalKader.getText().toString(), "utf-8");
						String kaderTerlatih = URLEncoder.encode(new_kaderTerlatih.getText().toString(), "utf-8");
						url += "?idPosyandu="+ idPosyandu + "&totalKader=" + totalKader + "&kaderTerlatih=" + kaderTerlatih;

						getRequest(url);
					}
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
			Intent home = new Intent (Kader.this, PerkembanganPosyandu.class);
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
}
