<?php

require 'conexion.php';


function getLead($idLead)
{
	$sql = "SELECT a.*,ac.*, ";
	$sql .= "CONCAT_WS(' ',u.first_name,u.last_name) as assigned_user_name ";
    $sql .= "FROM leads a  , leads_cstm ac , users u WHERE a.id = ac.id AND a.deleted = '0' ";
	$sql .= "AND t.assigned_user_id = u.id ";
    $sql .= "AND a.id = '$idLead' ";
	return getAdditionalInfoFromBD($sql);
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


function getAdditionalInfoFromBD($sql)
{	
		//Realiza el query en la base de datos
        $mysqli = makeSqlConnection();
		
		$res = $mysqli->query($sql);

        $rows = array();
        while($r = mysqli_fetch_assoc($res))
        {
                $obj = (object) $r;
                $obj->email_address = getEmail($obj->id);
				$obj->campaign_name = getCampaignName($obj->campaign_id);

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
