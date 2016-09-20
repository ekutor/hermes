<?php

require 'conexion.php';

function getSubTasks($currentUser = null)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT id,name FROM notes t , notes_cstm nc WHERE t.id = nc.id_c AND nc.estado_c != 'Completed' AND t.deleted = '0' ";
	if(!empty($currentUser)){
		$sql .= " AND t.assigned_user_id = '$currentUser'";
	}
	$sql .= " ORDER BY name ASC";
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

function getSubTask($idNote)
{
	$sql = "SELECT t.*, nc.*, ";
	$sql .= "CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name ";
    $sql .= "FROM notes t , notes_cstm nc, users u WHERE t.id = nc.id_c AND nc.estado_c != 'Completed' AND t.deleted = '0' ";
	$sql .= "AND t.assigned_user_id = u.id ";
    $sql .= "AND t.id = '$idNote' ";

	 return getInfoFromBD($sql);
}

function getSubTaskxTask($idTask)
{
     $sql = "SELECT t.*, nc.*, ";
	$sql .= "CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name ";
    $sql .= "FROM notes t , notes_cstm nc, users u WHERE t.id = nc.id_c AND nc.estado_c != 'Completed' AND t.deleted = '0' ";
	$sql .= "AND t.assigned_user_id = u.id ";
	$sql .= "AND t.parent_type='Tasks' ";
	$sql .= "AND t.parent_id='$idTask'";

    return getInfoFromBD($sql);
}



function getInfoFromBD($sql)
{	
		//Realiza el query en la base de datos
        $mysqli = makeSqlConnection();
		
		$res = $mysqli->query($sql);

        $rows = array();
        while($r = mysqli_fetch_assoc($res))
        {
                $obj = (object) $r;
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
	else if($parent_type == 'Tasks')
	{
		$sql = "SELECT name FROM tasks WHERE id = '$parent_id'";
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
