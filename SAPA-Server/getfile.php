<?php
	$server="localhost";
    $username="k8758334_umar";
    $password="binkhattab";
	$database="k8758334_umar";
			
	$Connect=mysql_connect($server, $username, $password) or die(mysql_error());
	mysql_select_db($database, $Connect) or die(mysql_error());
	
	$response = array();
	
	$fileid = $_POST["fileid"];
	
	$query = mysql_query("select *from file where fileid = $fileid");
	
	if(mysql_num_rows($query) > 0)
	{
		$record = mysql_fetch_array($query);
		
		$getfile = array();
		$getfile["fileid"] = $record["fileid"];
		$getfile["filepath"] = $record["filepath"];
		$getfile["filename"] = $record["filename"];
		$getfile["filetype"] = $record["filetype"];
		$getfile["filesize"] = $record["filesize"];
		$getfile["filecategory"] = $record["filecategory"];
		
		$response["getfile"] = array();
		
		array_push($response["getfile"], $getfile);
		
		echo json_encode($response);
	}
	else
	{
		$response["message"] = "No Files Found";
		echo json_encode($response);
	}
?>