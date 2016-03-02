<?php

require 'conexion.php';

function getContact($idContact)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM contacts a LEFT JOIN contacts_cstm ac ON a.id = ac.id_c WHERE id = '$idContact'";
	
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
			
		$obj->email_address = getEmail($idContact);	
		$obj->idAccount = getAccountId($idContact);
		$obj->nameAccount = getAccountName($obj->idAccount);
		$obj->reports_to_name = getContactName($obj->reports_to_id);
		$obj->created_by_name = getUserName($obj->created_by);
		$obj->modified_user_name = getUserName($obj->modified_user_id);
		$obj->assigned_user_name = getUserName($obj->user_id_c);
		$obj->nameCampaign = getCampaignName($obj->campaign_id);
		
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




function getAccountId($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM accounts_contacts WHERE contact_id = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->account_id;
	}
}


function getAccountName($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT name FROM accounts WHERE id = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->name;
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


function getContactName($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT first_name FROM contacts WHERE id = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->first_name;
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
