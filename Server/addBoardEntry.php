<?php
    require 'database.php';
    $result = 0;
    if(isset($_GET['Name'])
    && isset($_GET['Contact'])
    && isset($_GET['Steps'])
    && isset($_GET['Temperature']))
    {
            $conn = openConnection();
            //function addNewUser($conn, $name, $contact, $problem, $priority)
            $result = addNewBoardEntry($conn, $_GET['Name'], $_GET['Contact'],$_GET['Steps'], $_GET['Temperature']);
    }

    echo $result;
?>