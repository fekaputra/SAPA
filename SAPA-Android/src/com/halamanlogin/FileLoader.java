package com.halamanlogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

public class FileLoader extends Activity
{
	//JSON node name
    private static final String TAG_FilePath = "filepath";
	
	//button to show progress dialog
    Button btnShowProgress;
    
    String filePath;
    
    //make an instance of static class
    
	FileCache fileCache = new FileCache();
	ExecutorService executorService;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file);
		
		clearCache();
		
		//take the filepath
        Intent i = getIntent();
		
		filePath = i.getStringExtra(TAG_FilePath);
        
        //string url
        final String url_get_file = Referensi.ip + filePath;
        
        Intent intent= new Intent();
		intent.setAction(Intent.ACTION_VIEW);
        
        File file = getFileFromUrl(url_get_file);
        
        String ext = GetExtention(filePath);
        
        intent.setDataAndType(Uri.fromFile(file), ext);
        
        try
    	{
    		startActivity(intent);
    	}
    	catch (ActivityNotFoundException e)
    	{
    		Toast.makeText(this, "No Application available to View this file", Toast.LENGTH_SHORT).show();
    	}
		
		btnShowProgress = (Button) findViewById(R.id.btnDownload);
        ///**
         //* Show Progress bar click event
         //*//*/
        btnShowProgress.setOnClickListener(new View.OnClickListener() {
 
            //@Override
            public void onClick(View v) {
                // starting new Async Task
            	Intent in = new Intent(getApplicationContext(), DownloadFile.class);
                in.putExtra("url", url_get_file);
                startActivityForResult(in, 100);
            }
        });
		
		//Toast.makeText(DocLoader.this, ext, Toast.LENGTH_SHORT).show();
	}
	
	public void clearCache() 
    {
        fileCache.clear();
    }
	
	private File getFileFromUrl(String url)
	{
		File f = fileCache.getFile(url);
		try
		{
			URL docUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)docUrl.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
		    conn.setInstanceFollowRedirects(true);
			InputStream is=conn.getInputStream();
			OutputStream os = new FileOutputStream(f);
			Utils.CopyStream(is, os);
			os.close();
			return f;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public String GetExtention(String name)
    {
    	name = name.replaceAll("%20", " ");
		MimeTypeMap mime = MimeTypeMap.getSingleton();
    	String type = name;
		String filenameArray[] = type.split("\\.");
		type = filenameArray[filenameArray.length-1];
        String ext = mime.getMimeTypeFromExtension(type.toLowerCase());
    	return ext;
    }
}
