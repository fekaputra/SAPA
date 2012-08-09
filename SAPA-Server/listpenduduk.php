<?php
	session_start();
	
	include("koneksi.php");
	
	$id = $_GET['id'];
	
	//array for JSON response
	$response = array();
	
	$query = mysql_query("SELECT * FROM data_kependudukan");
	
	if (mysql_num_rows($query) > 0)
	{
		$response["listpenduduk"] = array();
		while ($record = mysql_fetch_assoc($query))
		{
			if($record['idKotaKab'] == $id )
			{
				$listpenduduk = array();
				$listpenduduk["idKotaKab"] = $record["idKotaKab"];
				$listpenduduk["jml_penduduk"] = $record["jml_penduduk"];
				$listpenduduk["kepadatan_penduduk"] = $record["kepadatan_penduduk"];
				
				//push single product into final response array
				array_push($response["listpenduduk"], $listpenduduk);
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