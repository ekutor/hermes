<?php

require 'conexion.php';
require_once('IOManager.php');

function editTask($modo,$id,$asunto,$estimado,$descripcion,$estado,$fechaInicio,$fechaVence,$contacto,$prioridad,$asignado,$tipoRelacion,$idRelacion,$idUsuarioLogueado)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
	$log = new IOManager();	
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	
	
	
	//--Si es un task nuevo, lo crea------------------------------------------------------------
	if($modo == 'agregar')
	{			
		$id = md5($asunto.$fecha);	
		
		$sql5 = "INSERT INTO tasks (id,name,date_entered,created_by) VALUES ('$id','$asunto','$fecha','$idUsuarioLogueado')";
		$res5 = $mysqli->query($sql5);
		$log->log($sql5);
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
	date_modified = STR_TO_DATE('$fecha','%Y/%m/%d %H:%i:%s'),
	modified_user_id = '$idUsuarioLogueado', 
	description = '$descripcion', 
	assigned_user_id = '$asignado',
	status = '$estado',
	parent_type = '$tipoRelacion',
	parent_id = '$idRelacion',
	contact_id = '$contacto',
	priority = '$prioridad' ";
	
	if ( $fechaInicio != 'null' && !empty($fechaInicio))  
	{	
		$sql = $sql.",date_start = STR_TO_DATE('$fechaInicio','%Y-%m-%d %H:%i:%s') ";
	}
	
	if ($fechaVence != 'null' && !empty($fechaVence)) 
	{
		$sql = $sql.",date_due = STR_TO_DATE('$fechaVence','%Y-%m-%d %H:%i:%s') ";
	}
	
	$sql = $sql." WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
	$log->log($sql);	
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	//--Edita la tabla CSTM ------------------------------------------------------------------------------
	$sql2 = "UPDATE tasks_cstm SET 
	trabajo_estimado_c = '$estimado' ";
	
	$sql2 = $sql2."WHERE id_c = '$id'";
	$log->log($sql2);
	$res2 = $mysqli->query($sql2);
		
	if(!$res2)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
		
		
	
		
	if($res && $res2)
	{
		$array = array("respuesta" => "OK" , "id" => $id);
		$log->log("OK");
		return json_encode($array);
	}
	else
	{
		$log->log("fail");
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
}

?>
