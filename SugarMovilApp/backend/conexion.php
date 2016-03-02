<?php


function makeSqlConnection()
{	
	$con = new mysqli('localhost', 'root', '2nnIco5FXeVVz1B', 'crmpruebas_laumayer_produccion');	

	return $con;
}

?>
