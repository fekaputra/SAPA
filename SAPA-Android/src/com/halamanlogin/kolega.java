package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class kolega extends Activity 
{
	/*Variabel global*/
	
	private Button pilih, new_kolega;
	private ListView lv;
	private String[] data_kolega;
		
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kolega);
        
        //data_kolega diambil dari list item di file string.xml
        data_kolega = getResources().getStringArray(R.array.data_kolega);
        
        //menampilkan list item
        lv = (ListView)findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, data_kolega));     
 
        //menampilkan list item dengan mode multiple choice
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        
        //action untuk button Insert Data Kolega Baru
        new_kolega = (Button)findViewById(R.id.btnNewKolega); 
        new_kolega.setOnClickListener(new Button.OnClickListener()
		{
			//mengambil parameter yang dikirim activity lain
        	Intent i = getIntent();
        	
        	//menampung data dari parameter admin yang dikirim activity sebelumnya
			String admin = i.getStringExtra("admin");
			
			public void onClick(View v)
	        {
				//berpindah ke activity newKolega
				Intent start_new_kolega = new Intent(kolega.this, newKolega.class);
				
				//mengirimkan parameter admin untuk activity selanjutnya
				start_new_kolega.putExtra("admin", admin);
				
				//memulai activity newKolega
		    	startActivity(start_new_kolega);
	        }
		});
 
        //action untuk button Pilih
        pilih = (Button)findViewById(R.id.btn_pilih); 
        pilih.setOnClickListener(new Button.OnClickListener()
        {
        	//mengambil parameter yang dikirim activity lain
        	Intent i = getIntent();
        	
        	//menampung data dari parameter admin yang dikirim activity sebelumnya
			String admin = i.getStringExtra("admin");
			
        	public void onClick(View v) 
            {
                String selected = "";
                int cntChoice = lv.getCount();
 
                //menampung item yang diberi tanda check
                SparseBooleanArray sparseBooleanArray = lv.getCheckedItemPositions();
 
                for(int i = 0; i < cntChoice; i++)
                {
                    if(sparseBooleanArray.get(i)) 
                    {
                        selected += lv.getItemAtPosition(i).toString() + "\n";
                    }
                }
                
                //menampilkan hasil penampung di activity gis
                Intent peta = new Intent(kolega.this, gis.class);
    	       	peta.putExtra("admin", admin);
    	       	peta.putExtra("selected", selected);
    	       	startActivity(peta);
            }
         });    
    }
	
	@Override
	public void onBackPressed() 
	{
		//mengambil parameter yang dikirim activity lain
    	Intent i = getIntent();
    	
    	//menampung data dari parameter admin yang dikirim activity sebelumnya
		String admin = i.getStringExtra("admin");
		
		Intent back = new Intent (kolega.this, gis.class);
		back.putExtra("admin", admin);
    	startActivity(back);
	}
}