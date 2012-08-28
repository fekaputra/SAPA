package com.halamanlogin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class LaporanKegPKPKecActivity extends Activity 
{	
	// Progress Dialog
    private ProgressDialog pDialog;
 
    JSONParser jsonParser = new JSONParser();
    EditText textKec, textBulan, textUraianKeg, textTempat, textUnsur, textMasalah, textRTL;
    DatePicker textTanggal;
    
    //url to insert new data
    private static String url_insert = Referensi.url + "/insert_keg_pkp_kec.php";
    
    //json Node names
    private static final String TAG_SUCCESS = "success";
    
    ValidasiInsert validasi = new ValidasiInsert();
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertlapkegpkpkec);
        
        //edit text
        textKec  = (EditText) findViewById(R.id.editKec);
        message(textKec);
        
        textBulan  = (EditText) findViewById(R.id.editBulan);
        message(textBulan);
        
        textTanggal  = (DatePicker) findViewById(R.id.dateTanggal);
        
        textUraianKeg  = (EditText) findViewById(R.id.editUraianKeg);
        message(textUraianKeg);
        
        textTempat  = (EditText) findViewById(R.id.editTempat);
        message(textTempat);
        
        textUnsur  = (EditText) findViewById(R.id.editUnsur);
        message(textUnsur);
        
        textMasalah  = (EditText) findViewById(R.id.editMasalah);
        message(textMasalah);
        
        textRTL  = (EditText) findViewById(R.id.editRTL);
        message(textRTL);
        
        final EditText[] editText = new EditText[] {textKec, textBulan, textUraianKeg, textTempat, textUnsur, textMasalah, textRTL};
        
        //button insert
        Button buttonInsert = (Button) findViewById(R.id.kirim);
        
        //button click event
        buttonInsert.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				//call class validation insert to insert new data
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(LaporanKegPKPKecActivity.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					new InsertDataLapKegPKPKec().execute();
				}
			}
		});
        
        Button back = (Button) findViewById(R.id.kembali);
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent home_report = new Intent(LaporanKegPKPKecActivity.this, Report.class);
				home_report.putExtra("admin", admin);
		    	startActivity(home_report);
	        }
		});
    }
    
    public void message (EditText record)
	{
		EditText valid = record;
		if (valid.getText().toString().length() == 0)
		{
			valid.setError("This Record is Required");
		}
	}
    
    class InsertDataLapKegPKPKec extends AsyncTask <String, String, String>
    {
    	/**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() 
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(LaporanKegPKPKecActivity.this);
            pDialog.setMessage("Insert Data...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

		//insert data
		protected String doInBackground(String... args) 
		{
			String kecamatan = textKec.getText().toString();
			
			//problem in adding month, because in the database is int, but in here string
			//int bulan = Integer.parseInt(textBulan.getText().toString());
			String bulan = textBulan.getText().toString();
			
			//get from datepicker
			int day = textTanggal.getDayOfMonth();
			int month = textTanggal.getMonth() + 1;
			int year = textTanggal.getYear();
			String tanggal = day + "-" + month + "-" + year;
			
			String uraianKeg = textUraianKeg.getText().toString();
			String tempat = textTempat.getText().toString();
			String unsur = textUnsur.getText().toString();
			String masalah = textMasalah.getText().toString();
			String rtl = textRTL.getText().toString();
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair ("kecamatan", kecamatan));
			params.add(new BasicNameValuePair ("bulan", bulan));
			params.add(new BasicNameValuePair ("tanggal", tanggal));
			params.add(new BasicNameValuePair ("uraianKeg", uraianKeg));
			params.add(new BasicNameValuePair ("tempat", tempat));
			params.add(new BasicNameValuePair ("unsurTerlibat", unsur));
			params.add(new BasicNameValuePair ("masalah", masalah));
			params.add(new BasicNameValuePair ("rtl", rtl));
			
			//getting json object
			//using post method
			JSONObject json = jsonParser.makeHttpRequest(url_insert, "POST", params);
			
			// check for success tag
            try 
            {
                int success = json.getInt(TAG_SUCCESS);
 
                if (success == 1) 
                {
                	// successfully created product
                	//Toast.makeText(LaporanKegPKPKecActivity.this, "Data Telah Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                	//this.publishProgress("Success");
                	
                	Intent intent = getIntent();
    				String admin = intent.getStringExtra("admin");
    				
                	this.onPostExecute("Success");
                    Intent i = new Intent(getApplicationContext(), PerkembanganPosyandu.class);
                    i.putExtra("admin", admin);
                    startActivity(i);
                    
                    // closing this screen
                    finish();
                } 
                else 
                {
                    // failed to create product
                	//Toast.makeText(LaporanKegPKPKecActivity.this, "Data Gagal Disimpan, Silahkan Mencoba Lagi", Toast.LENGTH_SHORT).show();
                }
            } 
            catch (JSONException e) 
            {
                e.printStackTrace();
            }
            
			return null;
		}
		
		//after completing background task dismiss the progress dialog
		protected void onPostExecute (String url)
		{
			//dismiss the dialog
			pDialog.dismiss();
		}
    }
    
    //menonaktifkan tombol back di android
  	@Override
  	public void onBackPressed() 
  	{
  		String admin = "ADMIN";
  		
  		Intent back = new Intent(LaporanKegPKPKecActivity.this, Report.class);
        back.putExtra("admin", admin);
        startActivity(back);
  	}
}