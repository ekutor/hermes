<?php

require 'conexion.php';

function getOpportunityContacts($idOpportunity)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT contacts.id,contacts.first_name FROM contacts 
			LEFT JOIN opportunities_contacts 
			ON opportunities_contacts.contact_id = contacts.id 
			WHERE opportunities_contacts.opportunity_id = '$idOpportunity' 
			AND contacts.deleted = '0'";
			

	
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$rows[] = $r;
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
