<?php
	//include db connect class
    include("koneksi.php");
	
	$response = array();
	
	//check if one of the data is null, if null then failed, if not null then insert to database
	if(isset($_POST['kecamatan']) && isset($_POST['bulan']) && isset($_POST['tanggal']) 
	  && isset($_POST['uraianKeg']) && isset($_POST['tempat']) && isset($_POST['unsurTerlibat']) 
	  && isset($_POST['masalah']) && isset($_POST['rtl']))
	{
		$kec = $_POST['kecamatan'];
		$bulan = $_POST['bulan'];
		$tanggal = $_POST['tanggal'];
		$uraian_keg = $_POST['uraianKeg'];
		$tempat = $_POST['tempat'];
		$unsur_terlibat = $_POST['unsurTerlibat'];
		$masalah = $_POST['masalah'];
		$rtl = $_POST['rtl'];
		
		$query = "insert into keg_pkp_kec (kecamatan, bulan, tanggal, urutan_kegiatan, tempat, unsur_terlibat, masalah_yang_dihadapi, rtl) 
		          values ('$kec', '$bulan', '$tanggal', '$uraian_keg', '$tempat', '$unsur_terlibat', '$masalah', '$rtl')";
		$insert = mysql_query($query);
		
		// check if row inserted or not
		if ($insert) 
		{
			// successfully updated
			$response["success"] = 1;
			$response["message"] = "Insert Done";
 
			// echoing JSON response
			echo json_encode($response);
		} 
		else 
		{
 
		}
	}
	else
	{
		// required field is missing
		$response["success"] = 0;
		$response["message"] = "Required field(s) is missing";
 
		// echoing JSON response
		echo json_encode($response);
	}
?>