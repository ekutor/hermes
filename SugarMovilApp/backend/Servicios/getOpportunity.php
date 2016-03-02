<?php

require 'conexion.php';

function getOpportunity($idOpportunity)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM opportunities a LEFT JOIN opportunities_cstm ac ON a.id = ac.id_c WHERE id = '$idOpportunity'";
	
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
		
		$obj->idAccount = getAccountId($idOpportunity);
		$obj->nameAccount = getAccountName($obj->idAccount);
		$obj->nameCampaign = getCampaignName($obj->campaign_id);
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




function getAccountId($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM accounts_opportunities WHERE opportunity_id = '$id'";
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
