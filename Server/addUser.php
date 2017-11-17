<?php
    require 'database.php';
    $result = 0;
    if(isset($_GET['Name'])
    && isset($_GET['Contact'])
    && isset($_GET['Problem'])
    && isset($_GET['Priority']))
    {
            $conn = openConnection();
            //function addNewUser($conn, $name, $contact, $problem, $priority)
            $result = addNewUser($conn, $_GET['Name'], $_GET['Contact'], $_GET['Problem'], $_GET['Priority'],0,0);
    }

    echo $result;
?>