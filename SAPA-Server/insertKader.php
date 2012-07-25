<?php
	session_start();
	/* Referensi : http://www.nurwahyudin.com/php/membuat-sistem-login-dengan-php#axzz21aRegfyz */
	include("koneksi.php");
	
	$idPosyandu = $_REQUEST['idPosyandu'];
	$totalKader = $_REQUEST['totalKader'];
	$kaderTerlatih = $_REQUEST['kaderTerlatih'];
	
	//melakukan pencarian data idPosyandu di database yang sama dengan idPosyandu yang diinputkan user
	$query = mysql_query("SELECT * FROM kader WHERE idPosyandu = '$idPosyandu'");
	
	if (mysql_num_rows($query) == 1) 
	{
		//jika idPosyandu sudah terdaftar di database
		$update = "UPDATE kader SET totalKader = '$totalKader', kaderTerlatih = '$kaderTerlatih' WHERE idPosyandu = $idPosyandu ";
		$execute = mysql_query($update);
	} 
	else 
	{
		//jika idPosyandu tidak terdaftar di database
		$insert = "INSERT INTO kader (idPosyandu, totalKader, kaderTerlatih) VALUES ('$idPosyandu', '$totalKader', '$kaderTerlatih')";
		$execute = mysql_query($insert);
	}
	
	if($execute)
	{
		echo "Berhasil";
	}
	else
	{
		echo "Gagal";
	}
?>