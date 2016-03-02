<?php

require_once 'conexion.php';

function authDevice($deviceID)
{

	//$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM devices WHERE id = '$deviceID' AND deleted = '0'"; 
/*	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$rows[] = $r;
	}
	
		*/
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

        //$mysqli = makeSqlConnection();
        $sql = "SELECT * FROM devices WHERE id = '$deviceID' AND deleted = '0'";
/*      $res = $mysqli->query($sql);

        $rows = array();

        while($r = mysqli_fetch_assoc($res))
        {
                $rows[] = $r;
        }

                */
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
