<?php

require 'conexion.php';
require 'consultaInventario.php';

function getProducto($idProducto)
{

  
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM psg_productos a LEFT JOIN psg_productos_cstm ac ON a.id = ac.id_c WHERE id = '$idProducto'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$obj = (object) $r;
		
		$cantProducts = searchInfo($r['codigo_c']);
		$obj->saldo = $cantProducts;
		
		$a =  (array) $obj;
		
		$rows[] = $a;
	}
		
	if( empty( $rows ) )
	{
		return '{"results" :[]}';
	}
	else
	{
		//Convierte el arreglo en json y lo retorna
		$temp = json_encode(utf8ize($rows));
		return '{"results" :'.$temp.'}';
	}
}

function getProductos($queryText = null)
{


	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT id,name FROM psg_productos p, psg_productos_cstm pc where p.id=pc.id_c AND deleted ='0' ";
	
	if($queryText != null && !empty($queryText)){
		$sql .= " AND ( name like ('%$queryText%') OR  codigo_c like ('%$queryText%') )";
	}else{
		$sql .= " limit 50" ;
	}
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		
		$obj = (object) $r;
		
		$a =  (array) $obj;
	
		$rows[] = $a;
	}
	
	if( empty( $rows ) )
	{
		return '{"results" :[]}';
	}
	else
	{
		//Convierte el arreglo en json y lo retorna
		$temp = json_encode(utf8ize($rows));
		return '{"results" :'.$temp.'}';
	}
}

function utf8ize($d)
{
	if (is_array($d))
	{
		foreach ($d as $k => $v)
		{
			$d[$k] = utf8ize($v);
		}
	}
	else if (is_string ($d))
	{
		return utf8_encode($d);
	}
	return $d;
}

?>
