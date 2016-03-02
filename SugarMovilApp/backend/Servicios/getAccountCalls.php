<?php

require 'conexion.php';

function getAccountCalls($idAccount)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM calls WHERE parent_id = '$idAccount' AND deleted = '0'";
			

	
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
		
		$obj->created_by_name = getNombreUsuario($obj->created_by);
		
		$obj->assigned_user_name = getNombreUsuario($obj->assigned_user_id);
		
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




function getNombreUsuario($idUsuario)
{
	$mysqli = makeSqlConnection();
	$sql2 = "SELECT user_name FROM users WHERE id = '$idUsuario'";
	$res2 = $mysqli->query($sql2);
	while($r2 = mysqli_fetch_assoc($res2))
	{
		return $r2['user_name'];
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
