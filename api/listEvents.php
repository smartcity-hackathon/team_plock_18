<?php
require_once('config/config.php');

	$stmt = $db->query(
		"SELECT
			idEvent,
			name,
			fromDate,
			toDate,
			place,
			categoryName
		FROM
			events
		INNER JOIN
			categories
				ON events.idCategory = categories.idCategory
		WHERE
			toDate >= CURDATE()
		ORDER BY fromDate ASC"
	);
	$stmt->execute();
	$result = $stmt->fetchAll(PDO::FETCH_ASSOC);

	echo json_encode($result);
 ?>
