package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class NewDataAnak extends Activity 
{	
	private EditText newPosyandu, newNamaAnak, newBbLahir, newNamaIbu, newNamaAyah;
	private Button btnSubmit, back;
	private String jns_kel;
	private RadioGroup jenis_kelamin;
	DatePicker newTglLahir;
	
	ValidasiInsert validasi = new ValidasiInsert();

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_data_anak);
		
		newPosyandu = (EditText) findViewById(R.id.nama_posyandu);
		validasi.message(newPosyandu);
		newNamaAnak = (EditText) findViewById(R.id.new_namaAnak);
		validasi.message(newNamaAnak);
		newBbLahir = (EditText) findViewById(R.id.new_bbLahir);
		validasi.message(newBbLahir);
		newNamaIbu = (EditText) findViewById(R.id.new_namaIbu);
		validasi.message(newNamaIbu);
		newNamaAyah = (EditText) findViewById(R.id.new_namaAyah);
		validasi.message(newNamaAyah);
		
		newTglLahir = (DatePicker) findViewById(R.id.dateTanggal);
		
		final EditText[] editText = new EditText[] {newPosyandu, newNamaAnak, newBbLahir, newNamaIbu, newNamaAyah};
		
		jenis_kelamin = (RadioGroup) findViewById(R.id.radioGroupSex);
		

		btnSubmit = (Button) findViewById(R.id.daftar);
		btnSubmit.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v) 
			{
				int jk = jenis_kelamin.getCheckedRadioButtonId();
				if(jk == R.id.radioMale)
				{
					jns_kel = "Laki-laki";
				}
				else if(jk == R.id.radioFemale)
				{
					jns_kel = "Perempuan";
				};
				
				//mengambil tanggal dari datepicker
				int day = newTglLahir.getDayOfMonth();
				int month = newTglLahir.getMonth() + 1;
				int year =newTglLahir.getYear();
				String tanggal = day + "-" + month + "-" + year;
				
				//call class validation insert to insert new data
				boolean check = validasi.validation(editText);//validation(editText);
				if(check == false)
				{
					Toast.makeText(NewDataAnak.this, "There are some field(s) need to input", Toast.LENGTH_SHORT).show();
					validasi.messages(editText);
				}
				else
				{
					String toastMessage = 
							"Nama Posyandu : " + newPosyandu.getText().toString() + "\n" +
							"Nama Anak     : " + newNamaAnak.getText().toString() + "\n" +
							"Jenis Kelamin : " + jns_kel                          + "\n" +		
							"Tanggal Lahir : " + tanggal						  + "\n" +
							"Berat Badan   : " + newBbLahir.getText().toString() + "\n" +
							"Nama Ibu      : " + newNamaIbu.getText().toString() + "\n" +
							"Nama Ayah     : " + newNamaAyah.getText().toString();
				
					Toast t = Toast.makeText(NewDataAnak.this, toastMessage, Toast.LENGTH_LONG);
					t.show();
				}
			}
		});
		
		back = (Button) findViewById(R.id.kembali);
        back.setOnClickListener(new Button.OnClickListener()
		{			
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				Intent back = new Intent(NewDataAnak.this, Childcare.class);
				back.putExtra("admin", admin);
		    	startActivity(back);
	        }
		});
	}
	
	//menonaktifkan tombol back di android
	@Override
	public void onBackPressed() 
	{
		//tidak melakukan apa-apa
	}
}