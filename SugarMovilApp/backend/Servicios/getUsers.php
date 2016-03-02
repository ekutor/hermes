<?php

require 'conexion.php';

function getUsers()
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT u.id,u.user_name,u.first_name,u.last_name,u.is_admin,r.id as role ";
	$sql.= "FROM users u,acl_roles_users r ";
	$sql.= "WHERE r.user_id= u.id AND r.deleted= 0 AND employee_status='Active' AND u.deleted='0' ORDER BY user_name ASC";
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
