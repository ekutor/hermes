<?php

require 'conexion.php';

function getProductos()
{
$myServer = "172.30.5.49";
$myUser = "UsrPsg";
$myPass = "PsGcRm1402*LaU+";
$myDB = "LAUMAYER";
$dbhandle = mssql_connect($myServer, $myUser, $myPass)
  or die("Couldn't connect to SQL Server on $myServer");
$selected = mssql_select_db($myDB, $dbhandle)
  or die("Couldn't open database $myDB");


	//Realiza el query en la base de datos
	$mysqli = makeSqlConnection();
	//$sql = "SELECT * FROM psg_productos a LEFT JOIN psg_productos_cstm ac ON a.id = ac.id_c";
	$sql = "SELECT id,name FROM psg_productos where deleted ='0'";
	$res = $mysqli->query($sql);
	
	$rows = array();
	
	while($r = mysqli_fetch_assoc($res))
	{
		
		$obj = (object) $r;
		$querySaldo = "Select dbo.F_Saldo_Bodega_Informe(Year(GETDATE()),MONTH(GETDATE()),'".$r['id']."','BODPRDCTO','T','C') as Saldo";
		$result = mssql_query($querySaldo);
		if($row = mssql_fetch_array($result))
		{
			$obj->saldo =$row['Saldo'];	
		}	
		
		$a =  (array) $obj;
	
		$rows[] = $a;
	}
	mssql_close($dbhandle);	
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
