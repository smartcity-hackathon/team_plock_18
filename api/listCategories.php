<?php
require_once('config/config.php');

	$stmt = $db->query(
		"SELECT * FROM categories"
	);
	$stmt->execute();

	$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($result);

?>
