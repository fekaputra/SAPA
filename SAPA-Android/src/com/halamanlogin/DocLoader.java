package com.halamanlogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DocLoader extends Activity
{
	//JSON node name
    private static final String TAG_FilePath = "filepath";
	
	//button to show progress dialog
    Button btnShowProgress;
    
    String filePath;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.file_doc);
        
        //take the filepath
        Intent i = getIntent();
        
        filePath = i.getStringExtra(TAG_FilePath);
        
        //string url
        final String url_get_file = "http://fajarjuang.com/" + filePath;
        
        Toast.makeText(DocLoader.this, url_get_file, Toast.LENGTH_SHORT).show();
        
		WebView webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setPluginsEnabled(true);
		String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
		webView.loadUrl("https://docs.google.com/viewer?url=" + pdf);
		
		btnShowProgress = (Button) findViewById(R.id.btnDownload);
        ///**
         //* Show Progress bar click event
         //*//*/
        btnShowProgress.setOnClickListener(new View.OnClickListener() {
 
            //@Override
            public void onClick(View v) {
            	Intent i = getIntent();
        		String admin = i.getStringExtra("admin");
        		
            	Intent in = new Intent(getApplicationContext(), DownloadFile.class);
                in.putExtra("url", url_get_file);
                in.putExtra("admin", admin);
                startActivityForResult(in, 100);
            }
        });
	}
}
