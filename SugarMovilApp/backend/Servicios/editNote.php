<?php

require 'conexion.php';
require_once('IOManager.php');
require 'utils.php';


function editNote($modo,$id,$asunto,$descripcion,$estado,$fechaInicio,$fechaVence,$asignado,$idRelacion,$idUsuarioLogueado)
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
		
		$sql5 = "INSERT INTO adm_notas (id,document_name,date_entered,created_by) VALUES ('$id','$asunto','$fecha','$idUsuarioLogueado')";
		$res5 = $mysqli->query($sql5);
		$log->log($sql5);
		if(!$res5)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
		$fecha = date("Y/m/d h:i:s");
		$id2 = md5($asunto.$fecha);
		$sql6 = "INSERT INTO notes_adm_notas_1_c (id,date_modified,deleted,notes_adm_notas_1notes_ida,notes_adm_notas_1adm_notas_idb) ";
		$sql6 .= "VALUES ('$id2','$fecha','0','$idRelacion','$id')";
		$res6 = $mysqli->query($sql6);
		
		if(!$res6)
		{
			$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
			return json_encode($array);
		}
	}
	
	//function editTask($metodo,$id,$asunto,$estimado,$descripcion,$estado,$fechaInicio,$fechaVence,$contacto,$prioridad,$asignado,$tipoRelacion,$idRelacion,$idUsuarioLogueado)
	
	//--Edita la tabla tasks-------------------------------------------------------------------
	$sql = "UPDATE adm_notas SET 
	document_name = '$asunto',
	date_modified = STR_TO_DATE('$fecha','%Y/%m/%d %H:%i:%s'),
	modified_user_id = '$idUsuarioLogueado', 
	description = '$descripcion', 
	status_id = '$estado',
	assigned_user_id = '$asignado'";
	
	if ( $fechaInicio != 'null' && !empty($fechaInicio))  
	{	
		$sql = $sql.",active_date = STR_TO_DATE('$fechaInicio','%Y-%m-%d %H:%i:%s') ";
	}
	
	if ($fechaVence != 'null' && !empty($fechaVence)) 
	{
		$sql = $sql.",exp_date = STR_TO_DATE('$fechaVence','%Y-%m-%d %H:%i:%s') ";
	}
	
	$sql = $sql." WHERE id = '$id'";
	
	$res = $mysqli->query($sql);
	$log->log($sql);	
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	if($res)
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
