<?php
	session_start();
	
	include("koneksi.php");
		
	$category = $_GET['category'];
	
	//array for JSON response
	$response = array();
	
	$query = mysql_query("SELECT * FROM file WHERE filetype = 'jpg' OR filetype = 'png' OR filetype = 'jpeg' OR filetype = 'bmp' OR filetype = 'gif'  ");
	
	if (mysql_num_rows($query) > 0)
	{
		$response["listfile"] = array();
		while ($record = mysql_fetch_assoc($query))
		{
			if($record['filecategory'] == $category )
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
			else if($category == "all")
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
		}
		//echoing JSON response
		echo json_encode($response);
		//echo $category;
	}
	else
	{
		$response["message"] = "No Files Found";
		echo json_encode($response);
	}	
	
	/*if (mysql_num_rows($query) > 0)
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
	}*/	
?>