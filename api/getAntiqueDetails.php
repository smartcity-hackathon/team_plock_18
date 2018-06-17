<?php
require_once('config/config.php');

	$json = json_decode($_POST['JSON'], true);

	$stmt = $db->prepare(
		"SELECT
			name,
			place,
			description
		FROM
			historicalTreasures
		WHERE idHistTreas = :id"
	);
	$stmt->bindValue(':id', $json['id'], PDO::PARAM_INT);
	$stmt->execute();

	$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($result);
 ?>
