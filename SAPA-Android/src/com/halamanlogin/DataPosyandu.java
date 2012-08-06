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
import android.widget.Toast;

public class DataPosyandu extends Activity 
{
	private EditText new_kecamatan, new_desa, new_kelurahan, new_jmlBangunan, new_s, new_k, new_d, new_n, new_progPengembangan;
	private Button btnSubmit;
	private String url = Referensi.url + "/insertPerkembanganPosyandu.php";

	ValidasiInsert validasi = new ValidasiInsert();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_posyandu);
		
		new_kecamatan = (EditText) findViewById(R.id.new_kecamatan);
        validasi.message(new_kecamatan);
		new_desa = (EditText) findViewById(R.id.new_desa);
        validasi.message(new_desa);
		new_kelurahan = (EditText) findViewById(R.id.nama_kelurahan);
        validasi.message(new_kelurahan);
		
		new_jmlBangunan = (EditText) findViewById(R.id.new_jml_bangunan);
		new_s = (EditText) findViewById(R.id.new_s);
		new_k = (EditText) findViewById(R.id.new_k);
		new_d = (EditText) findViewById(R.id.new_d);
		new_n = (EditText) findViewById(R.id.new_n);
		new_progPengembangan = (EditText) findViewById(R.id.new_progPengembangan);

		final EditText[] editText = new EditText[] {new_kecamatan, new_desa, new_kelurahan};

		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				try 
				{
					//call class validation insert to insert new data
					boolean check = validasi.validation(editText);//validation(editText);
					if(check == false)
					{
						Toast.makeText(DataPosyandu.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
						validasi.messages(editText);
					}
					else
					{
						String kecamatan = URLEncoder.encode(new_kecamatan.getText().toString(), "utf-8");
						String desa = URLEncoder.encode(new_desa.getText().toString(), "utf-8");
						String kelurahan = URLEncoder.encode(new_kelurahan.getText().toString(), "utf-8");
						String jmlBangunan = URLEncoder.encode(new_jmlBangunan.getText().toString(), "utf-8");
						String s = URLEncoder.encode(new_s.getText().toString(), "utf-8");
						String k = URLEncoder.encode(new_k.getText().toString(), "utf-8");
						String d = URLEncoder.encode(new_d.getText().toString(), "utf-8");
						String n = URLEncoder.encode(new_n.getText().toString(), "utf-8");
						String progPengembangan = URLEncoder.encode(new_progPengembangan.getText().toString(), "utf-8");
						
						url += "?kecamatan="+ kecamatan+ "&desa=" + desa + "&kelurahan=" + kelurahan + "&jmlBangunan=" + jmlBangunan + "&s=" + s + "&k=" + k + "&d=" + d + "&n=" + n + "&progPengembangan=" + progPengembangan;

						getRequest(url);
					}
				} 
				catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
			}
		});

	}
	
	public void message (EditText record)
	{
		EditText valid = record;
		if (valid.getText().toString().length() == 0)
		{
			valid.setError("This Record is Required");
		}
	}

	/**
	 * Method untuk Mengirimkan data keserver event by button submit diklik
	 */
	public void getRequest(String Url) 
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try 
		{
			HttpResponse response = client.execute(request);
			Toast.makeText(this, "Insert data " + request(response), Toast.LENGTH_SHORT).show();
			Intent home = new Intent (DataPosyandu.this, PerkembanganPosyandu.class);
			startActivity(home);
		} 
		catch (Exception ex) 
		{
			Toast.makeText(this, "Tambah Data Gagal !", Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * Method untuk Menenrima data dari server
	 */
	public static String request(HttpResponse response) 
	{
		String result = "";

		try 
		{
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) 
			{
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
	}
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}