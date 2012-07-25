<?php
	session_start();
	
	include("koneksi.php");
	
	//array for JSON response
	$response = array();
	
	//Mengambil file tutorial yg ada di database
	$query = mysql_query("SELECT * FROM file WHERE filecategory = 'news' ");
	
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