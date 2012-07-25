<?php
	session_start();
	
	include("koneksi.php");
	
	$idPosyandu = $_REQUEST['idPosyandu'];
	$kb = $_REQUEST['kb'];
	$kia = $_REQUEST['kia'];
	$gizi = $_REQUEST['gizi'];
	$imunisasi = $_REQUEST['imunisasi'];
	$p2d = $_REQUEST['p2d'];
	
	//melakukan pencarian data idPosyandu di database yang sama dengan idPosyandu yang diinputkan user
	$query = mysql_query("SELECT * FROM program_pokok WHERE idPosyandu = '$idPosyandu'");
	
	if (mysql_num_rows($query) == 1) 
	{
		//jika idPosyandu sudah terdaftar di database
		$update = "UPDATE program_pokok SET kb = '$kb', kia = '$kia', gizi = '$gizi', imunisasi = '$imunisasi', p2d = '$p2d' WHERE idPosyandu = $idPosyandu ";
		$execute = mysql_query($update);
	}
	else 
	{
		//jika idPosyandu tidak terdaftar di database
		$insert = "INSERT INTO program_pokok (idPosyandu, kb, kia, gizi, imunisasi, p2d) 
		           VALUES ('$idPosyandu', '$kb', '$kia', '$gizi', '$imunisasi', '$p2d')";
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