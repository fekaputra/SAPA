<?php
	session_start();
	
	include("koneksi.php");
	
	//array for JSON response
	$response = array();
	
	$query = mysql_query("select * from data_map");
	
	if (mysql_num_rows($query) > 0)
	{
		$response["listmap"] = array();
		while ($record = mysql_fetch_assoc($query))
		{
			$listmap = array();
			$listmap["idKotaKab"] = $record["idKotaKab"];
			$listmap["kota_kabupaten"] = $record["kota_kabupaten"];
			$listmap["latitude"] = $record["latitude"];
			$listmap["longitude"] = $record["longitude"];
			
			//push single product into final response array
			array_push($response["listmap"], $listmap);
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