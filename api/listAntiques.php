<?php
require_once('config/config.php');

	$stmt = $db->query("SELECT idHistTreas AS id, name FROM historicalTreasures");
	$stmt->execute();

	$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($result);
?>
