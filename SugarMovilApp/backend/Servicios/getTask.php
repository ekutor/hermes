<?php

require 'conexion.php';

function getTask($idTask)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT t.id,t.name,t.date_entered,t.date_modified,t.modified_user_id,t.created_by,t.description,t.deleted,t.assigned_user_id,t.status,t.date_due_flag
,t.date_due,t.date_start_flag,t.date_start,t.parent_type,t.parent_id,t.contact_id,t.priority,tc.id_c,tc.aviso_c,tc.trabajo_estimado_c,tc.ejecuted_date_c,CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name 
FROM tasks t LEFT JOIN tasks_cstm tc 
ON t.id = tc.id_c 
JOIN users u ON t.assigned_user_id = u.id
WHERE t.id = '$idTask' ";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
			
		//$obj->assigned_user_name = getUserName($obj->assigned_user_id);
		$obj->contact_name = getContactName($obj->contact_id);
		$obj->parent_name = getParentName($obj->parent_type,$obj->parent_id);
		
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

function getTaskxAccount($idAccount)
{
        //Realiza el query en la base de datos
        $mysqli = makeSqlConnection();
        $sql = "SELECT t.id,t.name,t.date_entered,t.date_modified,t.modified_user_id,t.created_by,t.description,t.deleted,t.assigned_user_id,t.status,t.date_due_flag
,t.date_due,t.date_start_flag,t.date_start,t.parent_type,t.parent_id,t.contact_id,t.priority,tc.id_c,tc.aviso_c,tc.trabajo_estimado_c,tc.ejecuted_date_c,CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name
FROM tasks t LEFT JOIN tasks_cstm tc
ON t.id = tc.id_c
JOIN users u ON t.assigned_user_id = u.id
WHERE t.parent_id = '$idAccount' AND t.parent_type='Accounts' ";
        $res = $mysqli->query($sql);

        $rows = array();

        while($r = mysqli_fetch_assoc($res))
        {
                $obj = (object) $r;

                //$obj->assigned_user_name = getUserName($obj->assigned_user_id);
                $obj->contact_name = getContactName($obj->contact_id);
                $obj->parent_name = getParentName($obj->parent_type,$obj->parent_id);

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


function getParentName($parent_type,$parent_id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = '';
	
	if($parent_type == 'Accounts')
	{
		$sql = "SELECT name FROM accounts WHERE id = '$parent_id'";
		$res = $mysqli->query($sql);
		while($r = mysqli_fetch_assoc($res))
		{
			$obj = (object) $r;	
			return $obj->name;
		}
	}
    else if($parent_type == 'Contacts')
	{
		$sql = "SELECT first_name FROM contacts WHERE id = '$parent_id'";
		$res = $mysqli->query($sql);
		while($r = mysqli_fetch_assoc($res))
		{
			$obj = (object) $r;	
			return $obj->first_name;
		}
	}
	else if($parent_type == 'Leads')
	{
		$sql = "SELECT first_name,last_name FROM leads WHERE id = '$parent_id'";
		$res = $mysqli->query($sql);
		while($r = mysqli_fetch_assoc($res))
		{
			$obj = (object) $r;	
			$temp = $obj->first_name.' '.$obj->last_name;
			return $temp;
		}
	}
	else if($parent_type == 'Opportunities')
	{
		$sql = "SELECT name FROM opportunities WHERE id = '$parent_id'";
		$res = $mysqli->query($sql);
		while($r = mysqli_fetch_assoc($res))
		{
			$obj = (object) $r;	
			return $obj->name;
		}
	}
	else 
	{
		return '';	
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
