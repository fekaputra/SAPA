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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Kolega extends ListActivity 
{
	private ProgressDialog pDialog;
	
	//button untuk menampilkan progress dialog
    Button btnShowProgress;
    
    // tipe progress dialog (0 - for horizontal progress bar)
    public static final int progress_bar_type = 0;
    
    //Membuat objek JSON Parser
    JSONParser jParser = new JSONParser();
    
    ArrayList<HashMap<String, String>> dataList;
    
    private static String url_all_files = Referensi.url + "/listkolega.php";
    
    //nama-nama node JSON
    private static final String TAG_ListKolega = "listkolega";
    private static final String TAG_IdKotaKab = "idKotaKab";
    private static final String TAG_NamaKolega = "NamaKolega";
    private static final String TAG_Alamat = "Alamat";
    private static final String TAG_Telp = "Telp";
    private static final String TAG_Latitude = "latitude";
    private static final String TAG_Longitude = "longitude";
    
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
        
        ListView lv = getListView();
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
            {           	
            	String NamaKolega = ((TextView)view.findViewById(R.id.TextView01)).getText().toString();
            	String AlamatTelp = ((TextView)view.findViewById(R.id.TextView02)).getText().toString() + "\n" + ((TextView)view.findViewById(R.id.TextView03)).getText().toString();
            	String latitude = ((TextView)view.findViewById(R.id.latitude)).getText().toString();
            	String longitude = ((TextView)view.findViewById(R.id.longitude)).getText().toString();
            	           	            	
            	/**
            	 * Mengirimlan data Nama kolega, alamat+telp, latitude dan longitude ke class ItemKolega 
            	 * untuk menampilkan lokasi kolega yang dipilih oleh user dalam peta
            	 **/
            	Intent item = new Intent (Kolega.this, ItemKolega.class);
            	item.putExtra("NamaKolega", NamaKolega);
            	item.putExtra("AlamatTelp", AlamatTelp);
            	item.putExtra("latitude", latitude);
            	item.putExtra("longitude", longitude);
            	item.putExtra("admin", user.getUser() );
		    	startActivity(item);
            	
            }
        });
    }
    
    class LoadAllFiles extends AsyncTask<String, String, String>
    {    	
    	@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(Kolega.this);
			pDialog.setMessage ("Loading Data...Please Wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
    	
		protected String doInBackground(String... args) 
		{
			Intent intent = getIntent();
		    String id = intent.getStringExtra("id");
		    //String admin = intent.getStringExtra("admin");
		    
		    //Toast.makeText(getBaseContext(), admin , Toast.LENGTH_SHORT).show();
			
		    //Membuat parameter
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("id", id));
			
			//mendapatkan string JSON dari url
			JSONObject json = jParser.makeHttpRequest(url_all_files, "GET", params);
			
			try
			{
				//Mendapatkan array of product (list data yang ada di tabel data_kolega di DB)
				file = json.getJSONArray(TAG_ListKolega);
				for (int i = 0; i < file.length(); i++)
				{
					JSONObject o = file.getJSONObject(i);
					
					//menyimpan setiap item json pada variabel
					String idKotaKab = o.getString(TAG_IdKotaKab).toString();
					String NamaKolega = o.getString(TAG_NamaKolega).toString();
					String Alamat = o.getString(TAG_Alamat).toString();
					String Telp = o.getString(TAG_Telp).toString();
					String latitude = o.getString(TAG_Latitude).toString();
					String longitude = o.getString(TAG_Longitude).toString();
					
					//membuat hashmap baru
					HashMap<String, String> map = new HashMap<String, String>();
					
					//adding each child node to hash map key => value
					map.put(TAG_IdKotaKab, idKotaKab);
					map.put(TAG_NamaKolega, NamaKolega);
					map.put(TAG_Alamat, Alamat);
					map.put(TAG_Telp, Telp);
					map.put(TAG_Latitude, latitude);
					map.put(TAG_Longitude, longitude);
					
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
                            Kolega.this, dataList,
                            R.layout.list_kolega, new String[] { TAG_IdKotaKab, TAG_Latitude, TAG_Longitude, TAG_NamaKolega, TAG_Alamat, TAG_Telp},
                            new int[] { R.id.kabupaten_kota, R.id.latitude, R.id.longitude, R.id.TextView01, R.id.TextView02, R.id.TextView03 });
                    
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
    	
    	Intent back = new Intent (Kolega.this, Gis.class);
    	back.putExtra("admin", admin);
    	startActivity(back);
	}
}