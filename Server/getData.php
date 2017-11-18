<?php
require 'database.php';

header('Access-Control-Allow-Methods: GET, POST'); 
header("Access-Control-Allow-Headers: X-Requested-With"); 

$conn = openConnection();
$entries = readAlerts($conn);

echo json_encode($entries);
?>