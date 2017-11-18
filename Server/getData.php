<?php
require 'database.php';

$conn = openConnection();
$entries = readAlerts($conn);

echo json_encode($entries);
?>