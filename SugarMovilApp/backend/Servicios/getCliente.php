<?php

require 'conexion.php';

function getCliente($idCliente)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM leads a LEFT JOIN leads_cstm ac ON a.id = ac.id_c WHERE id = '$idCliente'";
	//$sql = "SELECT id,first_name,last_name FROM leads";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
		
		$obj->email_address = getEmail($idCliente);
		$obj->campaign_name = getCampaignName($obj->campaign_id);
		$obj->assigned_user_name = getUserName($obj->assigned_user_id);
		
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




function getUserName($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT user_name FROM users WHERE id = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->user_name;
	}
}



function getCampaignName($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT name FROM campaigns WHERE id = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->name;
	}
}



function getEmail($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT email_addresses.* 
			FROM email_addresses 
			left join email_addr_bean_rel 
			on email_addresses.id = email_addr_bean_rel.email_address_id 
			where email_addr_bean_rel.bean_id = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->email_address;
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
