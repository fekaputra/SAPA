<?php
	$server="localhost";
    $username="k8758334_umar";
    $password="binkhattab";
	$database="k8758334_umar";
			
	$Connect=mysql_connect($server, $username, $password) or die(mysql_error());
	mysql_select_db($database, $Connect) or die(mysql_error());
?>