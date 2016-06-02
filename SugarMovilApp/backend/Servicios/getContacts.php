<?php

require 'conexion.php';

function getContacts()
{
        //Realiza el query en la base de datos
        $mysqli = makeSqlConnection();
        $sql = "SELECT c.id,c.first_name,title,phone_mobile,phone_work,phone_other,phone_fax,accounts_contacts.account_id 
		FROM contacts c, accounts_contacts 
		WHERE c.deleted = '0' 
		AND c.id = accounts_contacts.contact_id 
		ORDER BY first_name ASC";
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

function getContactsxAccount($idAccount)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT contacts.id,contacts.first_name,contacts.title,contacts.phone_mobile,contacts.phone_work,contacts.phone_other,
	contacts.phone_fax,accounts_contacts.account_id 
	FROM contacts, accounts_contacts
	WHERE contacts.deleted = '0' 
	AND contacts.id = accounts_contacts.contact_id
	AND accounts_contacts.account_id= '$idAccount'
	ORDER BY contacts.first_name ASC";
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
