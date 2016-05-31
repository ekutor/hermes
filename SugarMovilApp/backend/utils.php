<?php

function getTabs($value){

  $text = str_replace("*.#.", "\n", $value);
  $text = str_replace("*##*", "\t", $text);
 return $text;
}
