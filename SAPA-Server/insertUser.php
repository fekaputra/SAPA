<?php
	session_start();
	
	include("koneksi.php");
	
	/*$username = $_GET['username'];
	$password = $_GET['password'];
	$email = $_GET['email'];*/
	
	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];
	$email = $_REQUEST['email'];
	
	//if($username != null && $password != null && $email != null)
	
	//melakukan pencarian data username di database yang sama dengan username yang diinputkan user
	$query = mysql_query("SELECT * FROM t_user WHERE username = '$username'");
	
	if (mysql_num_rows($query) == 1) 
	{
		//jika username sudah terdaftar di database
		echo "Gagal, Username sudah terdaftar";
	}
	else
	{
		//jika username belum terdaftar di database
		$query = "INSERT INTO t_user (username, password, email, status) VALUES ('$username','$password','$email','')";
		$insert = mysql_query($query);
		
		if($insert)
		{
			echo "Berhasil";
		}
		else
		{
			echo "Gagal";
		}
	}
?>