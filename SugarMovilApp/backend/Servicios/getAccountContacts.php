<?php

require 'conexion.php';

function getAccountContacts($idContact)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT a.id,a.name,acs.uen_c AS uen FROM accounts a ,accounts_cstm acs, accounts_contacts ac WHERE  a.id = acs.id_c AND a.id = ac.account_id AND  a.deleted = '0' AND ac.contact_id = '$idContact'";
			

	
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
