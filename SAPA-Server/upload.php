<?php
	$server="localhost";
    $username="k8758334_umar";
    $password="binkhattab";
	$database="k8758334_umar";
			
	$Connect=mysql_connect($server, $username, $password) or die(mysql_error());
	mysql_select_db($database, $Connect) or die(mysql_error());
	
	$filename = $_FILES['file']['name'];
	$filesize = $_FILES['file']['size'];
	$filecategory = $_POST['filecategory'];
	$filetype = $_POST['filetype'];
	$fileError = $_FILES['file']['error'];//get the error while upload
	if ($filesize > 0 || $fileError == 0)
	{
		$move = move_uploaded_file($_FILES['file']['tmp_name'], 'C:/xampp/htdocs/android/'.$filename);//save to the folder
		if ($move)
		{
			$query = mysql_query("insert into file (filename, filetype, filesize, filecategory, filepath) values ('$filename', '$filetype', '$filesize', '$filecategory', 'android/$filename')", $Connect);	
		}
		else
		{
			echo "Failed";	
		}
	}
	else
	{
		echo "Failed to Upload : ".$fileError;	
	}
?>