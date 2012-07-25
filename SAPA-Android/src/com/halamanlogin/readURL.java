package com.halamanlogin;

import java.io.*;
import java.net.URL;
public class readURL 
{
  private String url;
  
  public readURL(String url) 
  {
     this.url = url;
  }
  
  public String getHTML()
  {
	  String isi = "";
	  try {
	      URL urlExc = new URL(url);
	      BufferedReader in = new BufferedReader(new InputStreamReader(urlExc.openStream()));
	      String inputLine;
	      while ((inputLine = in.readLine()) != null)
	      {
	        isi+=inputLine;
	      }
	      in.close();
	  } catch(Exception ex) {
	      System.out.println("Error=" + ex.getMessage());
	  }
	  return isi;
  }
}

