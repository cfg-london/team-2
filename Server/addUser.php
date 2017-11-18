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
            $result = addNewUser($conn, $_GET['Name'], $_GET['Contact'], $_GET['Problem'], $_GET['Priority'], $_GET['XLoc'], $_GET['YLoc']);
            if(0==strcmp("FALL", $_GET['Problem']))
            {
                $filename="hasFallen.txt";
                $file=fopen($filename,"w");
                fwrite($file, "1");
                fclose($file);
            }
    }

    echo $result;
?>