<?php
class IOManager{
  var $file;
  var $path; 
  
 public function __construct() {
    $this->path = "/var/www/html/crm/pruebas/crm/movil/CRMLaumayerWS/IOManager/";
	$this->file = fopen($this->path."debug.log", "a") or die("Unable to open file!");

  }
 
 public function log($message){
	 fwrite($this->file, "\n".$message);
 }
 public function logAll($list){
	foreach ($list as $clave => $valor) {
		$this->log("Clave: ".$clave." = ".$valor);
	}
 }
}