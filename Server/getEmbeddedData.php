<?php
require 'database.php';

$conn = openConnection();
$entries = readBoardEntries($conn);

echo json_encode($entries);
?>