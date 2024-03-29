package com.halamanlogin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class KnowledgeAll extends ListActivity 
{
	private ProgressDialog pDialog;
	
	//button to show progress dialog
    Button btnShowProgress;
    
    //progress dialog type (0 - for horizontal progress bar)
    public static final int progress_bar_type = 0;
    
    //creating JSON Parser Object
    JSONParser jParser = new JSONParser();
    
    ArrayList<HashMap<String, String>> fileList;
    
    private static String url_all_files = Referensi.url + "/listfile.php";
    
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
        lv.setOnItemClickListener(new OnItemClickListener() {
 
            //@Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                String filePath = ((TextView)view.findViewById(R.id.filepath)).getText().toString();
                
                Intent in = null;
                
                //replace all white space
                filePath = filePath.replaceAll(" ", "%20");
                //Toast.makeText(KnowledgeAll.this, filePath, Toast.LENGTH_SHORT).show();
                
                in = new Intent(getApplicationContext(), FileLoader.class);
                
                in.putExtra(TAG_FilePath, filePath);
                startActivityForResult(in, 100);
            }
        });
    }
    
    class LoadAllFiles extends AsyncTask<String, String, String>
    {    	
    	@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(KnowledgeAll.this);
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
			
			params.add(new BasicNameValuePair("category", "all"));
			
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
                    		KnowledgeAll.this, fileList,
                            R.layout.file_view, new String[] { TAG_FileId, TAG_FilePath, TAG_FileName,
                                    TAG_FileSize},
                            new int[] { R.id.fileid, R.id.filepath ,R.id.TextView01, R.id.TextView02 });
                    
                    // updating listview
                    setListAdapter(adapter);
                }
            });
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
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	Intent i = getIntent();
		String admin = i.getStringExtra("admin");
		
    	switch (item.getItemId())
        {
        case R.id.all:
        	// Single menu item is selected do something
        	// Ex: launching new activity/screen or show alert message
            //Toast.makeText(KnowledgeAll.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();

        	Intent all = new Intent(KnowledgeAll.this, FileChooser.class);
			all.putExtra("admin", admin);
			all.putExtra("category", "all");
	    	startActivity(all);
            return true;
            
        case R.id.image:
        	
        	Intent image = new Intent(KnowledgeAll.this, PhotosActivity.class);
			image.putExtra("admin", admin);
			image.putExtra("category", "all");
	    	startActivity(image);
            return true;
            
        case R.id.audio:
        	Intent audio = new Intent(KnowledgeAll.this, SongsActivity.class);
			audio.putExtra("admin", admin);
			audio.putExtra("category", "all");
	    	startActivity(audio);
            return true;
            
        case R.id.video:
        	Intent video = new Intent(KnowledgeAll.this, VideosActivity.class);
			video.putExtra("admin", admin);
			video.putExtra("category", "all");
	    	startActivity(video);
            return true;
            
        case R.id.document:
        	Intent doc = new Intent(KnowledgeAll.this, DocumentsActivity.class);
			doc.putExtra("admin", admin);
			doc.putExtra("category", "all");
	    	startActivity(doc);
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}