<?php
require 'database.php';

$conn = openConnection();
$entries = readAlerts($conn);
header('Access-Control-Allow-Methods: GET, POST');
header("Access-Control-Allow-Headers: X-Requested-With");
echo json_encode($entries);
?>