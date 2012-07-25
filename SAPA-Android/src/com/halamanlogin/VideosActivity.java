/*package com.halamanlogin;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ListView;
import android.widget.Toast;

public class VideosActivity extends ListActivity {
	private File currentDir;
    private FileArrayAdapter adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        currentDir = new File("/sdcard/");//initialize currentDir to the root of the SD card
        fill(currentDir);
    }
    
    //to get all the files and folder for current directory were in
    //1. we're going to get an array of all the files and dirs in the current we're in
    //2. we're going to create 2 ListArrays. One for folders and one for files
    //3. we're going to sort files and dirs into the appropriate ListArray
    private void fill(File f)
    {
    	 File[]dirs = f.listFiles();
		 this.setTitle("Current Dir: "+f.getName());
		 List<Option>dir = new ArrayList<Option>();
		 List<Option>fls = new ArrayList<Option>();
		 try
		 {
			 for(File ff: dirs)
			 {
				String filename = ff.getName();
				String filenameArray[] = filename.split("\\.");
				String extension = filenameArray[filenameArray.length-1]; 
					
				//fls.add(new Option(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath()));
				if (extension.contains("mpeg") || extension.contains("3gp"))
				{
					fls.add(new Option(ff.getName(),"File Size: "+ff.length(),ff.getAbsolutePath()));
				}
			 }
		 }
		 catch(Exception e)
		 {
			 
		 }
		 Collections.sort(dir);
		 Collections.sort(fls);
		 dir.addAll(fls);
		 if(!f.getName().equalsIgnoreCase("sdcard"))
			 dir.add(0,new Option("..","Parent Directory",f.getParent()));
		 adapter = new FileArrayAdapter(VideosActivity.this,R.layout.photos_layout,dir);
		 this.setListAdapter(adapter);
    }
    
    @Override
    //to handle user clicking on files and folders
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Option o = adapter.getItem(position);
		if(o.getData().equalsIgnoreCase("folder")||o.getData().equalsIgnoreCase("parent directory"))
		{
			currentDir = new File(o.getPath());
			fill(currentDir);
		}
		else
		{
			onFileClick(o);
		}
	}
    
    private void onFileClick(Option o)
    {
		//differentiate file type
    	String filename = o.getName();
		String filenameArray[] = filename.split("\\.");
		String extension = filenameArray[filenameArray.length-1];
		
		Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_VIEW);
    	
    	if (extension.contains("mpeg") || extension.contains("3gp"))
		{
			intent.setDataAndType(Uri.parse("file://" + o.getPath()), "video/*");
		}
		
		try
    	{
    		startActivity(intent);
    	}
    	catch (ActivityNotFoundException e)
    	{
    		Toast.makeText(this, "No Application available to View this file", Toast.LENGTH_SHORT).show();
    	}
    }
    
    // Initiating Menu XML file (menu.xml) 
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
    
    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * 
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case R.id.menu_bookmark:
        	// Single menu item is selected do something
        	// Ex: launching new activity/screen or show alert message
            Toast.makeText(VideosActivity.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_save:
        	Toast.makeText(VideosActivity.this, "Save is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_search:
        	Toast.makeText(VideosActivity.this, "Search is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_share:
        	Toast.makeText(VideosActivity.this, "Share is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_delete:
        	Toast.makeText(VideosActivity.this, "Delete is Selected", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.menu_preferences:
        	Toast.makeText(VideosActivity.this, "Preferences is Selected", Toast.LENGTH_SHORT).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}*/

