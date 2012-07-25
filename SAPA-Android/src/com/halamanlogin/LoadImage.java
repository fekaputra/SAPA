package com.halamanlogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LoadImage extends Activity
{
	//JSON node name
    private static final String TAG_FilePath = "filepath";
    
    String filePath;
	
	//button to show progress dialog
    Button btnShowProgress;
    
    //progress dialog type (0 - for horizontal progress bar)
    public static final int progress_bar_type = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_image);
 
        // Loader image - will be shown before loading image
        int loader = R.drawable.ic_launcher;
 
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.image);
 
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
        
        Intent i = getIntent();
        
        filePath = i.getStringExtra(TAG_FilePath);
        
        // Image url
        final String url_get_file = "http://fajarjuang.com/" + filePath;
        
        //call the ImageLoader class
        imgLoader.DisplayImage(url_get_file, loader, image);
        
        btnShowProgress = (Button) findViewById(R.id.btnDownload);
        /**
         * Show Progress bar click event
         * */
        btnShowProgress.setOnClickListener(new View.OnClickListener() {
 
            //@Override
            public void onClick(View v) {
                // starting new Async Task
            	Intent i = getIntent();
        		String admin = i.getStringExtra("admin");
            	
            	Intent in = new Intent(getApplicationContext(), DownloadFile.class);//for audio
                in.putExtra("url", url_get_file);
                in.putExtra("admin", admin);
                startActivityForResult(in, 100);
            }
        });
    }
}
