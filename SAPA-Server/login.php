<?php
    session_start();
	
	include("koneksi.php");
	
	/*Menampung data username dan password yg diinput user dari aplikasi*/    
    $username = $_REQUEST['username'];
    $password = $_REQUEST['password']; 
    
	/*Mengambil data User di database yang sesuai dengan username dan password yg diinput*/
    $query = "SELECT * FROM t_user WHERE username ='$username' and password = '$password'";
    $execute = mysql_query($query);
	
	$record = mysql_fetch_array($execute);
	
    $jumlah = mysql_num_rows($execute);
    
	/*Mengirimkan data untuk di eksekusi oleh JAVA*/
    if ($jumlah >= 1 )
	{
		//cek status user, 1 untuk admin, 0 untuk member
		if($record['status'] == 1)
		{	
			echo "TRUE.ADMIN"; 
		} //masuk ke session milik admin
		else if ($record['status'] == 0)
		{	
			echo "TRUE.MEMBER";	 
		}//masuk ke session milik member
    }
	else
	{
        echo "FALSE";
    }
    
?>
