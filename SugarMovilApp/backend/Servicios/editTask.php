<?php

require 'conexion.php';

function editTask($modo,$id,$asunto,$estimado,$descripcion,$estado,$fechaInicio,$fechaVence,$contacto,$prioridad,$asignado,$tipoRelacion,$idRelacion,$idUsuarioLogueado)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
		
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	
	
	
	//--Si es un task nuevo, lo crea------------------------------------------------------------
	if($modo == 'agregar')
	{			
		$id = md5($asunto.$fecha);	
		
		$sql5 = "INSERT INTO tasks (id,name,date_entered,created_by) VALUES ('$id','$asunto','$fecha','$idUsuarioLogueado')";
		$res5 = $mysqli->query($sql5);
		
		if(!$res5)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		$sql6 = "INSERT INTO tasks_cstm (id_c) VALUES ('$id')";
		$res6 = $mysqli->query($sql6);
		
		if(!$res6)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
	}	
	
	
	
	
	
	//function editTask($metodo,$id,$asunto,$estimado,$descripcion,$estado,$fechaInicio,$fechaVence,$contacto,$prioridad,$asignado,$tipoRelacion,$idRelacion,$idUsuarioLogueado)
	
	//--Edita la tabla tasks-------------------------------------------------------------------
	$sql = "UPDATE tasks SET 
	name = '$asunto',
	date_modified = '$fecha',
	modified_user_id = '$idUsuarioLogueado', 
	description = '$descripcion', 
	assigned_user_id = '$asignado',
	status = '$estado',
	parent_type = '$tipoRelacion',
	parent_id = '$idRelacion',
	contact_id = '$contacto',
	priority = '$prioridad' ";
	
	if ($fechaInicio != '' && $fechaInicio != '<null>') 
	{
		$sql = $sql.",date_start = '$fechaInicio' ";
	}
	
	if ($fechaVence != '' && $fechaVence != '<null>') 
	{
		$sql = $sql.",date_due = '$fechaVence' ";
	}
	
	$sql = $sql."WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
		
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	//--Edita la tabla CSTM ------------------------------------------------------------------------------
	$sql2 = "UPDATE tasks_cstm SET 
	trabajo_estimado_c = '$estimado' ";
	
	$sql2 = $sql2."WHERE id_c = '$id'";
	
	$res2 = $mysqli->query($sql2);
		
	if(!$res2)
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
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
}

?>
