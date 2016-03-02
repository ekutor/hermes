<?php

require 'conexion.php';

function getOpportunityCalls($idOpportunity)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT id,name FROM calls  
	WHERE deleted = '0' AND parent_type = 'Opportunities' AND parent_id = '$idOpportunity' 
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
