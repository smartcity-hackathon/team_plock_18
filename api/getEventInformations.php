<?php
require_once('config/config.php');

	$json = json_decode($_POST['JSON'], true);

	$stmt = $db->prepare(
		"SELECT
			name,
			description,
			toDate,
			fromDate,
			place,
			categoryName
		FROM
			events
		INNER JOIN
			categories
				ON events.idCategory = categories.idCategory
		WHERE
			idEvent == :idEvent"
	);
	$stmt->bindValue(':idEvent', $json['id'], PDO::PARAM_INT);
	$stmt->execute();

	$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($result);
?>
