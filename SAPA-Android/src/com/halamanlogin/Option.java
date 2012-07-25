/* Nama Package : com.halamanlogin
   Nama Kelas   : Option.java
   Deskripsi    : Class yang berfungsi untuk mengambil suatu data itu berada di directory mana, 
                  nama datanya apa dan size datanya berapa
   Author       : Laras Ervintyana D.K.S
   Referensi    : www.stackoverflow.com
*/
package com.halamanlogin;

//this class is for hold our data we're going to get from listing all the file and directories
public class Option implements Comparable<Option>{
	private String name;
	private String data;
	private String path;
	
	public Option(String n,String d,String p)
	{
		name = n;
		data = d;
		path = p;
	}
	public String getName()
	{
		return name;
	}
	public String getData()
	{
		return data;
	}
	public String getPath()
	{
		return path;
	}
	//@Override
	public int compareTo(Option o) {
		if(this.name != null)
			return this.name.toLowerCase().compareTo(o.getName().toLowerCase()); 
		else 
			throw new IllegalArgumentException();
	}
}

