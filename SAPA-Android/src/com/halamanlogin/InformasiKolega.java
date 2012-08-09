package com.halamanlogin;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class InformasiKolega extends ItemizedOverlay<OverlayItem>
{
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	   
	private Context context;
	  
	String[] menu = {"Kependudukan", "Kolega", "Kesehatan"};
	String sJwban;
	int noSoal;
	
	User user = new User();
	   
	public InformasiKolega(Drawable defaultMarker) 
	{
		super(boundCenterBottom(defaultMarker));
	}
	   
	public InformasiKolega(Drawable defaultMarker, Context context) 
	{
		this(defaultMarker);
	    this.context = context;
	}
	
	@Override
	protected OverlayItem createItem(int i) 
	{
		return mapOverlays.get(i);
	}

	@Override
	public int size() 
	{
		return mapOverlays.size();
	}
	   
	public void addOverlay(OverlayItem overlay) 
	{
		mapOverlays.add(overlay);
	    this.populate();
	}
	   
	/**
	 * Getting Latitude and Longitude on Touch event
	 **/
	@Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) 
    {                         
        return false;
    }
	   
	@Override
	protected boolean onTap(int index) 
	{		
		OverlayItem item = mapOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.setPositiveButton("OK", new OnClickListener() 
		{
			@Override
			public void onClick(DialogInterface arg0, int arg1) 
			{
				String admin = user.getUser();	
				
				//set user dari data yang dikirim class sebelumnya
				user.setUser(admin);
				arg0.dismiss();
			}
		});
		dialog.show();
		return true;
	}
}