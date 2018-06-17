<?php

	$json = json_decode($_POST['JSON'], true);

	$recievedJson = $_REQUEST['image'];
	$imageContent = json_decode($recievedJson,true);
	$base = $imageContent["img"];

	$binary = base64_decode($base);
	$imageName = 'uploaded_image.jpg';
	header('Content-Type: bitmap; charset=utf-8');
	$file = fopen("images/{$imageName}", 'wb');
	fwrite($file, $binary);
	fclose($file);

	if ($json['to'] == "police") {
		$mail = "poloicja@plock.pl";
	} elseif ($json['to'] == "ambulance") {
		$mail = "pogotowie@plock.pl";
	} elseif ($json['to'] == "fireman") {
		$mail = "strazpozarna@plock.pl";
	} else {
		$mail = "urzadmiasta@plock.pl";
	}

	$headlines="From: {$json['from']}".PHP_EOL."Content-type: text/html; charset=utf-8"."X-MSMail-Priority: High";

	$message = "
		<html>
		<head>
			<title>
				{$json['title']}
			</title>
		</head>
		<body>
			<p>
				{$json['content']}
			</p>
			<img src=\"images/{$imageName}\" />
		</body>
		</html>
	";



  if (!preg_match("/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/", $_POST['title'])) {
      exit("Nieprawidłowy e-mail");
  } elseif (mail($mail, $json['title'], $json['content'], $headlines)) {
      echo "Wysłano";
  } else {
      echo "Wystąpił błąd! Spróbuj ponownie.";
  }

	unlink("images/{$imageName}");
?>
