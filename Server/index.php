<?php
require 'database.php';
$conn = openConnection();
$entries = readPointEntries($conn);

var_dump($entries);
/*foreach($entries as $entry)
{
    var_dump($entry);
    //echo $entry["Name"];
    echo "<br>";
}*/

?>