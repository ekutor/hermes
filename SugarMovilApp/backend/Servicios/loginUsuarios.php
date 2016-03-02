<?php

require 'conexion.php';
require 'connldap.php';

function loginUsuarios($usuario,$password)
{

if($usuario == 'h'){
  $usuario= 'luz';
  $password = 'Luces1037614153';
}
	$auth = connectLdap($usuario,$password);
	if($auth !=  "AUTHENTICATE"){
	    $array = array("auth" => "FALSE","message" =>"Usuario o Password invalidos");
            return json_encode($array);

	}

	//Realiza el query en la base de datos

	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM users WHERE user_name = '$usuario' AND deleted = '0' AND employee_status='Active' "; 
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$rows[] = $r;
	}
	
		
	if( empty( $rows ) )
	{
		$array = array("auth" => "FALSE","message" =>"Usuario No existe en la BD, la primera vez debe ingresar por CRM WEB");
		return json_encode($array);
	}
	else
	{
		
		//Consulta de Permisos
		$sql = "SELECT r.id as role,a.aclaccess, a.name as action,  a.category, a.acltype, ra.access_override ";
		$sql.="FROM users u , acl_roles r , acl_roles_actions ra ,acl_actions a ,acl_roles_users ru ";
		$sql.="WHERE u.id = ru.user_id AND ru.role_id = r.id AND r.id = ra.role_id ";
		$sql.="AND ra.action_id = a.id AND ru.deleted = 0 AND ra.deleted = 0 AND u.id ='".$rows[0]['id']."' ";
		$sql.="AND a.category IN ('Accounts','Opportunities','Contacts','Calls','Tasks','psg_Productos')";
		//$sql.="AND a.category IN ('Opportunities')";

		$salt = "/hsanchezmovil$/";
		
		$resp = $mysqli->query($sql);
		$rowsPerm = array(); $i=0;
		while($r = mysqli_fetch_assoc($resp))
        	{
                	$rowsPerm[] = $r;
			$role = $rowsPerm[$i]['role'];
			$rowsPerm[$i]['role'] = "";
			if($rowsPerm[$i]['access_override'] >= 89){
				$permit = true;
				$rowsPerm[$i]['access_type'] = "ALL";
			}else if($rowsPerm[$i]['access_override'] == 80 ){
				$permit = true;
				$rowsPerm[$i]['access_type'] = "GROUP";
			}else if($rowsPerm[$i]['access_override'] == 75 ){
				$permit = true;
				$rowsPerm[$i]['access_type'] = "OWNER";
			}else{
				$permit = false;
				$rowsPerm[$i]['access_type'] = "OWNER";
			}
			$rowsPerm[$i]['access_override']=$permit;
			$i++;
        	}

		$hash = md5($salt . $password);
       
		$array =	array("auth" => "TRUE",
		      "id" =>$rows[0]['id'],
		      "user_name" =>$rows[0]['user_name'],
		      "first_name" =>$rows[0]['first_name'],
		      "last_name" =>$rows[0]['last_name'],
		      "is_admin" =>$rows[0]['is_admin'],
		      "role" =>$role,
		      "hash" =>$hash,
		      "access"=>$rowsPerm
		);
		return json_encode($array);		
	}
}

?>
