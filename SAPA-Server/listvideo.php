<?php
	session_start();
	
	$server="localhost";
    $username="k8758334_umar";
    $password="binkhattab";
	$database="k8758334_umar";

	//membuat koneksi ke database
	mysql_connect("$server", "$username", "$password") or die(mysql_error());
	mysql_select_db($database) or die(mysql_error());
	
	//array for JSON response
	$response = array();
	
	//Mengambil data video yg ada di database
	$query = mysql_query("SELECT * FROM file WHERE filetype = 'mpeg' OR filetype = '3gp'");
	
	if (mysql_num_rows($query) > 0)
	{
		$response["listfile"] = array();
		while ($record = mysql_fetch_assoc($query))
		{
			$listfile = array();
			$listfile["fileid"] = $record["fileid"];
			$listfile["filepath"] = $record["filepath"];
			$listfile["filename"] = $record["filename"];
			$listfile["filetype"] = $record["filetype"];
			$listfile["filesize"] = $record["filesize"];
			$listfile["filecategory"] = $record["filecategory"];
			
			//push single product into final response array
			array_push($response["listfile"], $listfile);
		}
			
		//echoing JSON response
		echo json_encode($response);
	}
	else
	{
		$response["message"] = "No Files Found";
		echo json_encode($response);
	}	
?>