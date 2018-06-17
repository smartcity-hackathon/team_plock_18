<?php
$db_config = array(
	'host' => 'localhost',
	'user' => 'root',
	'pass' => 'root',
	'db' => 'mojPlock',
	'db_type' => 'mysql',
	'encoding' => 'utf8_polish_ci'
);
try {
  $dsn = $db_config['db_type'] .
  ':host=' . $db_config['host'] .
  ';encoding=' . $db_config['encoding'] .
  ';dbname=' . $db_config['db'];

  $db = new PDO(
  $dsn,
  $db_config['user'],
  $db_config['pass'] ,
  array(
  PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8",
  PDO::ATTR_EMULATE_PREPARES => false,
  PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
  ));

  $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

} catch (PDOException $e) {
	die ("Wystąpił błąd. Kod błędu: ".$e->getMessage());
}
?>
