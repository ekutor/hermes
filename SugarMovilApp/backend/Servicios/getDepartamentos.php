<?php

require 'conexion.php';

function getDepartamentos()
{
	

$temp = array();
$obj = (object) array('id' => '0');

$obj->id = '91';
$obj->nombre = 'AMAZONAS';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '05';
$obj->nombre = 'ANTIOQUIA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '81';
$obj->nombre = 'ARAUCA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '08';
$obj->nombre = 'ATLANTICO';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '13';
$obj->nombre = 'BOLIVAR';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '15';
$obj->nombre = 'BOYACA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '17';
$obj->nombre = 'CALDAS';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '18';
$obj->nombre = 'CAQUETA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '85';
$obj->nombre = 'CASANARE';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '19';
$obj->nombre = 'CAUCA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '20';
$obj->nombre = 'CESAR';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '27';
$obj->nombre = 'CHOCO';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '23';
$obj->nombre = 'CORDOBA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '25';
$obj->nombre = 'CUNDINAMARCA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '11';
$obj->nombre = 'DISTRITO CAPITAL DE BOGOTA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '94';
$obj->nombre = 'GUAINIA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '44';
$obj->nombre = 'GUAJIRA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '95';
$obj->nombre = 'GUAVIARE';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '41';
$obj->nombre = 'HUILA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '47';
$obj->nombre = 'MAGDALENA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '50';
$obj->nombre = 'META';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '52';
$obj->nombre = 'NARINO';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '54';
$obj->nombre = 'NORTE DE SANTANDER';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '86';
$obj->nombre = 'PUTUMAYO';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '63';
$obj->nombre = 'QUINDIO';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '66';
$obj->nombre = 'RISARALDA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '88';
$obj->nombre = 'SAN ANDRES';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '68';
$obj->nombre = 'SANTANDER';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '70';
$obj->nombre = 'SUCRE';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '73';
$obj->nombre = 'TOLIMA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '76';
$obj->nombre = 'VALLE';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '97';
$obj->nombre = 'VAUPES';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '99';
$obj->nombre = 'VICHADA';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;

$obj->id = '0';
$obj->nombre = 'VARIOS';
$obj->municipios = getMunicipios($obj->id);
$a =  (array) $obj;
$temp[] = $a;


	
	$temp = json_encode($temp);
	return '{"results" :'.$temp.'}';
}



function getMunicipios($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM psg_mod_ciudades a LEFT JOIN psg_mod_ciudades_cstm ac ON a.id = ac.id_c WHERE cod_dpto_c = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$rows[] = $r['name'];
	}
	
	return $rows;
}


function getMunicipiosDepartamento($id)
{
	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	$sql = "SELECT * FROM psg_mod_ciudades a LEFT JOIN psg_mod_ciudades_cstm ac ON a.id = ac.id_c WHERE cod_dpto_c = '$id'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		$rows[] = $r['name'];
	}
	
	$temp = json_encode($rows);
	return '{"results" :'.$temp.'}';
}





?>











