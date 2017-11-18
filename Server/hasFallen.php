<?php
    $filename="hasFallen.txt";
    $file=fopen($filename,"r");
    echo fread($file, filesize($filename));
    fclose($file);

    $file=fopen($filename,"w");
    fwrite($file, "0");
    fclose($file);
?>