package com.halamanlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class Kependudukan extends ListActivity 
{
	private ProgressDialog pDialog;
	
	//button untuk menampilkan progress dialog
    Button btnShowProgress;
    
    // tipe progress dialog (0 - for horizontal progress bar)
    public static final int progress_bar_type = 0;
    
    //Membuat objek JSON Parser
    JSONParser jParser = new JSONParser();
    
    ArrayList<HashMap<String, String>> dataList;
    
    private static String url_all_files = Referensi.url + "/listpenduduk.php";
    
    //nama-nama node JSON
    private static final String TAG_ListPenduduk = "listpenduduk";
    private static final String TAG_IdKotaKab = "idKotaKab";
    private static final String TAG_JmlPenduduk = "jml_penduduk";
    private static final String TAG_KepadatanPenduduk = "kepadatan_penduduk";
    
    JSONArray file = null;
    
    User user = new User();
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.all_files);
        
        dataList = new ArrayList<HashMap<String, String>>();
        
        Intent intent = getIntent();
		String admin = intent.getStringExtra("admin");
		
		//set user dari data yang dikirim class sebelumnya
		user.setUser(admin);
        
        //loading all files in the background
        new LoadAllFiles().execute();
    }
    
    class LoadAllFiles extends AsyncTask<String, String, String>
    {    	
    	@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(Kependudukan.this);
			pDialog.setMessage ("Loading Data...Please Wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
    	
		protected String doInBackground(String... args) 
		{
			Intent intent = getIntent();
		    String id = intent.getStringExtra("id");
			
		    //Membuat parameter
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			//Mengirimkan id sebagai idKotaKab ke php
			params.add(new BasicNameValuePair("id", id));
			
			//mendapatkan string JSON dari url
			JSONObject json = jParser.makeHttpRequest(url_all_files, "GET", params);
			
			try
			{
				//Mendapatkan array of product (list data yang ada di tabel data_kependudukan di DB)
				file = json.getJSONArray(TAG_ListPenduduk);
				for (int i = 0; i < file.length(); i++)
				{
					JSONObject o = file.getJSONObject(i);
					
					//menyimpan setiap item json pada variabel
					String idKotaKab = o.getString(TAG_IdKotaKab).toString();
					String jml_penduduk = o.getString(TAG_JmlPenduduk).toString();
					String kepadatan_penduduk = o.getString(TAG_KepadatanPenduduk).toString();
					
					//membuat hashmap baru
					HashMap<String, String> map = new HashMap<String, String>();
					
					//menambahkan setiap child node ke hash map key => value
					map.put(TAG_IdKotaKab, idKotaKab);
					map.put(TAG_JmlPenduduk, jml_penduduk);
					map.put(TAG_KepadatanPenduduk, kepadatan_penduduk);
					
					//menambahkan hashlist ke array list
					dataList.add(map);
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(String file_url) 
		{
            // dismiss dialog setelah mendapatkan semua "produk"
            pDialog.dismiss();
            // updating UI dari Background Thread
            runOnUiThread(new Runnable() 
            {
                public void run() 
                {
                    /**
                     * Updating parsed JSON data kedalam ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            Kependudukan.this, dataList,
                            R.layout.list_kependudukan, new String[] { TAG_IdKotaKab, TAG_JmlPenduduk, TAG_KepadatanPenduduk},
                            new int[] { R.id.kabupaten_kota, R.id.TextView01, R.id.TextView02 });
                    
                    // updating listview
                    setListAdapter(adapter);
                }
            });
        }
    } 
    
    //Action jika tombol back di tekan
    @Override
    public void onBackPressed() 
	{
    	String admin = user.getUser();
    	
    	Intent back = new Intent (Kependudukan.this, Gis.class);
    	back.putExtra("admin", admin);
    	startActivity(back);
	}
}