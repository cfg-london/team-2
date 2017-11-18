<?php
    require 'database.php';
    $result = 0;
    if(isset($_GET['Name'])
    && isset($_GET['Contact'])
    && isset($_GET['Problem'])
    && isset($_GET['Priority'])
    && isset($_GET['XLoc'])
    && isset($_GET['YLoc']))
    {
            $conn = openConnection();
            //function addNewUser($conn, $name, $contact, $problem, $priority)
            $result = addNewUser2($conn, $_GET['Name'], $_GET['Contact'], $_GET['Problem'], $_GET['Priority'], $_GET['XLoc'], $_GET['YLoc']);
    }

    echo $result;
?>