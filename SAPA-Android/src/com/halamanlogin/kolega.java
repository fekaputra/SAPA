package com.halamanlogin;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class kolega extends ListActivity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // storing string resources into Array
        String[] data_kolega = getResources().getStringArray(R.array.data_kolega);
        
        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.kolega, R.id.label, data_kolega));
        
        ListView lv = getListView();
        
        // listening to single list item on click
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
          {
        	  // selected item 
        	  String data = ((TextView) view).getText().toString();
        	  
        	  /*
        	  // Launching new Activity on selecting single List Item
        	  Intent i = new Intent(getApplicationContext(), ListItemGis.class);
        	  // sending data to new activity
        	  i.putExtra("data", data);
        	  startActivity(i);*/
        	  Intent i = getIntent();
      		  String admin = i.getStringExtra("admin");
      		  
        	  if(data.equals("Input Data Kolega Baru"))
        	  {
        		  Intent kolega_baru = new Intent(kolega.this, newKolega.class);
        		  kolega_baru.putExtra("admin", admin);
  				  startActivity(kolega_baru);
        	  }
        	  else if(data.equals("Daftar Tenaga Medis"))
        	  {
        		  Intent t_medis = new Intent(kolega.this, kolega.class);
        		  t_medis.putExtra("admin", admin);
  				  startActivity(t_medis);
        	  }
        	  else if(data.equals("Daftar Dokter Spesialis"))
        	  {
        		  Intent spesialis = new Intent(kolega.this, kolega.class);
        		  spesialis.putExtra("admin", admin);
  				  startActivity(spesialis);
        	  }
        	  else if(data.equals("Daftar Puskesmas"))
        	  {
        		  Intent puskesmas = new Intent(kolega.this, kolega.class);
        		  puskesmas.putExtra("admin", admin);
  				  startActivity(puskesmas);
        	  }
        	  else if (data.equals("Kembali")) 
  			  {
  			  	  Intent back = new Intent(kolega.this, gis.class);
  				  back.putExtra("admin", admin);
  				  startActivity(back);
  			  } 
        	  
          }
        });
    }
}