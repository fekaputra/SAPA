package com.halamanlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class gis extends MapActivity 
{	
	//Membuat objek JSON Parser
    JSONParser jParser = new JSONParser();
    
    private static String url_all_files = Referensi.url + "/listmap.php";
    
    //nama-nama node JSON
    private static final String TAG_ListMap = "listmap";
    private static final String TAG_KotaKabupaten = "kota_kabupaten";
    private static final String TAG_Latitude = "latitude";
    private static final String TAG_Longitude = "longitude";
    private static final String TAG_IdKotaKab = "idKotaKab";
    
    JSONArray file = null;
    
    User user = new User();
	    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_gis);
		
		//Membuat parameter
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		//mendapatkan string JSON dari url
		JSONObject json = jParser.makeHttpRequest(url_all_files, "GET", params);
		
		Intent intent = getIntent();
		String admin = intent.getStringExtra("admin");
		
		//set user dari data yang dikirim class sebelumnya
		user.setUser(admin);
					
		try
		{
			//Mendapatkan array of product (list data yang ada di tabel data_map di DB)
			file = json.getJSONArray(TAG_ListMap);
			for (int i = 0; i < file.length(); i++)
			{
				JSONObject o = file.getJSONObject(i);
				
				//menyimpan setiap item json pada variabel
				String idKotaKab = o.getString(TAG_IdKotaKab).toString();
				String kota_kabupaten = o.getString(TAG_KotaKabupaten).toString();
				String latitude = o.getString(TAG_Latitude).toString();
				String longitude = o.getString(TAG_Longitude).toString();
				
				//membuat hashmap baru
				HashMap<String, String> map = new HashMap<String, String>();
				
				//menambahkan setiap child node ke hash map key => value
				map.put(TAG_KotaKabupaten, kota_kabupaten);
				map.put(TAG_Latitude, latitude);
				map.put(TAG_Longitude, longitude);
				map.put(TAG_IdKotaKab, idKotaKab);
				
				
				// Menampilkan kontrol zoom
		        MapView mapView = (MapView) findViewById(R.id.mapView);
		        mapView.setBuiltInZoomControls(true);
		        
		        //mapView.setStreetView(true);
		        
		        /**
		         * Menampilkan lokasi dengan Latitude dan Longitude yang ada di database
		         * */        
		        MapController mc = mapView.getController();
		        double lat = Double.parseDouble(latitude);
		        double lon = Double.parseDouble(longitude);
		        
		        GeoPoint geoPoint = new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6));
		        mc.animateTo(geoPoint);
		        mc.setZoom(12);
		        mapView.invalidate(); 
		        
		        /**
		         * Penempatan Marker
		         * */
		        List<Overlay> mapOverlays = mapView.getOverlays();
		        Drawable drawable = this.getResources().getDrawable(R.drawable.mark_red_mdpi_2);
		        AddItemizedOverlay itemizedOverlay = new AddItemizedOverlay(drawable, this);
		        
		        OverlayItem overlayitem = new OverlayItem(geoPoint, kota_kabupaten, idKotaKab); //kota_kabupaten : untuk set title di dialog title, idKotaKab : untuk set snippet 
		        itemizedOverlay.setUser(user.getUser());
		        itemizedOverlay.addOverlay(overlayitem);
		        mapOverlays.add(itemizedOverlay);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
    }

	@Override
	protected boolean isRouteDisplayed() 
	{
		return false;
	}
		
	//Action jika tombol back di tekan
	@Override
	public void onBackPressed() 
	{		
		String admin = user.getUser();
		//Toast.makeText(getBaseContext(), admin, Toast.LENGTH_SHORT).show();		
		if(admin.equals("ADMIN"))
		{
			Intent home = new Intent (gis.this, homeAdmin.class);
			home.putExtra("admin", admin);
	    	startActivity(home);
		}
		else
		{
			Intent home = new Intent (gis.this, homeMember.class);
			home.putExtra("admin", admin);
	    	startActivity(home);
		}
	}
}