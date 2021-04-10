#!/usr/local/bin/php
<?php 

$timestamp = date('Y-m-d H:i', time());

$configs = require('config.php');
$apikey = $configs['steam_apikey'];
$steamid = $configs['steam_id'];


$ch = curl_init("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key={$apikey}&steamid={$steamid}&include_appinfo=true&format=json");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$output = curl_exec($ch);

if($output === false)
{
    echo 'Curl error: ' . curl_error($ch);
    exit();
}

$json = json_decode($output, true);

$trimmedjson = $json["response"]["games"];

// Create connection
$conn = new mysqli($configs['db_host'], $configs['db_username'], $configs['db_password'], 'GameTime');
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

foreach($trimmedjson as $value) {
    $appid = $value["appid"];
    $name = addslashes($value["name"]);
    $time = intdiv($value["playtime_forever"], 60);
    $timeMinutes = $value["playtime_forever"];

    $getsql = "SELECT * FROM PC WHERE appid = $appid";
    $getresult = $conn->query($getsql);

    $row = $getresult->fetch_assoc();

    if ($getresult->num_rows == 0 && $timeMinutes > 0) {
        $setsql = "INSERT INTO PC (id, appid, name, hours, totalMinutes) VALUES (NULL, $appid, '$name', $time, $timeMinutes)";
        if ($conn->query($setsql) === TRUE) {
            echo "{$timestamp}:     {$name} added successfully \n";
        } else {
            echo "Error: " . $setsql . "<br>" . $conn->error . "<br>";
        }
    } else if ($row["totalMinutes"] < $timeMinutes) {
        $setsql = "UPDATE PC SET totalMinutes = $timeMinutes, hours = $time WHERE appid = $appid";
        if ($conn->query($setsql) === TRUE) {
            echo "{$timestamp}:     {$name} updated successfully \n";
        } else {
            echo "Error: " . $setsql . "<br>" . $conn->error . "<br>";
        }
    }

}

?>
