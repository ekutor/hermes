<?php

require 'conexion.php';

function getNotes($currentUser = null)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT a.id, a.document_name FROM adm_notas a,  notes_adm_notas_1_c ar WHERE a.id = ar.notes_adm_notas_1adm_notas_idb AND a.status_id != 'Completed' AND a.deleted = '0' AND ar.deleted = '0'";
	if(!empty($currentUser)){
		$sql .= " AND a.assigned_user_id = '$currentUser'";
	}
	$sql .= " ORDER BY document_name ASC";
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

function getNote($idNote)
{
	$sql = "SELECT a.*, ar.notes_adm_notas_1notes_ida as parent_id,";
	$sql .= "CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name ";
	$sql .= "FROM adm_notas a,  notes_adm_notas_1_c ar, users u ";
    $sql .= "WHERE a.id = ar.notes_adm_notas_1adm_notas_idb AND a.status_id != 'Completed' AND a.deleted = '0' AND ar.deleted = '0' ";
	$sql .= "AND a.assigned_user_id = u.id ";
    $sql .= "AND a.id = '$idNote' ";

	 return getInfoFromBD($sql);
}

function getNotesxSubTask($idTask)
{
 	$sql = "SELECT a.*, ar.notes_adm_notas_1notes_ida as parent_id,";
	$sql .= "CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name ";
	$sql .= "FROM adm_notas a,  notes_adm_notas_1_c ar, users u ";
    $sql .= "WHERE a.id = ar.notes_adm_notas_1adm_notas_idb AND a.status_id != 'Completed' AND a.deleted = '0' AND ar.deleted = '0' ";
	$sql .= "AND a.assigned_user_id = u.id ";
    $sql .= "AND ar.notes_adm_notas_1notes_ida = '$idTask' AND ar.notes_adm_notas_1notes_ida != ''";

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
                $obj->parent_name = getParentName($obj->parent_id);

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


function getParentName($parent_id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = '';
	
	$sql = "SELECT name FROM notes WHERE id = '$parent_id'";
	$res = $mysqli->query($sql);
	if($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		return $obj->name;
	}
	else 
	{
		return '';	
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
