package com.halamanlogin;

import android.widget.EditText;

public class ValidasiInsert 
{
	public void message (EditText record)
	{
		EditText valid = record;
		if (valid.getText().toString().length() == 0)
		{
			valid.setError("This Record is Required");
		}
	}
    
    public void messages (EditText[] fields)
    {
    	for(int i = 0; i < fields.length; i++)
    	{
    		EditText currentFields = fields[i];
    		if(currentFields.getText().toString().length() <= 0)
    		{
    			message(currentFields);
    		}
    	}
    }
    
    public boolean validation (EditText[] fields)
    {
    	boolean loop = false;
    	int i = 0;
    	boolean check = false;
    	while (i < fields.length && loop == false)
    	{
    		EditText currentFields = fields[i];
    		if(currentFields.getText().toString().length() <= 0)
    		{
    			loop = true;
    			check = false;
    		}
    		else
    		{
    			check = true;
    		}
    		i = i + 1;
    	}
		return check;
    }
}
