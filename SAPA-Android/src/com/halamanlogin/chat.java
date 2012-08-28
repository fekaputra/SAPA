package com.halamanlogin;

import java.util.ArrayList;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Chat extends Activity
{
	private ArrayList<String> messages = new ArrayList<String>();
    private Handler mHandler = new Handler();
    private SettingsDialog mDialog;
    private EditText mRecipient;
    private EditText mSendText;
    private ListView mList;
    private XMPPConnection connection;
    
    //change
    private com.halamanlogin.DiscussArrayAdapter adapter;
    private ListView lv;
    
    //MultiUserChat muc = new MultiUserChat();
	Button back;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        Log.i("XMPPClient", "onCreate called");
        
        //first
        //setContentView(R.layout.e_communication);
        
        //change
        setContentView(R.layout.activity_discuss);
        lv = (ListView) findViewById(R.id.listView1);
        adapter = new DiscussArrayAdapter(getApplicationContext(), R.layout.listitem_discuss);
        
        lv.setAdapter(adapter);

        mRecipient = (EditText) this.findViewById(R.id.recipient);
        Log.i("XMPPClient", "mRecipient = " + mRecipient);
        mSendText = (EditText) this.findViewById(R.id.editText1);
        Log.i("XMPPClient", "mSendText = " + mSendText);
        //mList = (ListView) this.findViewById(R.id.listMessages);
        //Log.i("XMPPClient", "mList = " + mList);
        //setListAdapter();

        // Dialog for getting the xmpp settings
        mDialog = new SettingsDialog(this);

        // Set a listener to show the settings dialog
        Button setup = (Button) this.findViewById(R.id.setup);
        setup.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View view) 
            {
                mHandler.post(new Runnable() 
                {
                    public void run() 
                    {
                        mDialog.show();
                    }
                });
            }
        });

        // Set a listener to send a chat text message
        Button send = (Button) this.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View view) 
            {
                String to = mRecipient.getText().toString();
                //String to = "jtk.101511016.larasedks@gmail.com";
                String text = mSendText.getText().toString();

                Log.i("XMPPClient", "Sending text [" + text + "] to [" + to + "]");
                Message msg = new Message(to, Message.Type.chat);
                
                msg.setBody(text);
                
                connection.sendPacket(msg);
                
                messages.add(connection.getUser() + ":");
                
                messages.add(text);
                
                //change
                adapter.add(new OneMessage(false, text));
                
                //setListAdapter();
            }
        });
		
		back = (Button) findViewById(R.id.kembali);
		back.setOnClickListener(new Button.OnClickListener()
		{
			public void onClick(View v)
	        {
				Intent i = getIntent();
				String admin = i.getStringExtra("admin");
				
				if(admin.equals("ADMIN"))
				{
					Intent home = new Intent (Chat.this, HomeAdmin.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
				else
				{
					Intent home = new Intent (Chat.this, HomeMember.class);
					home.putExtra("admin", admin);
			    	startActivity(home);
				}
	        }
		});
	}
	
	/**
     * Called by Settings dialog when a connection is establised with the XMPP server
     *
     * @param connection
     */
    public void setConnection(XMPPConnection connection) {
        this.connection = connection;
        if (connection != null) 
        {
            // Add a packet listener to get messages sent to us
            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            connection.addPacketListener(new PacketListener() 
            {
                public void processPacket(Packet packet) 
                {
                    Message message = (Message) packet;
                    if (message.getBody() != null) 
                    {
                        String fromName = StringUtils.parseBareAddress(message.getFrom());
                        Log.i("XMPPClient", "Got text [" + message.getBody() + "] from [" + fromName + "]");
                        
                        //first
                        messages.add(fromName + ":");
                        messages.add(message.getBody());
                        
                        //change
                        //adapter.add(new OneMessage(false, message.getBody()));
                        adapter.add(new OneMessage(true, message.getBody()));
                        
                        // Add the incoming message to the list view
                        /*mHandler.post(new Runnable() 
                        {
                            public void run() 
                            {
                                setListAdapter();
                            }
                        });*/
                    }
                }
            }, filter);
        }
    }

    private void setListAdapter() 
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.multi_line_list_item, messages);
        mList.setAdapter(adapter);
    }
}
