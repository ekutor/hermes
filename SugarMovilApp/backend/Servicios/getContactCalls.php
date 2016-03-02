<?php

require 'conexion.php';

function getContactCalls($idContact)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT calls.id,calls.name FROM calls 
	LEFT JOIN calls_contacts ON calls.id = calls_contacts.call_id 
	WHERE calls.deleted = '0' 
	AND calls_contacts.contact_id = '$idContact' 
	ORDER BY name ASC";
	$res = $mysqli->query($sql);
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
		
		$a =  (array) $obj;
		
		$rows[] = $a;
	}
		
	if( empty( $rows ) )
	{
		return '{"results" :[]}';
	}
	else
	{
		//Convierte el arreglo en json y lo retorna
		$temp = json_encode(utf8ize($rows));
		return '{"results" :'.$temp.'}';
	}
}



function utf8ize($d)
{
	if (is_array($d))
	{
		foreach ($d as $k => $v)
		{
			$d[$k] = utf8ize($v);
		}
	}
	else if (is_string ($d))
	{
		return utf8_encode($d);
	}
	return $d;
}

?>
