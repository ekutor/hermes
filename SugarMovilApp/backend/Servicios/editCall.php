<?php

require 'conexion.php';

function editCall($modo,$id,$asunto,$resultado,$descripcion,$direccion,$estado,$fechaInicio,$duracion,$parent_id,$parent_type,$idCampana,$idUsuarioLogueado,$idContacto)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
		
	$mysqli = makeSqlConnection();
	
	
	
	
	//--Si es un opportunity nuevo, lo crea------------------------------------------------------------
	if($modo == 'agregar')
	{			
		$id = md5($asunto.$fecha);	
		
		$sql5 = "INSERT INTO calls (id,name,created_by,date_entered,assigned_user_id) VALUES ('$id','$asunto','$idUsuarioLogueado','$fecha','$idUsuarioLogueado')";
		$res5 = $mysqli->query($sql5);
		
		if(!$res5)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		$sql6 = "INSERT INTO calls_cstm (id_c) VALUES ('$id')";
		$res6 = $mysqli->query($sql6);
		
		if(!$res6)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		
		//------------------------------------------------------------------------------------------------------------------------------------------
		//Inserta relacion entre contact y calls -------------------------------------------------------------------------------------------------
		//------------------------------------------------------------------------------------------------------------------------------------------
		if($idContacto != null)
		{
			$idBeanRel = md5($idContacto.$fecha);
		
			$sql10 = "INSERT INTO calls_contacts (id,call_id,contact_id,date_modified) 
					VALUES ('$idBeanRel','$id','$idContacto','$fecha')";
			$res10 = $mysqli->query($sql10);
			
			if(!$res10)
			{
				$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
				return json_encode($array);
			}
		}
		
		
		
	}
	
	
	
	
	
	
	//--Edita la tabla calls   -------------------------------------------------------------------
	$sql = "UPDATE calls SET 
	name = '$asunto',
	date_modified = '$fecha',
	modified_user_id = '$idUsuarioLogueado', 
	description = '$descripcion', 
	status = '$estado',
	direction = '$direccion' ";
	
	if ($fechaInicio != '' && $fechaInicio != '<null>') 
	{
		$sql = $sql.",date_start = '$fechaInicio' ";
	}
	
	if ($parent_id != '' && $parent_id != '<null>') 
	{
		$sql = $sql.",parent_id = '$parent_id' ";
	}
	
	if ($parent_type != '' && $parent_type != '<null>') 
	{
		$sql = $sql.",parent_type = '$parent_type' ";
	}
	
	if (ctype_digit((string)$duracion)) 
	{
		$sql = $sql.",duration_minutes = '$duracion' ";
	}
	
	$sql = $sql."WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
		
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	//--Edita la tabla calls_cstm   -------------------------------------------------------------------
	$sql2 = "UPDATE calls_cstm SET resultadodelallamada_c = '$resultado'  WHERE id_c = '$id'";
	$res2 = $mysqli->query($sql2);
	
	if(!$res2)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	
	//--Edita la campana del call   -------------------------------------------------------------------
	$sql3 = "DELETE FROM calls_campaigns_1_c WHERE calls_campaigns_1calls_ida = '$id'";
	$res3 = $mysqli->query($sql3);
	
	$id_temp = md5($fecha.$id);
	$sql3 = "INSERT INTO calls_campaigns_1_c (id,calls_campaigns_1calls_ida) VALUES ('$id_temp','$id')";
	$res3 = $mysqli->query($sql3);
	
	$sql3 = "UPDATE calls_campaigns_1_c SET calls_campaigns_1campaigns_idb = '$idCampana'  WHERE calls_campaigns_1calls_ida = '$id'";
	$res3 = $mysqli->query($sql3);
	
	if(!$res3)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
		
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

?>
