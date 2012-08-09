<?php
	session_start();
	
	include("koneksi.php");
	
	$id = $_GET['id'];
	
	//array for JSON response
	$response = array();
	
	$query = mysql_query("SELECT * FROM data_kesehatan");
	
	if (mysql_num_rows($query) > 0)
	{
		$response["listkesehatan"] = array();
		while ($record = mysql_fetch_assoc($query))
		{
			if($record['idKotaKab'] == $id )
			{
				$listkesehatan = array();
				$listkesehatan["idKotaKab"] = $record["idKotaKab"];
				$listkesehatan["jml_dbd"] = $record["jml_dbd"];
				$listkesehatan["jml_malaria"] = $record["jml_malaria"];
				$listkesehatan["jml_giziBuruk"] = $record["jml_giziBuruk"];
				
				//push single product into final response array
				array_push($response["listkesehatan"], $listkesehatan);
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