<?php

require 'conexion.php';

function getAccountOpportunities($idAccount)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT opportunities.id,opportunities.name FROM opportunities 
			LEFT JOIN accounts_opportunities 
			ON accounts_opportunities.opportunity_id = opportunities.id 
			WHERE accounts_opportunities.account_id = '$idAccount' 
			AND opportunities.deleted = '0' 
			AND accounts_opportunities.deleted = '0'";
			
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
