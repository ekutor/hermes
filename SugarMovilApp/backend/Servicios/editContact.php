<?php

require 'conexion.php';

function editContact($modo,$id,$name,$identificacion,$cumpleanos,$genero,$cargo,$certificaciones,$profesion,$tipoContacto,$telefono1,$extension1,$telefono2,$extension2,
	$celular,$fax,$email,$cuenta,$departamento,$municipio,$direccion,$segmento,$grupoObjetivo,$uen,$zona,$canal,$sector,$estado,$regalo1,$fechaRegalo1,$motivoRegalo1,
	$regalo2,$fechaRegalo2,$motivoRegalo2,$regalo3,$fechaRegalo3,$motivoRegalo3,$regalo4,$fechaRegalo4,$motivoRegalo4,$regalo5,$fechaRegalo5,$motivoRegalo5,
	$informa,$tomaContacto,$nollamar,$campana,$diligenciado,$modificado,$responsable,$idUsuarioLogueado,$idOpportunity)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");
	
	$mysqli = makeSqlConnection();
	
	if($modo == 'agregar')
	{
		//return "que damier";	
			
		$id = md5($identificacion.$fecha);	
		
		$sql5 = "INSERT INTO contacts (id,first_name,created_by) VALUES ('$id','$name','$idUsuarioLogueado')";
		$res5 = $mysqli->query($sql5);
		
		if(!$res5)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error, "modulo" => "inserta contacto");
			return json_encode($array);
		}
		
		$sql6 = "INSERT INTO contacts_cstm (id_c) VALUES ('$id')";
		$res6 = $mysqli->query($sql6);
		
		if(!$res6)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error, "modulo" => "inserta contacto cstm");
			return json_encode($array);
		}
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta el nuevo email -------------------------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		$idEmail = md5($name.$email.$fecha);
		$str = strtoupper($email);
		
		//Se crea el email
		$sql7 = "INSERT INTO email_addresses (id,email_address,email_address_caps,date_created) 
				VALUES ('$idEmail','$email','$str','$fecha')";
		$res7 = $mysqli->query($sql7);
		
		if(!$res7)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error, "modulo" => "inserta email");
			return json_encode($array);
		}
		
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta el nuevo email_rel ---------------------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		$idBeanRel = md5($idEmail.$id.$fecha);
		
		$sql8 = "INSERT INTO email_addr_bean_rel (id,email_address_id,bean_id,bean_module,primary_address,date_created) 
				VALUES ('$idBeanRel','$idEmail','$id','contacts','1','$fecha')";
		$res8 = $mysqli->query($sql8);
		
		if(!$res8)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error, "modulo" => "inserta relacion email");
			return json_encode($array);
		}
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta relacion entre account y contact -------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		$idBeanRel = md5($name.$id.$fecha);
		
		$sql9 = "INSERT INTO accounts_contacts (id,contact_id,date_modified) 
				VALUES ('$idBeanRel','$id','$fecha')";
		$res9 = $mysqli->query($sql9);
		
		if(!$res9)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error, "modulo" => "account-contact");
			return json_encode($array);
		}
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta relacion entre opportunity y contact ---------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		if($idOpportunity != '')
		{
			$idBeanRel = md5($name.$id.$fecha);
			$sql10 = "INSERT INTO opportunities_contacts (id,contact_id,opportunity_id,date_modified) 
					VALUES ('$idBeanRel','$id','$idOpportunity','$fecha')";
			$res10 = $mysqli->query($sql10);
			
			if(!$res10)
			{
				$array = array("respuesta" => "FAIL", "error" => $mysqli->error, "modulo" => "opportunity-contact");
				return json_encode($array);
			}
		}
	}	
		
	//Realiza el query en la base de datos
	
	$sql = "UPDATE contacts SET 
	date_modified = '$fecha',
	modified_user_id = '$idUsuarioLogueado',
	assigned_user_id = '$responsable', 
	first_name = '$name', 
	title = '$cargo',
	phone_work = '$telefono1',
	phone_other = '$telefono2',
	phone_mobile = '$celular',
	lead_source = '$tomaContacto',
	reports_to_id = '$informa',
	campaign_id = '$campana',
	phone_fax = '$fax' ";
	
	if ($cumpleanos != '' && $cumpleanos != '<null>') 
	{
		$sql = $sql.",birthdate = '$cumpleanos' ";
	}
	
	if (ctype_digit((string)$nollamar)) 
	{
		$sql = $sql.",do_not_call = '$nollamar' ";
	}
	
	$sql = $sql."WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
		
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	$sql2 = "UPDATE contacts_cstm SET 
	genero_c = '$genero',
	profesion_c = '$profesion',
	certificaciones_c = '$certificaciones', 
	tipocontacto_c = '$tipoContacto', 
	departamento_c = '$departamento',
	regalo1_c = '$regalo1',
	regalo2_c = '$regalo2',
	regalo3_c = '$regalo3',
	regalo4_c = '$regalo4',
	regalo5_c = '$regalo5',
	mregalo1_c = '$motivoRegalo1',
	mregalo2_c = '$motivoRegalo2',
	mregalo3_c = '$motivoRegalo3',
	mregalo4_c = '$motivoRegalo4',
	mregalo5_c = '$motivoRegalo5',
	segmento_c = '$segmento',
	uen_c = '$uen',
	canal_c = '$canal',
	grupo_objetivo_c = '$grupoObjetivo',
	estado_cliente_c = '$estado',
	direccion_c = '$direccion',
	zona_c = '$zona',
	identificacion_c = '$identificacion',
	user_id_c = '$responsable',
	sector_c = '$sector',
	municipio_c = '$municipio' ";
	
	if (ctype_digit((string)$extension1)) 
	{
		$sql2 = $sql2.",extension1_c = '$extension1' ";
	}
	
	if (ctype_digit((string)$extension2)) 
	{
		$sql2 = $sql2.",extension2_c = '$extension2' ";
	}
	
	if ($fechaRegalo1 != '' && $fechaRegalo1 != '<null>') 
	{
		$sql2 = $sql2.",fecregalo1_c = '$fechaRegalo1' ";
	}
	if ($fechaRegalo2 != '' && $fechaRegalo2 != '<null>') 
	{
		$sql2 = $sql2.",fecregalo2_c = '$fechaRegalo2' ";
	}
	if ($fechaRegalo3 != '' && $fechaRegalo3 != '<null>') 
	{
		$sql2 = $sql2.",fecregalo3_c = '$fechaRegalo3' ";
	}
	if ($fechaRegalo4 != '' && $fechaRegalo4 != '<null>') 
	{
		$sql2 = $sql2.",fecregalo4_c = '$fechaRegalo4' ";
	}
	if ($fechaRegalo5 != '' && $fechaRegalo5 != '<null>') 
	{
		$sql2 = $sql2.",fecregalo5_c = '$fechaRegalo5' ";
	}
	 
	$sql2 = $sql2."WHERE id_c = '$id'"; 
	
	$res2 = $mysqli->query($sql2);
	
	if(!$res2)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	$sql3 = "UPDATE accounts_contacts SET account_id = '$cuenta',date_modified = '$fecha' WHERE contact_id = '$id'";
	$res3 = $mysqli->query($sql3);
	
	
	
	editarEmail($id, $email);
	
	if($res && $res2 && $res3)
	{
		$array = array("respuesta" => "OK");
		return json_encode($array);
	}
	else
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
}




function editarEmail($idAccount,$email)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
		
	//obtengo el id del email
	$mysqli = makeSqlConnection();
	$sql = "SELECT email_address_id FROM email_addr_bean_rel WHERE bean_id = '$idAccount'";
	$res = $mysqli->query($sql);
	
	$idEmail = "";
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;	
		$idEmail = $obj->email_address_id;
	}
	
	$str = strtoupper($email);
	
	//edito el email
	$sql2 = "UPDATE email_addresses SET email_address = '$email',email_address_caps = '$str',date_modified = '$fecha' WHERE id = '$idEmail'";
	$res2 = $mysqli->query($sql2);
}







?>







