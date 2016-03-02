<?php

require 'conexion.php';

function addOpportunity($tipo,$nombre,$marcasEnergia,$marcasComunicaciones,$marcasIluminacion,$etapa,$cuenta,$usuarioFinal,$valorEstimado,$fechaCierre,$medio,$descripcion,$proximoPaso,$probabilidad,$idUsuario)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");
	$id = md5($fecha);	
		
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "INSERT INTO opportunities (id,name,date_entered,created_by,description,assigned_user_id,opportunity_type,amount,date_closed,next_step,sales_stage,probability) VALUES ('$id','$name','$fecha','$idUsuario','$descripcion','$idUsuario','$tipo','$valorEstimado','$fechaCierre','$proximoPaso','$etapa','$probabilidad')"; 
	$res = $mysqli->query($sql);
	
	
	if($res)
	{
		$array = array("respuesta" => "OK","respuesta2" => "OK");
		return json_encode($array);
	}
	else
	{
		$array = array("respuesta" => "FAIL");
		return json_encode($array);
	}
}

?>
