package com.halamanlogin;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MotionEvent;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem>
{
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	   
	private Context context;
	   
	String[] menu = {"Kependudukan", "Kolega", "Kesehatan"};
	String sJwban;
	int noSoal;
	
	String user;
	
	public String getUser()
	{
		return user;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
		   
	public AddItemizedOverlay(Drawable defaultMarker) 
	{
		super(boundCenterBottom(defaultMarker));
	}
	   
	public AddItemizedOverlay(Drawable defaultMarker, Context context) 
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
		int numChoiceAns = menu.length;
		CharSequence[] opsia = new CharSequence[numChoiceAns];
		   
		final OverlayItem item = mapOverlays.get(index);
		 
		for (int i = 0; i < numChoiceAns; i++) 
		{
			opsia[i] = menu[i];
		}
		   
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(item.getTitle());
		final String id = item.getSnippet();
		dialog.setItems(opsia, new DialogInterface.OnClickListener() 
		{				
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				sJwban = menu[which];
				String user = getUser();
				
				if(sJwban.equals("Kependudukan"))
				{ 
					Intent intent = new Intent(context, Kependudukan.class);  
				    intent.putExtra("id", id);
				    intent.putExtra("admin", user);
				    context.startActivity(intent);
				}
				else if(sJwban.equals("Kolega"))
				{
					Intent intent = new Intent(context, Kolega.class);  
				    intent.putExtra("id", id);
				    intent.putExtra("admin", user);
				    context.startActivity(intent);
				}
				else if(sJwban.equals("Kesehatan"))
				{
					Intent intent = new Intent(context, Kesehatan.class);  
				    intent.putExtra("id", id);
				    intent.putExtra("admin", user);
				    context.startActivity(intent);
				}
			}
		}).show();
		return true;
	}
}
	