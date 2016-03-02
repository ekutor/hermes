<?php

require 'conexion.php';

function getAccount($idAccount)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM accounts a, accounts_cstm ac WHERE a.id = ac.id_c AND a.id = '$idAccount'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		$idUsuario = $obj->assigned_user_id;
		
		
		$sql2 = "SELECT user_name FROM users WHERE id = '$idUsuario'";
		$res2 = $mysqli->query($sql2);
		while($r2 = mysqli_fetch_assoc($res2))
		{
			$obj->assigned_user_name = $r2['user_name'];
		}
		
		$obj->email_address = getAccountEmail($idAccount);
		
		//$obj->hash = crypt(md5("C4l4mb31"));
		
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


function getAccountEmail($idAccount)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT email_addresses.* 
			FROM email_addresses 
			left join email_addr_bean_rel 
			on email_addresses.id = email_addr_bean_rel.email_address_id 
			where email_addr_bean_rel.bean_id = '$idAccount'";
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
