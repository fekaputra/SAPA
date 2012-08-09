package com.halamanlogin;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class ItemKolega extends MapActivity 
{	
    User user = new User();
    
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_gis);
        
        Intent intent = getIntent();
		String admin = intent.getStringExtra("admin");
		
		//set user dari data yang dikirim class sebelumnya
		user.setUser(admin);
        
        // Menampilkan kontrol zoom
		MapView mapView = (MapView) findViewById(R.id.mapView);
		mapView.setBuiltInZoomControls(true);
		      
		/**
		 * Mengubah tipe peta
		 * */
		//mapView.setSatellite(true); // Satellite View
		//mapView.setStreetView(true); // Street View
		//mapView.setTraffic(true); // Traffic view
		
		//Mengambil data yang dikirim dari class Kolega
		Intent i = getIntent();
		String NamaKolega = i.getStringExtra("NamaKolega");
		String AlamatTelp = i.getStringExtra("AlamatTelp");
		String latitude = i.getStringExtra("latitude");
		String longitude = i.getStringExtra("longitude");
		        
		/**
		 * Menampilkan lokasi dengan Latitude dan Longitude yang dikirim dari class Kolega
		 * */        
		MapController mc = mapView.getController();
		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);
		        
		GeoPoint geoPoint = new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6));
		mc.animateTo(geoPoint);
		mc.setZoom(18);
		mapView.invalidate(); 
		        
		/**
		 * Penempatan Marker
		 * */
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.mark_red_mdpi_2);
		InformasiKolega itemizedOverlay = new InformasiKolega(drawable, this);
		        
		OverlayItem overlayitem = new OverlayItem(geoPoint, NamaKolega, AlamatTelp);
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
    }	
					
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	
	//Action jika tombol back di tekan
    @Override
    public void onBackPressed() 
	{
    	String admin = user.getUser();
    	
    	Intent back = new Intent (ItemKolega.this, gis.class);
    	back.putExtra("admin", admin);
    	startActivity(back);
	}
}