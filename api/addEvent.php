<?php
require_once('config/config.php');

	try {
			$json = json_decode($_POST['JSON'], true);

			$stmt = $db->prepare(
				"INSERT INTO
					events(
						idEvent,
						name,
						description,
						fromDate,
						toDate,
						place,
						idCategory)
				VALUES (
					NULL,
					:name,
					:description,
					:fromDate,
					:toDate,
					:place,
					:idCategory
				)"
			);
			$stmt->bindValue(':name', $json['name'], PDO::PARAM_STR);
			$stmt->bindValue(':description', $json['description'], PDO::PARAM_STR);
			$stmt->bindValue(':fromDate', $json['fromDate']);
			$stmt->bindValue(':toDate', $json['toDate']);
			$stmt->bindValue(':place', $json['place'], PDO::PARAM_STR);
			$stmt->bindValue(':idCategory', $json['idCategory'], PDO::PARAM_INT);
			$stmt->execute();

	} catch (Exception $e) {
		echo json_encode(
			array(
				'type' => "error",
				'error' => "Nie udało się utworzyć wydarzenia",
			)
		);
	}

 ?>
