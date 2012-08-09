<?php
	session_start();
	
	include("koneksi.php");
	
	$id = $_GET['id'];
	
	//array for JSON response
	$response = array();
	
	$query = mysql_query("SELECT * FROM data_kolega");
	
	if (mysql_num_rows($query) > 0)
	{
		$response["listkolega"] = array();
		while ($record = mysql_fetch_assoc($query))
		{
			if($record['idKotaKab'] == $id )
			{
				$listkolega = array();
				$listkolega["idKotaKab"] = $record["idKotaKab"];
				$listkolega["NamaKolega"] = $record["NamaKolega"];
				$listkolega["Alamat"] = $record["Alamat"];
				$listkolega["Telp"] = $record["Telp"];
				$listkolega["latitude"] = $record["latitude"];
				$listkolega["longitude"] = $record["longitude"];
				
				//push single product into final response array
				array_push($response["listkolega"], $listkolega);
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
?>