package com.halamanlogin;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.halamanlogin.VideosActivity.LoadAllFiles;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.sms.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class VideosActivity extends ListActivity 
{
	private ProgressDialog pDialog;
	
	//button to show progress dialog
    Button btnShowProgress;
    
    //progress dialog type (0 - for horizontal progress bar)
    public static final int progress_bar_type = 0;
    
    //creating JSON Parser Object
    JSONParser jParser = new JSONParser();
    
    ArrayList<HashMap<String, String>> fileList;
    
    private static String url_all_files = "http://fajarjuang.com/android/listvideo.php";
    //private static String url = "http://167.205.34.196/";//use lan itb
    //private static String url = "http://192.168.107.1/";//in home
    
    //JSON Node Names
    private static final String TAG_ListFile = "listfile";
    private static final String TAG_FileName = "filename";
    private static final String TAG_FileSize = "filesize";
    private static final String TAG_FileId = "fileid";
    private static final String TAG_FilePath = "filepath";
    
    JSONArray file = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.all_files);
        
        fileList = new ArrayList<HashMap<String, String>>();
        
        //loading all files in the background
        new LoadAllFiles().execute();
        
        ListView lv = getListView();
        
        // on seleting single product
        // launching Edit Product Screen
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            //@Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                //getting values from selected ListItem
                //String fileId = ((TextView)view.findViewById(R.id.fileid)).getText().toString();
                //String fileName = ((TextView)view.findViewById(R.id.TextView01)).getText().toString();
                String filePath = ((TextView) view.findViewById(R.id.filepath)).getText().toString();
                
                Intent in = null;
                
                //replace all white space
                filePath = filePath.replaceAll(" ", "%20");
                
                String type = mimeName(filePath);
                
                if (type.contains("image"))
                {
                	in = new Intent(getApplicationContext(), LoadImage.class);
                }
                else if (type.contains("audio") || type.contains("video"))
                {
                	in = new Intent(getApplicationContext(), AVLoader.class);
                }
                else if (type.contains("application"))
                {
                	in = new Intent(getApplicationContext(), DocLoader.class);
                }

                in.putExtra(TAG_FilePath, filePath);
                startActivityForResult(in, 100);
            }
        });
    }
    
    private String mimeName(String name)
    {
    	String fileName = name;
    	MimeTypeMap mime = MimeTypeMap.getSingleton();
		String filenameArray[] = fileName.split("\\.");
		fileName = filenameArray[filenameArray.length-1];
        String type = mime.getMimeTypeFromExtension(fileName.toLowerCase());
    	return type;
    }
    
    class LoadAllFiles extends AsyncTask<String, String, String>
    {    	
    	@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(VideosActivity.this);
			pDialog.setMessage ("Loading All Files...Please Wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
    	
		//@Override
		protected String doInBackground(String... args) 
		{
			//building parameter
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			//getting JSON string from url
			JSONObject json = jParser.makeHttpRequest(url_all_files, "GET", params);
			
			//check log cat
			//Log.d("All Files : ", json.toString());
			
			try
			{
				//getting array of product
				file = json.getJSONArray(TAG_ListFile);
				for (int i = 0; i < file.length(); i++)
				{
					JSONObject o = file.getJSONObject(i);
					
					//storing each json item in variable
					String fileId = o.getString(TAG_FileId).toString();
					String fileName = o.getString(TAG_FileName).toString();
					String fileSize = o.getString(TAG_FileSize).toString();
					String filePath = o.getString(TAG_FilePath).toString();
					
					//creating new hashmap
					HashMap<String, String> map = new HashMap<String, String>();
					
					//adding each child node to hash map key => value
					map.put(TAG_FileId, fileId);
					map.put(TAG_FileName, fileName);
					map.put(TAG_FileSize, fileSize);
					map.put(TAG_FilePath, filePath);
					
					//adding hashlist to array list
					fileList.add(map);
				}
			}
			catch (JSONException e)
			{
				e.printStackTrace();

			}
			
			return null;
		}
		
		protected void onPostExecute(String file_url) 
		{
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() 
            {
                public void run() 
                {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            VideosActivity.this, fileList,
                            R.layout.file_view, new String[] { TAG_FileId, TAG_FilePath, TAG_FileName,
                                    TAG_FileSize},
                            new int[] { R.id.fileid, R.id.filepath ,R.id.TextView01, R.id.TextView02 });
                    
                    // updating listview
                    setListAdapter(adapter);
                }
            });
        }
    }
}