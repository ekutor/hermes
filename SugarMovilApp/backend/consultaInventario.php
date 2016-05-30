<?php

function searchInfo($productCode)
{
//$myServer = "192.168.0.103";
$myServer = "172.30.5.49";
$myUser = "UsrPsg";
$myPass = "PsGcRm1402*LaU+";
$myDB = "LAUMAYER"; 

$dbhandle = mssql_connect($myServer, $myUser, $myPass)
  or die("Couldn't connect to SQL Server on $myServer"); 

$selected = mssql_select_db($myDB, $dbhandle)
  or die("Couldn't open database $myDB"); 

$query = "Select dbo.F_Saldo_Bodega_Informe(Year(GETDATE()),MONTH(GETDATE()),'$productCode','BODPRDCTO','T','C') as saldo";

$result = mssql_query($query);

$numRows = mssql_num_rows($result); 

if($row = mssql_fetch_array($result))
{

  $objectRow =  $row['saldo'];
  //$objectRow = $query;
}

mssql_close($dbhandle);

return $objectRow;

}

