<?php

require 'conexion.php';
require_once('IOManager.php');
require 'utils.php';


function editSubtask($modo,$id,$asunto,$descripcion,$estado,$fechaInicio,$fechaVence,$contacto,$asignado,$tipoRelacion,$idRelacion,$idUsuarioLogueado)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
	$log = new IOManager();	
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	
		$descripcion = getTabs($descripcion);
	
	//--Si es un task nuevo, lo crea------------------------------------------------------------
	if($modo == 'agregar')
	{			
		$id = md5($asunto.$fecha);	
		
		$sql5 = "INSERT INTO notes (id,name,date_entered,created_by) VALUES ('$id','$asunto','$fecha','$idUsuarioLogueado')";
		$res5 = $mysqli->query($sql5);
		$log->log($sql5);
		if(!$res5)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		
		$sql6 = "INSERT INTO notes_cstm (id_c) VALUES ('$id')";
		$res6 = $mysqli->query($sql6);
		
		if(!$res6)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
	}
	
	//function editTask($metodo,$id,$asunto,$estimado,$descripcion,$estado,$fechaInicio,$fechaVence,$contacto,$prioridad,$asignado,$tipoRelacion,$idRelacion,$idUsuarioLogueado)
	
	//--Edita la tabla tasks-------------------------------------------------------------------
	$sql = "UPDATE notes SET 
	name = '$asunto',
	date_modified = STR_TO_DATE('$fecha','%Y/%m/%d %H:%i:%s'),
	modified_user_id = '$idUsuarioLogueado', 
	description = '$descripcion', 
	assigned_user_id = '$asignado',
	parent_type = '$tipoRelacion',
	parent_id = '$idRelacion',
	contact_id = '$contacto'";
	
	$sql = $sql." WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
	$log->log($sql);	
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	//--Edita la tabla CSTM ------------------------------------------------------------------------------
	$sql2 = "UPDATE notes_cstm SET 
	estado_c = '$estado'";
	
	if ( $fechaInicio != 'null' && !empty($fechaInicio))  
	{	
		$sql2 = $sql2.",fechainicio_c = STR_TO_DATE('$fechaInicio','%Y-%m-%d') ";
	}
	
	if ($fechaVence != 'null' && !empty($fechaVence)) 
	{
		$sql2 = $sql2.",fechafin_c = STR_TO_DATE('$fechaVence','%Y-%m-%d') ";
	}
	
	$sql2 = $sql2." WHERE id_c = '$id'";
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
