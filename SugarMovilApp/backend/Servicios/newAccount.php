<?php

require 'conexion.php';

function newAccount($name,$nit,$codigoAlterno,$canal,$sector,$telefono1,$ext1,$telefono2,$ext2,$celular,$fax,$direccion,$municipio,$departamento,
	$zona,$uen,$email,$web,$grupo,$segmento,$estado,$descuento,$presupuesto,$descripcion,$correoTransporte,$usuarioAsignado,$usuarioCreador,
	$fechaConstitucion,$ventasActual,$ventasAnterior,$numeroAlianzas,$alianzas,$origenCuenta,
	$fechaFacturacion,$facturacionDiaria,$facturacionAcumuladaMes,$porcentajeCumplimiento,$facturacionAutorizada,$facturacionNoAutorizada,
	$fechaDespacho,$remesa,$destino,$nombreDestinatario,$numeroUnidades,$numeroDocumento,
	$nombreDestinatario2,$destino2,$motivo,
	$cupoDisponible,$cupoCr,$totalCartera,$condicionPago,$plazoPago,$promedioPago,$carteraVencida,$carteraVencer)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");
	
	$id = md5($nit.$fecha);	
	
		
	//------------------------------------------------------------------------------------------------------------------------------------------
	//Inserta el nuevo account -----------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	$mysqli = makeSqlConnection();
	$sql = "INSERT INTO accounts (id,name,date_entered,created_by,description,assigned_user_id,phone_office,phone_alternate,phone_fax,website) 
			VALUES ('$id','$name','$fecha','$usuarioCreador','$descripcion','$usuarioAsignado','$telefono1','$telefono2','$fax','$web')";
	$res = $mysqli->query($sql);
	
	if(!$res)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	

	
	//------------------------------------------------------------------------------------------------------------------------------------------
	//Inserta el nuevo account_cstm ------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------	
	$sql2 = "INSERT INTO accounts_cstm (id_c) VALUES ('$id')";
	$res2 = $mysqli->query($sql2);
	
	if(!$res2)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	//edita el account_cstm --------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	$sql3 = "UPDATE accounts_cstm SET 
	nit_c = '$nit',
	cod_alterno_c = '$codigoAlterno',
	canal_c = '$canal',
	sector_c = '$sector',
	celular_c = '$celular',
	direccion_c = '$direccion',
	municipio_c = '$municipio',
	departamento_c = '$departamento',
	zona_c = '$zona',
	uen_c = '$uen',
	grupo_objetivo_c = '$grupo',
	segmento_c = '$segmento',
	estado_c = '$estado',
	descuentocomercial_c = '$descuento',
	presupuestoanual_c = '$presupuesto',
	segmento_c = '$segmento',
	correotransporte_c = '$correoTransporte',
	ventasactual_c = '$ventasActual',
	ventasanterior_c = '$ventasAnterior',
	numeroalianzas_c = '$numeroAlianzas', 
	alianzasestrategicas_c = '$alianzas',
	origencuenta_c = '$origenCuenta',
	fechafacturacion_c = '$fechaFacturacion',
	facturaciondiara_c = '$facturacionDiaria',
	facturacionmes_c = '$facturacionAcumuladaMes',
	porcentaje_cumplimiento_c = '$porcentajeCumplimiento',
	facturacionautorizada_c = '$facturacionAutorizada', 
	facturacionnoautorizada_c = '$facturacionNoAutorizada',
	fecha_despacho_c = '$fechaDespacho',
	remesa_c = '$remesa',
	destino_c = '$destino',
	nombredestinatario_c = '$nombreDestinatario',
	unidades_c = '$numeroUnidades',
	documento_c = '$numeroDocumento',
	nombredestinatario2_c = '$nombreDestinatario2',
	destino2_c = '$destino2',
	motivo_c = '$motivo',
	cupodisponible_c = '$cupoDisponible',
	cupocr_c = '$cupoCr',
	totalcartera_c = '$totalCartera',
	condpago_c = '$condicionPago',
	plpago_c = '$plazoPago',
	prompago_c = '$promedioPago',
	carteravencida_c = '$carteraVencida',
	carteravencer_c = '$carteraVencer' ";
	
	if (ctype_digit((string)$ext1)) 
	{
		$sql3 = $sql3.",extension1_c = '$ext1' ";
	}
	
	if (ctype_digit((string)$ext2)) 
	{
		$sql3 = $sql3.",extension2_c = '$ext2' ";
	}
	
	if ($fechaConstitucion != '') 
	{
		$sql3 = $sql3.",fechaempresa_c = '$fechaConstitucion' ";
	}
	 
	$sql3 = $sql3."WHERE id_c = '$id'";
	
	$res3 = $mysqli->query($sql3);
	
	if(!$res3)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	//Inserta el nuevo emai --------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	$idEmail = md5($email.$fecha);
	$str = strtoupper($email);
	
	//Se crea el email
	$sql4 = "INSERT INTO email_addresses (id,email_address,email_address_caps,date_created) 
			VALUES ('$idEmail','$email','$str','$fecha')";
	$res4 = $mysqli->query($sql4);
	
	if(!$res4)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	//Inserta el nuevo email_rel -----------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------
	$idBeanRel = md5($idEmail.$id.$fecha);
	
	$sql5 = "INSERT INTO email_addr_bean_rel (id,email_address_id,bean_id,bean_module,primary_address,date_created) 
			VALUES ('$idBeanRel','$idEmail','$id','accounts','1','$fecha')";
	$res5 = $mysqli->query($sql5);
	
	if(!$res5)
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
		
	if($res && $res2 && $res3 && $res4 && $res5)
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




function esVacio($var)
{
	if($var == '' || $var == "")
	{
		return NULL;	
	}
	else 
	{
		return $var;
	}
}




?>


























