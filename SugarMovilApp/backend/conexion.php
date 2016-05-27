<?php


function makeSqlConnection()
{	//crmpruebas_laumayer_produccion
	$con = new mysqli('localhost', 'root', '2nnIco5FXeVVz1B', 'db_app_crm');	

	return $con;
}

?>
