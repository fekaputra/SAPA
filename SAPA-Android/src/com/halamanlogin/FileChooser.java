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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class FileChooser extends ListActivity 
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
			pDialog = new ProgressDialog(FileChooser.this);
			pDialog.setMessage ("Loading All Files...Please Wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
    	
		//@Override
		protected String doInBackground(String... args) 
		{
			Intent intent = getIntent();
		    String category = intent.getStringExtra("category");
			
			//building parameter
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("category", category));
			
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
				//Toast.makeText(this, e.printStackTrace(), Toast.LENGTH_SHORT).show();
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
                            FileChooser.this, fileList,
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