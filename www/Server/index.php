<?php
require 'database.php';

$conn = openConnection();
$entries = readAlerts($conn);

//var_dump($entries);
/*foreach($entries as $entry)
{
    var_dump($entry);
    //echo $entry["Name"];
    echo "<br>";
}*/
echo json_encode($entries);
?>