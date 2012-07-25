<?php
	session_start();
	
	include("koneksi.php");
	
	//$idPosyandu = $_REQUEST['idPosyandu'];
	$kecamatan = $_REQUEST['kecamatan'];
	$desa = $_REQUEST['desa'];
	$kelurahan = $_REQUEST['kelurahan'];
	$jmlBangunan = $_REQUEST['jmlBangunan'];
	$s = $_REQUEST['s'];
	$k = $_REQUEST['k'];
	$d = $_REQUEST['d'];
	$n = $_REQUEST['n'];
	$progPengembangan = $_REQUEST['progPengembangan'];
	
	if($kecamatan != null && $desa != null && $kelurahan != null )
	{
		/* grab the posts from the db */
		$query = "INSERT INTO perkembangan_posyandu (idPosyandu, kecamatan, desa, kelurahan, jmlBangunan, s, k, d, n, progPengembangan) 
												 VALUES ('', '$kecamatan', '$desa', '$kelurahan', '$jmlBangunan', '$s', '$k', '$d', '$n', '$progPengembangan')";
		$insert = mysql_query($query);
	}
	else
	{
		echo "FAILED";
	}
?>