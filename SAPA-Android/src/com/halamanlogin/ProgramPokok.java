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

public class ProgramPokok extends Activity {
	
	private EditText new_kb, new_kia, new_gizi, new_imunisasi, new_p2d;
	private Button btnSubmit;
	
	// Seusuaikan url dengan nama domain yang anda gunakan
	private String url = Referensi.url + "/insertProgramPokok.php";
	
	/**
	 * Method yang dipanggil pada saat aplikaasi dijalankan
	 * */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.program_pokok);
		
		//Menampilkan Id Kelurahan/Desa yang dipilih User
		final TextView idKelurahan = (TextView) findViewById(R.id.idKelurahan);	
		
		//Mengambil data yang diintent dari activity sebelumnya
		Intent i = getIntent();
		String id = i.getStringExtra("id");
				
    	idKelurahan.setText(id);
		
		new_kb = (EditText) findViewById(R.id.new_kb);
		new_kia = (EditText) findViewById(R.id.new_kia);
		new_gizi =  (EditText) findViewById(R.id.new_gizi);
		new_imunisasi =  (EditText) findViewById(R.id.new_imunisasi);
		new_p2d =  (EditText) findViewById(R.id.new_p2d);
		
		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			//@Override
			public void onClick(View v) 
			{

				try {
					// setiap parameter yang akan dikirim melalui http
					// harus encode agar
					// dapat terbaca dengan baik oleh server
					String idPosyandu = URLEncoder.encode(idKelurahan.getText().toString(), "utf-8");
					String kb = URLEncoder.encode(new_kb.getText().toString(), "utf-8");
					String kia = URLEncoder.encode(new_kia.getText().toString(), "utf-8");
					String gizi = URLEncoder.encode(new_gizi.getText().toString(), "utf-8");
					String imunisasi = URLEncoder.encode(new_imunisasi.getText().toString(), "utf-8");
					String p2d = URLEncoder.encode(new_p2d.getText().toString(), "utf-8");
					
					url += "?idPosyandu="+ idPosyandu + "&kb=" + kb + "&kia=" + kia + "&gizi=" + gizi + "&imunisasi=" + imunisasi + "&p2d=" + p2d;

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
			//Menampilkan data yang diinputkan user
			/*String toastMessage = 
					"Total Kader 	: " + new_totalKader.getText().toString() + "\n" +
					"Kader Terlatih : " + new_kaderTerlatih.getText().toString();*/
			
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Tambah Data " + request(response), Toast.LENGTH_SHORT).show();
			
			//Pindah ke halaman Perkembangan Posyandu
			Intent home = new Intent (ProgramPokok.this, PerkembanganPosyandu.class);
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
