<?php

require 'conexion.php';

function addCall($name,$description,$duration_minutes,$date_start,$date_end,$status,$direction,$resultadodelallamada_c,$idContact,$idUsuario)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");
	$id_llamada = md5($fecha);	
		
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "INSERT INTO calls (id,name,date_entered,created_by,description,assigned_user_id,duration_minutes,date_start,date_end,status,direction) 
	VALUES ('$id_llamada','$name','$fecha','$idUsuario','$description','$idUsuario','$duration_minutes','$date_start','$date_end','$status','$direction')"; 
	$res = $mysqli->query($sql);
	
	$sql = "INSERT INTO calls_cstm (id_c,resultadodelallamada_c) VALUES ('$id_llamada','$resultadodelallamada_c')"; 
	$res2 = $mysqli->query($sql);
	
	$id_temp = md5($id_llamada.'-'.$idUsuario);
	
	$sql = "INSERT INTO calls_contacts (id,call_id,contact_id,date_modified) VALUES ('$id_temp','$id_llamada','$idContact','$fecha')"; 
	$res3 = $mysqli->query($sql);
	
	if($res && $res2 && $res3)
	{
		$array = array("respuesta" => "OK");
		return json_encode($array);
	}
	else
	{
		$array = array("respuesta" => "FAIL");
		return json_encode($array);
	}
}

?>
