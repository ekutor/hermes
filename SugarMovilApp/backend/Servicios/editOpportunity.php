<?php

require 'conexion.php';

function editOpportunity($modo,$id,$date_entered,$energia_c,$id_c,$assigned_user_id,$comunicaciones_c,$amount,$amount_usdollar,$nameAccount,$description,$name,$valoroportunidad_c,$date_closed,$probability,$fuente_c,$iluminacion_c,$created_by,$usuario_final_c,$currency_id,$tipo_c,$date_modified,$modified_user_id,$nameCampaign,$deleted,$campaign_id,$lead_source,$medio_c,$idAccount,$opportunity_type,$assigned_user_name,$sales_stage,$next_step)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
	
$description = getTabs($description);	
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	
	$sql= "";$sql2="";$sql5 ="";$sql6="";
	
	//--Si es un opportunity nuevo, lo crea------------------------------------------------------------
	if($modo == 'agregar')
	{			
		$id = md5($name.$fecha);	
		
		$sql5 = "INSERT INTO opportunities (id,name,date_entered,created_by,deleted) 
			VALUES ('$id','$name',now(),'$created_by','0')";
		$res5 = $mysqli->query($sql5);
		
		if(!$res5)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		$sql6 = "INSERT INTO opportunities_cstm (id_c) VALUES ('$id')";
		$res6 = $mysqli->query($sql6);
		
		if(!$res6)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta relacion entre account y opportunity -------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		$idBeanRel = md5($name.$id.$fecha);
		
		$sql9 = "INSERT INTO accounts_opportunities (id,opportunity_id,date_modified,deleted,account_id) 
				VALUES ('$idBeanRel','$id',now(),0,'$idAccount')";
		$res9 = $mysqli->query($sql9);
		
		if(!$res9)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta relacion entre contact y opportunity -------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		/*if($idContacto != null)
		{
			$idBeanRel = md5($idContacto.$fecha);
		
			$sql10 = "INSERT INTO opportunities_contacts (id,opportunity_id,contact_id,date_modified) 
					VALUES ('$idBeanRel','$id','$idContacto','$fecha')";
			$res10 = $mysqli->query($sql10);
			
			if(!$res10)
			{
				$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
				return json_encode($array);
			}
		}*/
		
		
		
	}	
	
	
	//--Edita la tabla opportunities-------------------------------------------------------------------
	$sql = "UPDATE opportunities SET 
	name = '$name',
	date_modified = now(),
	modified_user_id = '$modified_user_id', 
	description = '$description', 
	assigned_user_id = '$assigned_user_id',
	opportunity_type = '$opportunity_type',
	campaign_id = '$campaign_id',
	lead_source = '$lead_source',
	amount_usdollar = '$amount_usdollar',
	currency_id ='$currency_id',
	next_step = '$next_step',
	sales_stage = '$sales_stage' ";
	
	if ($date_closed != '' && $date_closed != 'null') 
	{
		$sql = $sql.",date_closed = '$date_closed' ";
	}
	
	if (ctype_digit((string)$probability)) 
	{
		$sql = $sql.",probability = '$probability' ";
	}
	
	$sql = $sql."WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
		
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	//--Edita la tabla CSTM ------------------------------------------------------------------------------
	$sql2 = "UPDATE opportunities_cstm SET 
	medio_c = '$medio_c', 
	tipo_c = '$tipo_c', 
	energia_c = '$energia_c',
	comunicaciones_c = '$comunicaciones_c',
	iluminacion_c = '$iluminacion_c',
	usuario_final_c = '$usuario_final_c',
	fuente_c = '$fuente_c' ";
	
	if (ctype_digit((string)$valoroportunidad_c)) 
	{
		$sql2 = $sql2.",valoroportunidad_c = '$valoroportunidad_c' ";
	}
	
	$sql2 = $sql2."WHERE id_c = '$id'";
	
	$res2 = $mysqli->query($sql2);
		
	if(!$res2)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	//--Edita el account asociado a la oportunidad ------------------------------------------------------
	$sql3 = "UPDATE accounts_opportunities SET account_id = '$idAccount',date_modified = now() WHERE opportunity_id = '$id'";
	$res3 = $mysqli->query($sql3);
	
	if(!$res3)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
		
		
		
	
		
	if($res && $res2)
	{
		$array = array("respuesta" => "OK");
		
		return json_encode($array);
	}
	else
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error,"SQL1"=>$sql,"SQL2"=>$sql2,"SQL5"=>$sql5,"SQL6"=>$sql6);
		return json_encode($array);
	}
}

function getTabs($value){

  $text = str_replace("*.#.", "\n", $value);
  $text = str_replace("*##*", "\t", $text);
 return $text;
}

?>
