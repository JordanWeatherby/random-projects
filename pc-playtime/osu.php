#!/usr/local/bin/php
<?php 

$timestamp = date('Y-m-d H:i', time());

$configs = require('/var/www/html/databases/config.php');
$apikey = $configs['osu_apikey'];
$osuid = $configs['osu_id'];

$secondsSum = 0;

// Get all modes
for ($x = 0; $x <= 3; $x++) {
    $ch = curl_init("https://osu.ppy.sh/api/get_user?k={$apikey}&u={$osuid}&m={$x}");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    $output = curl_exec($ch);

    $json = json_decode($output, true);

    $value = $json[0];

    $secondsSum += $value["total_seconds_played"];
}


// Create connection
$conn = new mysqli($configs['db_host'], $configs['db_username'], $configs['db_password'], 'GameTime');
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

    $hours = intdiv($secondsSum, 3600);
    $timeMinutes = intdiv($secondsSum, 60);

    $getsql = "SELECT * FROM PC WHERE name = 'osu!'";
    $getresult = $conn->query($getsql);

    $row = $getresult->fetch_assoc();
    if ($getresult->num_rows == 0) {
        $setsql = "INSERT INTO PC (id, appid, name, hours, totalMinutes) VALUES (NULL, 0, 'osu!', $hours, $timeMinutes)";
        if ($conn->query($setsql) === TRUE) {
            echo "{$timestamp}:     osu! added successfully \n";
        } else {
            echo "Error: " . $setsql . "<br>" . $conn->error . "<br>";
        }
    } else if ($row["totalMinutes"] < $timeMinutes) {
        $setsql = "UPDATE PC SET totalMinutes = $timeMinutes, hours = $hours WHERE name = 'osu!'";
        if ($conn->query($setsql) === TRUE) {
            echo "{$timestamp}:     osu! updated successfully \n";
        } else {
            echo "Error: " . $setsql . "<br>" . $conn->error . "<br>";
        }
    }


?>