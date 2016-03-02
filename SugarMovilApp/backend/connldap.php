<?php


function connectLdap($user,$pass)
{	

	$adServer = "ldap://laumayer.local";
	$ldapCon = ldap_connect($adServer);
	
	$ldaprdn = 'LAUMAYER' . "\\" . $user;
	ldap_set_option($ldapCon, LDAP_OPT_PROTOCOL_VERSION, 3);
	ldap_set_option($ldapCon, LDAP_OPT_REFERRALS, 0);
	
	$bind = @ldap_bind($ldapCon, $ldaprdn, $pass);

	if($bind){
		$filter="(sAMAccountName=$user)";
		$resp = "AUTHENTICATE";
 	}else{
		$resp = "FAIL";
	}
	@ldap_close($ldapCon);
	return $resp;

}

?>
