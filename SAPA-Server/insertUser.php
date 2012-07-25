<?php
	session_start();
	
	include_once("koneksi.php");
	
	/*$username = $_GET['username'];
	$password = $_GET['password'];
	$email = $_GET['email'];*/
	
	$username = $_REQUEST['username'];
	$password = $_REQUEST['password'];
	$email = $_REQUEST['email'];
	
	if($username != null && $password != null && $email != null)
	{
		/* grab the posts from the db */
		$query = "INSERT INTO t_user (username, password, email, status) VALUES ('$username','$password','$email','')";
		$insert = mysql_query($query);
	}
	else
	{
		echo "FAILED";
	}
?>