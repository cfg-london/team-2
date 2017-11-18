<?php
require 'database.php';

header('Access-Control-Allow-Methods: GET, POST'); 
header("Access-Control-Allow-Headers: X-Requested-With"); 
header("Access-Control-Allow-Origin: {$_SERVER['HTTP_ORIGIN']}");
header('Access-Control-Allow-Credentials: true');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST');
header('Access-Control-Allow-Methods: GET, POST');
header("Access-Control-Allow-Headers: X-Requested-With");

$conn = openConnection();
$entries = readAlerts($conn);

echo json_encode($entries);
?>