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
	private Button pilih;
	private ListView lv;
	private String[] data_kolega;
		
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kolega);
        
        data_kolega = getResources().getStringArray(R.array.data_kolega);
        
        lv = (ListView)findViewById(R.id.list);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, data_kolega));     
 
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
 
        pilih = (Button)findViewById(R.id.btn_pilih); 
        pilih.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v) 
            {
            	Intent intent = getIntent();
        		String admin = intent.getStringExtra("admin");
        		
                String selected = "";
                int cntChoice = lv.getCount();
 
                SparseBooleanArray sparseBooleanArray = lv.getCheckedItemPositions();
 
                for(int i = 0; i < cntChoice; i++)
                {
                    if(sparseBooleanArray.get(i)) 
                    {
                        selected += lv.getItemAtPosition(i).toString() + "\n";
                    }
                }
                
                Intent peta = new Intent(kolega.this, gis.class);
    	       	peta.putExtra("admin", admin);
    	       	peta.putExtra("selected", selected);
    	       	startActivity(peta);
            }
         });    
    }
}