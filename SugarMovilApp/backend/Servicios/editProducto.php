<?php

require 'conexion.php';

function editProducto($idUsuario,$idProducto,$codigo,$nombre,$referencia,$marca,$inventario,$precioPesos,$precioDolares,$grupo)
{
	date_default_timezone_set('America/Bogota');
	$fecha = date("Y/m/d h:i:s");	
		
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "UPDATE psg_productos SET date_modified = '$fecha', name = '$nombre', modified_user_id = '$idUsuario' WHERE id = '$idProducto'"; 
	$res = $mysqli->query($sql);
	
	$mysqli = makeSqlConnection();
	$sql = "UPDATE psg_productos_cstm SET codigo_c = '$codigo', referencia_c = '$referencia', marca_c = '$marca', grupo_c = '$grupo', en_inventario_c = '$inventario', precio1_c = '$precioPesos', precio2_c = '$precioDolares'  WHERE id_c = '$idProducto'";
	$res2 = $mysqli->query($sql);
		
	if($res && $res2)
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
