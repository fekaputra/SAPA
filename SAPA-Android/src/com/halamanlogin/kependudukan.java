package com.halamanlogin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;;

public class kependudukan extends ListActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // storing string resources into Array
        String[] data_penduduk = getResources().getStringArray(R.array.data_penduduk);
        
        // Binding Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.kependudukan, R.id.label, data_penduduk));
        
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
        	  
        	  if(data.equals("Jumlah Penduduk (jiwa)"))
        	  {
        		  Intent jml = new Intent(kependudukan.this, DPJenisKelamin.class);
  				  jml.putExtra("admin", admin);
  				  startActivity(jml);
        	  }
        	  else if(data.equals("Kepadatan Penduduk (jiwa/km2)"))
        	  {
        		  Intent kepadatan = new Intent(kependudukan.this, kependudukan.class);
        		  kepadatan.putExtra("admin", admin);
  				  startActivity(kepadatan);
        	  }
        	  else if(data.equals("Jumlah Penduduk Berdasarkan Jenis Kelamin (jiwa)"))
        	  {
        		  Intent dp_jk = new Intent(kependudukan.this, kependudukan.class);
        		  dp_jk.putExtra("admin", admin);
  				  startActivity(dp_jk);
        	  }
        	  else if (data.equals("Kembali")) 
  			 {
  			  	  Intent back = new Intent(kependudukan.this, gis.class);
  				  back.putExtra("admin", admin);
  				  startActivity(back);
  			 } 
        	  
          }
        });
    }
}

/*package com.halamanlogin;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class kependudukan extends ListActivity 
{
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		//setContentView(R.layout.kependudukan);

		// Create an array of Strings, that will be put to our ListActivity
		String[] menu = new String[] { "Data Penduduk Menurut Jenis Kelamin", "Data Penduduk Menurut Kelompok Usia", "Kembali" };

		// Menset nilai array ke dalam list adapater sehingga data pada array
		// akan dimunculkan dalam list
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
	}

	@Override
	/**method ini akan mengoveride method onListItemClick
	 * yang ada pada class List Activity
	 * method ini akan dipanggil apabilai ada salah satu item
	 * dari list menu yang dipilih
	 
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String pilihan = o.toString();
		tampilkanPilihan(pilihan);
	}

	protected void tampilkanPilihan(String pilihan) {
		Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
		try {
			//Intent i = null;
			if (pilihan.equals("Data Penduduk Menurut Jenis Kelamin")) 
			{
				Intent dp_jk = new Intent(this, DPJenisKelamin.class);
				dp_jk.putExtra("admin", admin);
				startActivity(dp_jk);
			} 
			else if (pilihan.equals("Data Penduduk Menurut Kelompok Usia")) 
			{
				Intent dp_usia = new Intent(this, DPUsia.class);
				dp_usia.putExtra("admin", admin);
				startActivity(dp_usia);
			} 
			else if (pilihan.equals("Kembali")) 
			{
				Intent back = new Intent(this, gis.class);
				back.putExtra("admin", admin);
				startActivity(back);
			} 
			else 
			{
				Toast.makeText(this,"Anda Memilih: " + pilihan + " , " + "Actionnya belum dibuat", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}*/