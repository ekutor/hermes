<?php

require 'conexion.php';

function editAccount($id,$name,$nit,$codigoAlterno,$canal,$sector,$telefono1,$ext1,$telefono2,$ext2,$celular,$fax,$direccion,$municipio,$departamento,
	$zona,$uen,$email,$web,$grupo,$segmento,$estado,$descuento,$presupuesto,$descripcion,$correoTransporte,$usuarioAsignado,$usuarioCreador,
	$fechaConstitucion,$ventasActual,$ventasAnterior,$numeroAlianzas,$alianzas,$origenCuenta,
	$fechaFacturacion,$facturacionDiaria,$facturacionAcumuladaMes,$porcentajeCumplimiento,$facturacionAutorizada,$facturacionNoAutorizada,
	$fechaDespacho,$remesa,$destino,$nombreDestinatario,$numeroUnidades,$numeroDocumento,
	$nombreDestinatario2,$destino2,$motivo,
	$cupoDisponible,$cupoCr,$totalCartera,$condicionPago,$plazoPago,$promedioPago,$carteraVencida,$carteraVencer)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
		
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "UPDATE accounts SET 
	name = '$name',
	date_modified = '$fecha',
	modified_user_id = '$usuarioCreador',
	description = '$descripcion',
	assigned_user_id = '$usuarioAsignado',
	phone_office = '$telefono1',
	phone_alternate = '$telefono2',
	phone_fax = '$fax',
	website = '$web' 
	WHERE id = '$id'"; 
	$res = $mysqli->query($sql);
	
	if($res)
	{
		
	}
	else
	{
		$array = array("respuesta" => "FAIL", "error" => $mysqli->error);
		return json_encode($array);
	}
	
	
	$sql2 = "UPDATE accounts_cstm SET 
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
		$sql2 = $sql2.",extension1_c = '$ext1' ";
	}
	
	if (ctype_digit((string)$ext2)) 
	{
		$sql2 = $sql2.",extension2_c = '$ext2' ";
	}
	
	if ($fechaConstitucion != '') 
	{
		$sql2 = $sql2.",fechaempresa_c = '$fechaConstitucion' ";
	}
	 
	$sql2 = $sql2."WHERE id_c = '$id'";
	 

	$res2 = $mysqli->query($sql2);
	
	
	editarEmail($id, $email);
	
	
		
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















