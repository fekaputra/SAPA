<?php
	session_start();
	
	include("koneksi.php");
	
	$idPosyandu = $_REQUEST['idPosyandu'];
	$pratama = $_REQUEST['pratama'];
	$madya = $_REQUEST['madya'];
	$purnama = $_REQUEST['purnama'];
	$mandiri = $_REQUEST['mandiri'];
	$jumlahStrata = $_REQUEST['jumlahStrata'];
	
	//melakukan pencarian data idPosyandu di database yang sama dengan idPosyandu yang diinputkan user
	$query = mysql_query("SELECT * FROM strata_posyandu WHERE idPosyandu = '$idPosyandu'");
	
	if (mysql_num_rows($query) == 1) 
	{
		//jika idPosyandu sudah terdaftar di database
		$update = "UPDATE strata_posyandu SET pratama = '$pratama', madya = '$madya', purnama = '$purnama', mandiri = '$mandiri', jumlahStrata = '$jumlahStrata' WHERE idPosyandu = $idPosyandu ";
		$execute = mysql_query($update);
	}
	else 
	{
		//jika idPosyandu tidak terdaftar di database
		$insert = "INSERT INTO strata_posyandu (idPosyandu, pratama, madya, purnama, mandiri, jumlahStrata) 
		           VALUES ('$idPosyandu','$pratama','$madya','$purnama', '$mandiri', '$jumlahStrata')";
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