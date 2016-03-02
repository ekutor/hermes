<?php
//$myServer = "192.168.0.103";
$myServer = "172.30.5.49";
$myUser = "UsrPsg";
$myPass = "PsGcRm1402*LaU+";
$myDB = "LAUMAYER"; 

$codigo= $_GET['txt'];
/*
?>
<script type="text/javascript">
alert(<?php echo "H".$codigo; ?>);
</script>
<?php
*/
//connection to the database
$dbhandle = mssql_connect($myServer, $myUser, $myPass)
  or die("Couldn't connect to SQL Server on $myServer"); 

//select a database to work with
$selected = mssql_select_db($myDB, $dbhandle)
  or die("Couldn't open database $myDB"); 

//declare the SQL statement that will query the database
/*
$query = "SELECT id, name, year ";
$query .= "FROM cars ";
$query .= "WHERE name='BMW'"; 
*/
#$query = "Select dbo.F_Saldo_Bodega_Informe(Year(GETDATE()),MONTH(GETDATE()),'LOV0325','BODPRDCTO','T','C') as Saldo";

$query = "Select dbo.F_Saldo_Bodega_Informe(Year(GETDATE()),MONTH(GETDATE()),'$codigo','BODPRDCTO','T','C') as Saldo";

//$query="SELECT * FROM information_schema.tables";


//execute the SQL query and return records
$result = mssql_query($query);

$numRows = mssql_num_rows($result); 
//echo "<h1>" . $numRows . " Row" . ($numRows == 1 ? "" : "s") . " Returned </h1>"; 

//display the results 
while($row = mssql_fetch_array($result))
{
//print_r($row);
  echo $row['Saldo'];
}
//close the connection

mssql_close($dbhandle);

//return $row;

