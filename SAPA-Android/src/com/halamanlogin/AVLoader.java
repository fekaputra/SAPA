package com.halamanlogin;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AVLoader extends Activity implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceHolder.Callback 
{	
	private SurfaceView mPreview;
	private SurfaceHolder holder;
	private MediaPlayer mp;
	private static final String TAG = "MediaPlayerDemo";
	private int mVideoWidth;
	private int mVideoHeight;
	private boolean mIsVideoSizeKnown = false;
	private boolean mIsVideoReadyToBePlayed = false;
	
	//JSON node name
    private static final String TAG_FilePath = "filepath";
    
    String filePath;
    
    //button to show progress dialog
    Button btnShowProgress;
    	
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_av);
		mPreview = (SurfaceView) findViewById(R.id.surface);
		holder = mPreview.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
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
            	
                //new DownloadFileFromURL().execute(url_get_file);
            	Intent in = new Intent(getApplicationContext(), DownloadFile.class);
                in.putExtra("url", "fajarjuang.com" + filePath);
                in.putExtra("admin", admin);
                startActivityForResult(in, 100);
            }
        });
	}
	
	private void play()
	{
		Intent i = getIntent();
        
        filePath = i.getStringExtra(TAG_FilePath); 
        
        //URL
        String url_get_file = "fajarjuang.com" + filePath;
        
        try
        {
        	//Create a new media player and set the listeners
        	mp = new MediaPlayer();
        	mp.setDisplay(holder);
        	mp.setOnBufferingUpdateListener(this);
        	mp.setOnCompletionListener(this);
        	mp.setOnPreparedListener(this);
        	mp.setOnVideoSizeChangedListener(this);
        	mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        	mp.setDataSource(url_get_file);
        	mp.prepare();
        	mp.start();
        }
        catch (Exception e)
        {
        	Log.e(TAG, "error : " + e.getMessage(), e);
        }
	}


	public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) 
	{
	    Log.d(TAG, "surfaceChanged called");
	}


	public void surfaceCreated(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "surfaceCreated called");
		play();
	}


	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "surfaceDestroyed called");
	}


	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) 
	{
		// TODO Auto-generated method stub
		Log.v(TAG, "onVideoSizeChanged called");
	    if (width == 0 || height == 0) 
	    {
	    	Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
	    	return;
	    }
	    mIsVideoSizeKnown = true;
	    mVideoWidth = width;
	    mVideoHeight = height;
	    if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) 
	    {
	    	startVideoPlayback();
	    }
	}


	public void onPrepared(MediaPlayer arg0) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "onPrepared called");
	    mIsVideoReadyToBePlayed = true;
	    if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) 
	    {
	    	startVideoPlayback();
	    }
	}


	public void onCompletion(MediaPlayer arg0) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "onCompletion called");
	}


	public void onBufferingUpdate(MediaPlayer mp, int percent) 
	{
		// TODO Auto-generated method stub
		Log.d(TAG, "onBufferingUpdate percent:" + percent);
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
	    releaseMediaPlayer();
	    doCleanUp();
	}

	@Override
	protected void onDestroy() 
	{
	    super.onDestroy();
	    releaseMediaPlayer();
	    doCleanUp();
	}

	private void releaseMediaPlayer() 
	{
		if (mp != null) 
	    {
			mp.release();
			mp = null;
	    }
	}

	private void doCleanUp() 
	{
	    mVideoWidth = 0;
	    mVideoHeight = 0;
	    mIsVideoReadyToBePlayed = false;
	    mIsVideoSizeKnown = false;
	}
	
	private void startVideoPlayback() 
	{
	    Log.v(TAG, "startVideoPlayback");
	    holder.setFixedSize(mVideoWidth, mVideoHeight);
	    mp.start();
	}
}
