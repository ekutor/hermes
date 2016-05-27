<?php

require 'conexion.php';

function getCall($idCall)
{
	$sql = "SELECT * FROM calls a LEFT JOIN calls_cstm ac ON a.id = ac.id_c WHERE deleted = '0' AND id = '$idCall' ORDER BY name ASC";
	
	return getGenericCall($sql);
}

function getGenericCall($sql)
{
	
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
		
		$obj->created_by_name = getNombreUsuario($obj->created_by);
		
		$obj->assigned_user_name = getNombreUsuario($obj->assigned_user_id);
		
		$obj->parent_name = getParentName($obj->parent_id,$obj->parent_type);
		
		$obj->campaign_name = getCampaignName($obj->id);
		
		$obj->campaign_id = getCampaignId($obj->id);
		
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

function getAccountCalls($idAccount)
{
	$sql = "SELECT * FROM calls a LEFT JOIN calls_cstm ac ON a.id = ac.id_c 
	WHERE a.parent_id='$idAccount' AND deleted = '0' ORDER BY name ASC";
		
	return getGenericCall($sql);
}


function getContactCalls($idContact)
{
	$sql = "SELECT * FROM calls a LEFT JOIN calls_cstm ac ON a.id = ac.id_c 
	LEFT JOIN calls_contacts ON a.id = calls_contacts.call_id 
	WHERE calls_contacts.deleted = '0' 
	AND a.deleted = '0' 
	AND calls_contacts.contact_id = '$idContact' 
	ORDER BY name ASC";
		
	return getGenericCall($sql);
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



function getParentName($id,$tipo)
{
	if($tipo == 'Accounts')
	{
		$mysqli = makeSqlConnection();
		$sql2 = "SELECT name FROM accounts WHERE id = '$id'";
		$res2 = $mysqli->query($sql2);
		while($r2 = mysqli_fetch_assoc($res2))
		{
			return $r2['name'];
		}
	}
	else
	{
		return "";			
	}
}




function getCampaignId($id)
{
	$mysqli = makeSqlConnection();
	$sql2 = "SELECT calls_campaigns_1campaigns_idb FROM calls_campaigns_1_c WHERE calls_campaigns_1calls_ida = '$id'";
	$res2 = $mysqli->query($sql2);
	$idCamapna = '';
	while($r2 = mysqli_fetch_assoc($res2))
	{
		return $r2['calls_campaigns_1campaigns_idb'];
	}
}





function getCampaignName($id)
{
	$mysqli = makeSqlConnection();
	$sql2 = "SELECT calls_campaigns_1campaigns_idb FROM calls_campaigns_1_c WHERE calls_campaigns_1calls_ida = '$id'";
	$res2 = $mysqli->query($sql2);
	$idCamapna = '';
	while($r2 = mysqli_fetch_assoc($res2))
	{
		$idCamapna = $r2['calls_campaigns_1campaigns_idb'];
	}
	
	$sql2 = "SELECT name FROM campaigns WHERE id = '$idCamapna'";
	$res2 = $mysqli->query($sql2);
	while($r2 = mysqli_fetch_assoc($res2))
	{
		return $r2['name'];
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
