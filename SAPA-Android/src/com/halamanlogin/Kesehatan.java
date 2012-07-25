package com.halamanlogin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Kesehatan extends ListActivity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // storing string resources into Array
        String[] data_kesehatan = getResources().getStringArray(R.array.data_kesehatan);
        
        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.kesehatan, R.id.label, data_kesehatan));
        
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
      		  
        	  if(data.equals("Jumlah Penderita TB Paru"))
        	  {
        		  Intent TB = new Intent(Kesehatan.this, Kesehatan.class);
        		  TB.putExtra("admin", admin);
  				  startActivity(TB);
        	  }
        	  else if(data.equals("Insiden Flu Burung (Avian Influenza)"))
        	  {
        		  Intent i_fluburung = new Intent(Kesehatan.this, Kesehatan.class);
        		  i_fluburung.putExtra("admin", admin);
  				  startActivity(i_fluburung);
        	  }
        	  else if(data.equals("Jumlah Penderita Flu Burung (Avian Influenza)"))
        	  {
        		  Intent fluburung = new Intent(Kesehatan.this, Kesehatan.class);
        		  fluburung.putExtra("admin", admin);
  				  startActivity(fluburung);
        	  }
        	  else if(data.equals("Insiden DBD"))
        	  {
        		  Intent i_dbd = new Intent(Kesehatan.this, Kesehatan.class);
        		  i_dbd.putExtra("admin", admin);
  				  startActivity(i_dbd);
        	  }
        	  else if(data.equals("Jumlah Penderita DBD"))
        	  {
        		  Intent dbd = new Intent(Kesehatan.this, Kesehatan.class);
        		  dbd.putExtra("admin", admin);
  				  startActivity(dbd);
        	  }
        	  else if (data.equals("Kembali")) 
  			  {
  			  	  Intent back = new Intent(Kesehatan.this, gis.class);
  				  back.putExtra("admin", admin);
  				  startActivity(back);
  			  } 
        	  
          }
        });
    }
}