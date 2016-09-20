<?php

require 'conexion.php';

function getOpportunities($currentUser = null)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT id,name FROM opportunities WHERE deleted = '0'";
	
	if(!empty($currentUser)){
		$sql .= " AND assigned_user_id = '$currentUser'";
	}
	$sql .= " ORDER BY name ASC";
	
	$res = $mysqli->query($sql);
	
	$rows = array();
	
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
