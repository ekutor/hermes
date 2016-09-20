<?php

require_once 'conexion.php';

function authDevice($user,$deviceID)
{

	$mysqli = makeSqlConnection();
	$sqlInsert = "INSERT INTO movil_app_login ( user,device_id,last_signin ) VALUES( '$user','$deviceID',now()) "; 
	$mysqli->query($sqlInsert);
	
	if( empty( $deviceID ) )
	{
		$array = array("auth" => "FALSE","message" =>"Hmmm... Ud esta intentando Ingresar a una zona no autorizada!!!");
		return json_encode($array);
	}
	else
	{
	       return "Auth_OK";		
	}
}

function auth($deviceID = null,$hash = null)
{
		
	/*$sql = "SELECT * FROM movil_app_devices WHERE device_id = '$deviceID' AND permited = '0'"; 
	
	$res = $mysqli->query($sql);
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$rows[] = $r;
	}*/
	if( !empty( $deviceID)  && !empty($hash) )
        {
                return "Auth_OK";
        }
        else
        {
        	$array = array("auth" => "FALSE","message" =>"Hmmm... Ud esta intentando Ingresar a una zona no autorizada!!!");
                return json_encode($array);
	}
	
}


?>
